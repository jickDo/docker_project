package cloudNative.limjickchan.controller

import cloudNative.limjickchan.dto.EmailMessageDto
import cloudNative.limjickchan.dto.EmailRequestDto
import cloudNative.limjickchan.service.EmailService
import cloudNative.limjickchan.service.NewsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "email", description = "Email 컨트롤러")
@RequestMapping("/mail")
class EmailController(
    private val emailService: EmailService,
    private val newsService: NewsService,
) {
    @Operation(summary = "나에게 메일보내기", description = "사용자가 입력하는 정보를 등록된 나의 이메일로 보내는 API")
    @PostMapping("/request") // userInfo를 프론트 단에서 받아서 나에게 그정보를 메일로 전송하는 api이다.
    fun sendToMeEmail(@RequestBody userInfo: EmailMessageDto) {
        return emailService.sendEmailToMe(userInfo)
    }

    @Operation(summary = "뉴스 전송", description = "가천대와 관련된 랜덤한 기사를 보내준다.")
    @PostMapping("/send") // email을 받아서 네이버 뉴스 api에서 받아온 가천대와 관련된 랜덤기사를 보내준다.
    fun sendNewsToUser(@RequestBody emailRequestDto: EmailRequestDto) {
        return newsService.sendNewsToUser(emailRequestDto.email)
    }

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }
}
