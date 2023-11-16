package android.ifeanyi.aitrivia.app.data.model

data class TriviaCategory(val id: Int, val value: String) {
    override fun toString(): String {
        return value
    }
}