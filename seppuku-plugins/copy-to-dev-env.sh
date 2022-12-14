#!/usr/bin/env sh

for f in */build/devlibs/*.jar; do
	echo "cp '$f' -> '../seppuku-client/run/seppuku/plugins/'"
	cp "$f" ../seppuku-client/run/seppuku/plugins/
done