package cloudNative.limjickchan.dto

import cloudNative.limjickchan.constants.Job

data class EmailMessageDto(
    val name: String?,
    val emailAddress: String?,
    val companyUrl: String?,
    val address: String?,
    val job: Job?,
    val comment: String?,
)
