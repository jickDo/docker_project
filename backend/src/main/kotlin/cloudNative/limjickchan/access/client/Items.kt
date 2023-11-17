package cloudNative.limjickchan.access.client
data class NewsResponse(
    val items: List<Item>, // JSON 배열에 맞게 List 형태로 선언
)

data class Item( // 뉴스에 해당하는 json타입을 미리 data class로 정해두면 알아서 파싱을 해준다.
    var title: String?,
    var originallink: String?,
    var link: String?,
    var description: String?,
    var pubDate: String?,
)
