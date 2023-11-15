package cloudNative.limjickchan.controller

import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.service.EmailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class EmailController(
    private val emailService: EmailService,
) {
    @PostMapping("/request")
    fun sendToMeEmail(@RequestBody userInfo: EmailMessageDto) {
        return emailService.sendEmailToMe(userInfo)
    }
}
