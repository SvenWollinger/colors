package io.wollinger.colors

import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement

fun main() {
    val colorsTable = id<HTMLDivElement>("colors")
    val titles = id<HTMLDivElement>("titles")

    document.onfullscreenchange = {
        if(!document.fullscreen) {
            println("Fullscreen off")
            titles.style.visibility = "visible"
            colorsTable.style.visibility = "visible"
            document.body!!.style.backgroundColor = "#1d6d86"
            document.body!!.style.asDynamic().overflow = "auto"
        }
    }
    Color.defaultColors.forEach { color ->
        val div = (document.createElement("div") as HTMLDivElement).apply {
            innerHTML = color.name
            style.backgroundColor = color.cssString
            classList.add("item", "text-border")
        }
        div.onclick = {
            colorsTable.style.visibility = "hidden"
            titles.style.visibility = "hidden"
            document.body!!.style.backgroundColor = color.cssString
            document.body!!.style.asDynamic().overflow = "hidden"
            document.documentElement!!.requestFullscreen()
        }
        colorsTable.append(div)
    }
}