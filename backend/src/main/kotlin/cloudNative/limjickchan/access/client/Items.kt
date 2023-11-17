package cloudNative.limjickchan.access.client

// data class NewsResponse(
//    val items: Items,
// )
//
// data class Items(
//    val title: String?,
//    val originallink: String?,
//    val link: String?,
//    val description: String?,
//    val pubDate: String?,
// )

data class NewsResponse(
    val items: List<Item>, // JSON 배열에 맞게 List 형태로 선언
)

data class Item(
    var title: String?,
    var originallink: String?,
    var link: String?,
    var description: String?,
    var pubDate: String?,
)
