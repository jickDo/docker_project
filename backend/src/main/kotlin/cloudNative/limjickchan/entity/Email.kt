package cloudNative.limjickchan.entity

import cloudNative.limjickchan.constants.Job
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val name: String? = null

    val email: String? = null

    val companyUrl: String? = null

    val address: String? = null

    val job: Job? = null

    val comment: String? = null
}
