#!/usr/bin/env sh
# Compile a mini-JS program and run it on the JSM virtual machine.
#   ./mjs programme.js [--trace] [--vars]
set -e
DIR=$(cd "$(dirname "$0")" && pwd)
SRC="$1"; shift
java -cp "$DIR/build/classes" Compilateur "$SRC" > /dev/null
java -cp "$DIR/build/classes" JSMachine "${SRC%.*}.jsm" "$@"
