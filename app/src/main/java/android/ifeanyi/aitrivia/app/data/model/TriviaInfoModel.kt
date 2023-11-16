package android.ifeanyi.aitrivia.app.data.model

data class TriviaInfoModel(
    val amount: String? = null,
    val category: TriviaCategory? = null,
    val type: TriviaType? = null,
    val difficulty: String? = null,
) {
    fun validate(): Boolean {
        return amount != null && category != null && type != null && difficulty != null
    }
}
