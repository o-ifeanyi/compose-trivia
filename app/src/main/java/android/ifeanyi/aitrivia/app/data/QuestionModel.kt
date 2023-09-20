package android.ifeanyi.aitrivia.app.data

import com.google.gson.annotations.SerializedName

data class QuestionModel(
    val test: String,
    val answer: String,
    val options:List<String>,
    @SerializedName(value = "selected_answer")
    val selectedAnswer: String?,
)
