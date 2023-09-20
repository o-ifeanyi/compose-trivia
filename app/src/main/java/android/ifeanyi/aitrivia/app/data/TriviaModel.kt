package android.ifeanyi.aitrivia.app.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class TriviaModel(
    val title: String,
    val questions: List<QuestionModel>,
    @SerializedName(value = "created_at")
    val createdAt: Date?,
    val triviaInfo: TriviaInfoModel?,
)
