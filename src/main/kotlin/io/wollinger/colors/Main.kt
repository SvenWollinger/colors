package io.wollinger.colors

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement

fun main() {
    val colorsTable = id<HTMLDivElement>("colors")
    val titles = id<HTMLDivElement>("titles")

    fun setColor(color: Color) {
        colorsTable.style.visibility = "hidden"
        titles.style.visibility = "hidden"
        document.body!!.style.backgroundColor = color.cssString
        document.body!!.style.asDynamic().overflow = "hidden"
        if(document.fullscreenEnabled) document.documentElement!!.requestFullscreen()
    }

    fun clearColor() {
        titles.style.visibility = "visible"
        colorsTable.style.visibility = "visible"
        document.body!!.style.backgroundColor = "#1d6d86"
        document.body!!.style.asDynamic().overflow = "auto"
    }

    document.onfullscreenchange = test@ {
        if(document.fullscreen) return@test null
        clearColor()
    }

    getParams().get("rgb")?.let { urlColor ->
        val (r, g, b) = urlColor.split(";").map { it.toInt() }
        id<HTMLInputElement>("ccolor").value = rgbToHex(r, g, b)
    }

    id<HTMLButtonElement>("csubmit").onclick = {
        val color = hexToRgb(id<HTMLInputElement>("ccolor").value)
        window.setSearchParam("rgb", "${color.r};${color.g};${color.b}")
        setColor(color)
    }

    Color.defaultColors.forEach { color ->
        val div = (document.createElement("div") as HTMLDivElement).apply {
            innerHTML = color.name
            style.backgroundColor = color.cssString
            classList.add("item", "text-border")
        }
        div.onclick = { setColor(color) }
        colorsTable.append(div)
    }
}