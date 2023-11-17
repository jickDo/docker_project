package cloudNative.limjickchan.access.client

import cloudNative.limjickchan.constants.SecretNaverConst
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange(SecretNaverConst.NAVER_URL) // 상수화된 네이버 API서버로 동신한다.
interface MailAccessClient {
    // HTTP Interface의 구현체이다.
    @GetExchange // Get으로 서버에 접근한다.
    fun getMail(
        @RequestHeader("X-Naver-Client-Id") clientId: String, // 헤드에 필요한 값을 담는다.
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String, // 헤드에 필요한 값을 담는다.
        @RequestParam("query") query: String, // 검색 키워드이며, 네이버 api는 query값이 필수이다.
        @RequestParam("display") display: Int, // 기사의 반환 갯수를 말하며, 필수값은 아니다.
        @RequestParam("start") start: Int, // 검색 시작 인덱스를 뜻한다., 필수값은 아니다.
    ): NewsResponse
}
