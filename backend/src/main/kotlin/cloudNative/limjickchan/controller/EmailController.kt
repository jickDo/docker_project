package cloudNative.limjickchan.controller

import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.service.EmailService
import cloudNative.limjickchan.service.NewsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class EmailController(
    private val emailService: EmailService,
    private val newsService: NewsService,
) {
    @PostMapping("/request")
    fun sendToMeEmail(@RequestBody userInfo: EmailMessageDto) {
        return emailService.sendEmailToMe(userInfo)
    }

    @PostMapping("/send")
    fun sendNewsToUser(@RequestParam email: String) {
        return newsService.sendNewsToUser(email)
    }
}
