package cloudNative.limjickchan.configuration

import cloudNative.limjickchan.dto.EmailMessageDto
import org.thymeleaf.context.Context
object ContextGeneratorToMe {
    // 나에게 보내는 Context생성기이다.
    // 따로 빼놓은 이유는 메인로직에 크게 중요하지 않는 부가로직이며, 추후 코드 재사용에서도 유용하기 때문이다.
    fun configureContext(emailMessageDto: EmailMessageDto): Context {
        val context = Context()
        context.setVariable("name", emailMessageDto.name)
        context.setVariable("emailAddress", emailMessageDto.emailAddress)
        context.setVariable("companyUrl", emailMessageDto.companyUrl)
        context.setVariable("address", emailMessageDto.address)
        context.setVariable("Job", emailMessageDto.job?.name)
        context.setVariable("comment", emailMessageDto.comment)
        return context
    }
}
