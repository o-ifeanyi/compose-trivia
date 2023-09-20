package android.ifeanyi.aitrivia.app.data


enum class TriviaType { boolean, multipleChoice }
data class TriviaInfoModel(
    val amount: Int,
    val topic: String,
    val category: String,
    val type: TriviaType,
    val difficulty: String,
)
