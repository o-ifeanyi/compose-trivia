package android.ifeanyi.aitrivia.app.data.model

import com.google.gson.annotations.SerializedName

enum class Direction { Left, Right }
data class TriviaQuestion(
    val question: String,
    @SerializedName(value = "correct_answer")
    val correctAnswer: String,
    @SerializedName(value = "incorrect_answers")
    val incorrectAnswers: List<String>,
    val index: Int?,
    val zIndex: Int?,
    val direction: Direction?,
    val selectedAnswer: String?,
) {
    val options: List<String>
        get() {
            return incorrectAnswers.plus(correctAnswer).shuffled()
        }
}
