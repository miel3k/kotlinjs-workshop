import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLParagraphElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.dom.addClass

fun main() {
    val content = document.getElementById("content") as HTMLDivElement
    getAsync("https://api.github.com/users") { responseText ->
        val users = JSON.parse<Array<User>>(responseText)
        users.forEach {
            val containerElement = document.createElement("div") as HTMLDivElement
            val loginElement = document.createElement("p") as HTMLParagraphElement
            val avatarElement = document.createElement("img") as HTMLImageElement
            loginElement.textContent = it.login
            avatarElement.src = it.avatar_url
            containerElement.addClass("card", "card-shadow")
            loginElement.addClass("text-login")
            avatarElement.addClass("cover-avatar")
            containerElement.append(loginElement, avatarElement)
            content.appendChild(containerElement)
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