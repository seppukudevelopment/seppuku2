/*
 *     Copyright (C) 2022  Seppuku Development
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package pw.seppuku.client.ecs.components.minecraft.client.hud.gui

import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.util.math.MatrixStack

typealias InGameHudRenderCallback = InGameHud.(matrices: MatrixStack, tickDelta: Float) -> Unit

data class InGameHudRender(val callback: InGameHudRenderCallback)
