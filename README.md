# Mini-JS compiler and virtual machine

![tests](https://github.com/JMJJ-projects/mini-js-compiler/actions/workflows/test.yml/badge.svg)

A compiler for a small subset of JavaScript, written in Java with **JavaCC**, that produces assembly for
the course's stack-based **Mini-JS-Machine**. A virtual machine that executes that assembly is included,
so programs run end to end.

University compiler course project (Licence 3 Informatique, Université Sorbonne Paris Nord, 2025),
built in pairs with Gildas De Michiel. The assignment also graded the git history, so the commits
follow the course step by step (TP1 → TP2 → fragments 1–3).

```
source.js ──► lexer + parser (JavaCC) ──► AST ──► constant folding ──► code generation ──► source.jsm ──► JSMachine
```

## Example

```js
// examples/pgcd.js — greatest common divisor by repeated subtraction
a = 1071;
b = 462;
do {
    if (a > b) { a = a - b; } else { b = b - a; }
} while (!(a == b));
print(a);
```

```console
$ make
$ ./mjs examples/pgcd.js
21
```

The generated `examples/pgcd.jsm`:

```
CstNb 1071
SetVar a
CstNb 462
SetVar b
GetVar a
GetVar b
GrStNb
ConJmp 5
GetVar a
GetVar b
SubsNb
SetVar a
Jump 4
GetVar b
GetVar a
SubsNb
SetVar b
GetVar a
GetVar b
Equals
Not
ConJmp 1
Jump -19
GetVar a
Print
Halt
```

## Language

| Feature | Syntax |
|---|---|
| Numbers | `42`, `3.14`, `1.5e3`, `NaN` |
| Booleans | `True`, `False` |
| Arithmetic | `+ - * /`, unary `-` |
| Comparison & logic | `== < <= > >=`, `&&`, `!` |
| Variables | `x = expr;`, chained `x = y = 0;` |
| Control flow | `if (c) … else …`, `do { … } while (c);`, `{ … }` blocks |
| Output | `print(expr);` |
| Modules | `import name` inlines an already compiled `name.jsm` |
| Comments | `// …` and `/* … */` |

## How it works

- **Grammar** ([`Compilateur.jj`](Compilateur.jj)): one JavaCC rule per precedence level
  (logic → equality → comparison → additive → multiplicative → unary), so the parse tree directly
  encodes operator precedence and left associativity.
- **AST**: one class per construct (`Plus`, `IfElse`, `DoWhile`, `SetVar`…). Each node knows its
  size in instructions (`getTaille()`), which is how jump offsets for `if`/`while`/`&&` are computed
  without labels.
- **Optimisation**: `simplifie()` folds constant sub-expressions (`2 * (3 + 4)` → `14`) and removes
  branches whose condition is known at compile time.
- **Code generation**: `toAssembly()` emits the stack-machine instructions (`CstNb`, `AddiNb`,
  `ConJmp`, `Jump`, `SetVar`, `Print`…).
- **Virtual machine** ([`JSMachine.java`](JSMachine.java)): interprets `.jsm` with a value stack,
  a variable context and relative jumps. `--trace` prints every step, `--vars` dumps the variables.

## Build & test

Requires Java 11+ (`lib/javacc.jar` is bundled).

```bash
make                         # generate the parser and compile everything into build/
./mjs examples/factorielle.js
./mjs examples/fibonacci.js --trace
make test                    # compile + run every example and compare with *.expected
```

## Changes since the course submission (2026)

- Fixed jump offsets: variables, unary minus and `!` reported a size of 0 or 1, which broke
  `if`/`while` containing them. `import` now accounts for the size of the imported code.
- Fixed instruction typos (`GrStsNb`, `SubiNb`, `CsteBo`) and statement sequences that skipped constant folding.
- Programs can now span several lines (newlines used to end the program).
- Added `print`, dropping of unused expression results, the `JSMachine` VM, examples, `make test` and CI.
