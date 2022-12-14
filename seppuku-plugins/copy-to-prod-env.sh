#!/usr/bin/env sh

for f in */build/libs/*.jar; do
	echo "cp '$f' -> '~/.local/share/PrismLauncher/instances/1.19.3-fabric/.minecraft/seppuku/plugins/'"
	cp "$f" ~/.local/share/PrismLauncher/instances/1.19.3-fabric/.minecraft/seppuku/plugins/
done