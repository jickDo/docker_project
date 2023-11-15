package cloudNative.limjickchan.dto

import cloudNative.limjickchan.constants.Job
import cloudNative.limjickchan.entity.Email
import com.fasterxml.jackson.annotation.JsonProperty

data class EmailMessageDto(
    val name: String?,
    val emailAddress: String?,
    val companyUrl: String?,
    val address: String?,
    @JsonProperty("Job")
    val job: Job?,
    val comment: String?,
) {
    companion object {
        fun toEmail(emailMessageDto: EmailMessageDto): Email {
            return Email().apply {
                emailAddress = emailMessageDto.emailAddress
                companyUrl = emailMessageDto.companyUrl
                address = emailMessageDto.address
                job = emailMessageDto.job
                comment = emailMessageDto.comment
            }
        }
    }
}
