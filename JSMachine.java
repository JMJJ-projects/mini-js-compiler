import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Interpreter for the Mini-JS-Machine assembly (.jsm) produced by the compiler.
 *
 * Implements the "minimal machine" of the course specification: a value stack,
 * one variable context, relative jumps, and Print / Halt.
 *
 * Usage: java JSMachine program.jsm [--trace]
 */
public class JSMachine {

    /** A JSM value: a double, a boolean, or undefined (null). */
    static String show(Object v) {
        if (v == null) return "undefined";
        if (v instanceof Double) {
            double d = (Double) v;
            if (Double.isNaN(d)) return "NaN";
            if (d == Math.rint(d) && !Double.isInfinite(d)) return Long.toString((long) d);
            return Double.toString(d);
        }
        return (Boolean) v ? "true" : "false";
    }

    static double num(Object v, String instr) {
        if (v instanceof Double) return (Double) v;
        if (v instanceof Boolean) return (Boolean) v ? 1 : 0;   // JS coercion
        if (v == null) return Double.NaN;
        throw new IllegalStateException(instr + ": nombre attendu");
    }

    static boolean bool(Object v) {
        if (v instanceof Boolean) return (Boolean) v;
        if (v instanceof Double) { double d = (Double) v; return d != 0 && !Double.isNaN(d); }
        return false;
    }

    private final List<String[]> code = new ArrayList<>();
    private final Deque<Object> stack = new ArrayDeque<>();
    private final Map<String, Object> context = new LinkedHashMap<>();
    private final StringBuilder out = new StringBuilder();
    private long steps = 0;

    public JSMachine(List<String> lines) {
        for (String line : lines) {
            String t = line.trim();
            if (!t.isEmpty()) code.add(t.split("\\s+", 2));
        }
    }

    private Object pop(String instr) {
        if (stack.isEmpty()) throw new IllegalStateException(instr + ": pile vide");
        return stack.pop();
    }

    /** Runs the program; returns everything written by Print. */
    public String run(boolean trace) {
        stack.push(UNDEFINED_MARK);  // the machine starts with one undefined value
        int pc = 0;
        while (pc < code.size()) {
            if (++steps > 10_000_000) throw new IllegalStateException("trop d'étapes (boucle infinie ?)");
            String[] ins = code.get(pc);
            String op = ins[0];
            String arg = ins.length > 1 ? ins[1] : null;
            if (trace) System.err.printf("%4d  %-16s stack=%s%n", pc, String.join(" ", ins), stackView());
            int next = pc + 1;

            switch (op) {
                case "CstNb": case "CsteNb":
                    stack.push(arg.equals("NaN") ? Double.NaN : Double.parseDouble(arg)); break;
                case "CstBo": case "CsteBo":
                    stack.push(arg.equalsIgnoreCase("true")); break;
                case "CsteUn": stack.push(UNDEFINED_MARK); break;

                case "AddiNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a + b); break; }
                case "SubsNb": case "SubiNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a - b); break; }
                case "MultNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a * b); break; }
                case "DiviNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a / b); break; }
                case "NegaNb": stack.push(-num(pop(op), op)); break;
                case "Not": stack.push(!bool(pop(op))); break;
                case "BoToNb": stack.push(num(pop(op), op)); break;
                case "NbToBo": case "NbToBe": stack.push(bool(pop(op))); break;

                case "Equals": case "Equal": { Object b = unwrap(pop(op)), a = unwrap(pop(op)); stack.push(jsEquals(a, b)); break; }
                case "NotEql": { Object b = unwrap(pop(op)), a = unwrap(pop(op)); stack.push(!jsEquals(a, b)); break; }
                case "LoEqNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a <= b); break; }
                case "GrEqNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a >= b); break; }
                case "LoStNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a < b); break; }
                case "GrStNb": case "GrStsNb": { double b = num(pop(op), op), a = num(pop(op), op); stack.push(a > b); break; }

                case "Jump": next = pc + Integer.parseInt(arg) + 1; break;
                case "ConJmp": if (!bool(pop(op))) next = pc + Integer.parseInt(arg) + 1; break;

                case "Copy": stack.push(stack.peek()); break;
                case "Swap": { Object x = pop(op), y = pop(op); stack.push(x); stack.push(y); break; }
                case "Drop": pop(op); break;
                case "Noop": break;

                case "SetVar": context.put(arg, unwrap(pop(op))); break;
                case "GetVar":
                    if (!context.containsKey(arg)) throw new IllegalStateException("variable non définie : " + arg);
                    stack.push(wrap(context.get(arg))); break;

                case "Print": out.append(show(unwrap(pop(op)))).append('\n'); break;
                case "Assert": if (!bool(pop(op))) throw new IllegalStateException("assertion : " + arg); break;
                case "Error": throw new IllegalStateException(arg == null ? "erreur" : arg);
                case "Halt": return out.toString();
                default: throw new IllegalStateException("instruction inconnue : " + op);
            }
            if (next < 0 || next > code.size()) throw new IllegalStateException("saut hors du programme à la ligne " + pc);
            pc = next;
        }
        return out.toString();
    }

    // ArrayDeque refuses null, so undefined is stored on the stack as a marker object.
    private static final Object UNDEFINED_MARK = new Object();
    private static Object unwrap(Object v) { return v == UNDEFINED_MARK ? null : v; }
    private static Object wrap(Object v) { return v == null ? UNDEFINED_MARK : v; }

    private static boolean jsEquals(Object a, Object b) {
        if (a == null || b == null) return a == b;
        if (a instanceof Double && b instanceof Double) return ((Double) a).doubleValue() == ((Double) b).doubleValue();
        return a.equals(b);
    }

    private String stackView() {
        List<String> items = new ArrayList<>();
        for (Object v : stack) items.add(show(unwrap(v)));
        return items.toString();
    }

    public Map<String, Object> variables() { return context; }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("usage: java JSMachine programme.jsm [--trace] [--vars]");
            System.exit(2);
        }
        boolean trace = false, vars = false;
        for (String a : args) { if (a.equals("--trace")) trace = true; if (a.equals("--vars")) vars = true; }
        JSMachine m = new JSMachine(Files.readAllLines(Paths.get(args[0])));
        try {
            System.out.print(m.run(trace));
        } catch (RuntimeException e) {
            System.err.println("Erreur d'exécution : " + e.getMessage());
            System.exit(1);
        }
        if (vars) {
            for (Map.Entry<String, Object> e : m.variables().entrySet())
                System.out.println(e.getKey() + " = " + show(e.getValue()));
        }
    }
}
