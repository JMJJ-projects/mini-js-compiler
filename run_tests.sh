#!/usr/bin/env sh
# Compiles each examples/*.js, runs it on the VM and diffs the output with examples/*.expected
DIR=$(cd "$(dirname "$0")" && pwd)
pass=0; fail=0
for src in "$DIR"/examples/*.js; do
  name=$(basename "$src" .js)
  expected="${src%.js}.expected"
  actual=$("$DIR/mjs" "$src" --vars 2>&1)
  if [ "$actual" = "$(cat "$expected")" ]; then
    pass=$((pass+1)); echo "  ok    $name"
  else
    fail=$((fail+1)); echo "  FAIL  $name"; echo "$actual" | diff "$expected" - | sed 's/^/        /'
  fi
done
echo "$pass passed, $fail failed"
[ "$fail" -eq 0 ]
