#!/usr/bin/env sh

PLUGINS_PATH="../seppuku-client/run/mods/"

if [ ! -d "$PLUGINS_PATH" ] ; then
  echo "make dir '$PLUGINS_PATH'"
  mkdir -p "$PLUGINS_PATH"
fi

for f in */build/devlibs/*.jar; do
	echo "copy '$f' to '$PLUGINS_PATH'"
	cp "$f" "$PLUGINS_PATH"
done
