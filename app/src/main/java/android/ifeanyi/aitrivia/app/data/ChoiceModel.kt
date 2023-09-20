package android.ifeanyi.aitrivia.app.data

import com.google.gson.annotations.SerializedName

data class ChoiceModel(
    @SerializedName(value = "finish_reason")
    val finishReason: String,
    val index: Int,
    val text: String
)
