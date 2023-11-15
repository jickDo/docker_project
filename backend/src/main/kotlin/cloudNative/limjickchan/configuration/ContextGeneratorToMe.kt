package cloudNative.limjickchan.configuration

import cloudNative.limjickchan.dto.EmailMessageDto
import org.thymeleaf.context.Context
object ContextGeneratorToMe {
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
