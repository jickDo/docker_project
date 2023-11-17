package cloudNative.limjickchan.controller

import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.service.EmailService
import cloudNative.limjickchan.service.NewsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class EmailController(
    private val emailService: EmailService,
    private val newsService: NewsService,
) {
    @PostMapping("/request") // userInfo를 프론트 단에서 받아서 나에게 그정보를 메일로 전송하는 api이다.
    fun sendToMeEmail(@RequestBody userInfo: EmailMessageDto) {
        return emailService.sendEmailToMe(userInfo)
    }

    @PostMapping("/send") // email을 받아서 네이버 뉴스 api에서 받아온 가천대와 관련된 랜덤기사를 보내준다.
    fun sendNewsToUser(@RequestParam email: String) {
        return newsService.sendNewsToUser(email)
    }
}
