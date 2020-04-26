import kotlinx.html.classes
import kotlinx.html.dom.append
import kotlinx.html.img
import kotlinx.html.js.div
import kotlinx.html.p
import org.w3c.dom.HTMLDivElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document

fun main() {
    val content = document.getElementById("content") as HTMLDivElement
    val apiUrl = js("getApiUrl()") as String
    getAsync("$apiUrl/users") { responseText ->
        val users = JSON.parse<Array<User>>(responseText)
        users.forEach {
            content.append {
                div {
                    classes = setOf("card", "card-shadow")
                    p(classes = "text-login") {
                        +it.login
                    }
                    img(classes = "cover-avatar") {
                        src = it.avatar_url
                    }
                }
            }
        }
    }
}

data class User(val login: String, val avatar_url: String)

private fun getAsync(url: String, callback: (String) -> Unit) {
    val xmlHttpRequest = XMLHttpRequest()
    xmlHttpRequest.open("GET", url)
    xmlHttpRequest.onload = {
        if (xmlHttpRequest.status == 200.toShort()) {
            callback.invoke(xmlHttpRequest.responseText)
        }
    }
    xmlHttpRequest.send()
}