package cloudNative.limjickchan.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Content() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    val title: String = ""

    val author: String = ""

    val date: String = ""

    val content: String = ""

    val imageUrl: String = ""
}
