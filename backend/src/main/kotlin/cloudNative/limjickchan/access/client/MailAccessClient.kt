package cloudNative.limjickchan.access.client

import cloudNative.limjickchan.constants.SecretNaverConst
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange(SecretNaverConst.NAVER_URL)
interface MailAccessClient {
    @GetExchange
    fun getMail(
        @RequestHeader("X-Naver-Client-Id") clientId: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String,
        @RequestParam("query") query: String,
        @RequestParam("display") display: Int,
        @RequestParam("start") start: Int,
    ): NewsResponse
}
