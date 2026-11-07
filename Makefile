# Build the compiler and the virtual machine.
#   make            -> build/
#   make test       -> compile and run every program in examples/, compare with *.expected
#   make run P=examples/factorielle.js
JAVACC_JAR ?= lib/javacc.jar
BUILD      := build
CLASSES    := $(BUILD)/classes
GEN        := $(BUILD)/gen

.PHONY: all test run clean

all: $(CLASSES)/Compilateur.class

$(GEN)/Compilateur.java: Compilateur.jj
	@mkdir -p $(GEN)
	java -cp $(JAVACC_JAR) javacc -OUTPUT_DIRECTORY=$(GEN) Compilateur.jj

$(CLASSES)/Compilateur.class: $(GEN)/Compilateur.java $(wildcard *.java)
	@mkdir -p $(CLASSES)
	javac -nowarn -d $(CLASSES) *.java $(GEN)/*.java

test: all
	./run_tests.sh

run: all
	./mjs $(P)

clean:
	rm -rf $(BUILD)
