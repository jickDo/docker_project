package cloudNative.limjickchan.access.client

import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange("https://api.mediastack.com/news?access_key=621fecc2003b38d4ba1d76bf9d51bf0c&country=kr")
interface MailAccessClient {
    @PostExchange
    fun getMail(
        @RequestHeader("Authorization") authorization: String,
    ): MailInfoDto
}
