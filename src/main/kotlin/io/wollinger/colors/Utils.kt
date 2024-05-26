package io.wollinger.colors

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.w3c.dom.Window
import org.w3c.dom.url.URLSearchParams
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

inline fun <reified T> id(id: String): T {
    val element = document.getElementById(id)
    if(element is T) return element
    throw Exception("\"$id\" is not of type ${T::class.simpleName}!")
}

fun getParams() = URLSearchParams(window.location.search)

fun Window.setSearchParam(key: String, value: String) {
    val params = getParams()
    params.set(key, value)
    window.history.pushState("", "", "?$params")
}

fun Window.removeSearchParam(key: String) {
    val params = getParams()
    params.delete(key)
    window.history.pushState("", "", "?$params")
}

suspend inline fun <reified T> dl(url: String): Deferred<T> {
    val decoded = Json.decodeFromString<T>(download(url).await())
    return Promise { onSuccess, _ -> onSuccess.invoke(decoded) }.asDeferred()
}

fun download(url: String): Deferred<String> {
    return Promise { onSuccess, _ ->
        XMLHttpRequest().apply {
            open("GET", url)
            send()
            onreadystatechange = {
                if(readyState == XMLHttpRequest.DONE && status == 200.toShort())
                    onSuccess.invoke(responseText)
            }
        }
    }.asDeferred()
}

fun Double.toFixed(digits: Int) = asDynamic().toFixed(2)

fun launch(block: suspend CoroutineScope.() -> Unit) {
    MainScope().launch(block = block)
}

fun hexToRgb(hex: String): Color {
    var hexColor = hex.removePrefix("#")

    if (hexColor.length == 3)
        hexColor = hexColor.map { "$it$it" }.joinToString("")

    val intColor = hexColor.toInt(16)

    val r = (intColor shr 16) and 0xFF
    val g = (intColor shr 8) and 0xFF
    val b = intColor and 0xFF

    return Color("custom", r, g, b)
}

fun rgbToHex(r: Int, g: Int, b: Int): String {
    fun c(c: Int) = with(c.toString(16)) { if(this.length == 1) "0$this" else this }
    return "#${c(r)}${c(g)}${c(b)}"
}