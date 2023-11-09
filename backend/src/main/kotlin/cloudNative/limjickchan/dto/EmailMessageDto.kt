package cloudNative.limjickchan.dto

data class EmailMessageDto(
    val receiver: String,
    val subject: String,
    val message: String,
)
