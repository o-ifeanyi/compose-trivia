package android.ifeanyi.aitrivia.app.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class TriviaModel(
    val questions: List<TriviaQuestion>,
    @SerializedName(value = "created_at")
    val createdAt: LocalDateTime?,
    val triviaInfo: TriviaInfoModel?,
)
