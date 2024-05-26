package io.wollinger.colors

data class Color(
    val name: String,
    val r: Int,
    val g: Int,
    val b: Int
) {

    val cssString = "rgb($r, $g, $b)"

    companion object {
        val defaultColors = listOf(
            Color("Red", 255, 0, 0),
            Color("Green", 0, 255, 0),
            Color("Blue", 0, 0, 255),
            Color("Yellow", 255, 255, 0),
            Color("Cyan", 0, 255, 255),
            Color("Magenta", 255, 0, 255),
            Color("Black", 0, 0, 0),
            Color("White", 255, 255, 255),
            Color("Gray", 128, 128, 128),
            Color("Maroon", 128, 0, 0),
            Color("Lime", 0, 255, 0),
            Color("Navy", 0, 0, 128),
            Color("Olive", 128, 128, 0),
            Color("Teal", 0, 128, 128),
            Color("Purple", 128, 0, 128),
            Color("Silver", 192, 192, 192),
            Color("Fuchsia", 255, 0, 255),
            Color("Aqua", 0, 255, 255),
            Color("Orange", 255, 165, 0),
            Color("Brown", 165, 42, 42),
            Color("Pink", 255, 192, 203),
            Color("Gold", 255, 215, 0),
            Color("Light Gray", 211, 211, 211),
            Color("Dark Gray", 169, 169, 169),
            Color("Light Blue", 173, 216, 230),
            Color("Dark Blue", 0, 0, 139),
            Color("Light Green", 144, 238, 144),
            Color("Dark Green", 0, 100, 0),
            Color("Light Cyan", 224, 255, 255),
            Color("Dark Cyan", 0, 139, 139),
            Color("Light Pink", 255, 182, 193),
            Color("Dark Red", 139, 0, 0),
            Color("Light Yellow", 255, 255, 224),
            Color("Dark Yellow", 204, 204, 0),
            Color("Beige", 245, 245, 220),
            Color("Indigo", 75, 0, 130),
            Color("Violet", 238, 130, 238),
            Color("Salmon", 250, 128, 114),
            Color("Coral", 255, 127, 80),
            Color("Khaki", 240, 230, 140),
            Color("Crimson", 220, 20, 60),
            Color("Chocolate", 210, 105, 30),
            Color("Plum", 221, 160, 221),
            Color("Orchid", 218, 112, 214),
            Color("Turquoise", 64, 224, 208),
            Color("Tan", 210, 180, 140),
            Color("Wheat", 245, 222, 179)
        )
    }
}

fun rgbToHex(r: Int, g: Int, b: Int): String {
    fun c(c: Int) = with(c.toString(16)) { if(this.length == 1) "0$this" else this }
    return "#${c(r)}${c(g)}${c(b)}"
}