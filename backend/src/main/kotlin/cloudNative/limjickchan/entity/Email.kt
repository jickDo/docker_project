package cloudNative.limjickchan.entity

import cloudNative.limjickchan.constants.Job
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val emailAddress: String? = null

    val companyUrl: String? = null

    val address: String? = null

    val job: Job? = null

    val comment: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    val user: User? = null
}
