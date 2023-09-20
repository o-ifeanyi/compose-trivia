package android.ifeanyi.aitrivia.app.data

import com.google.gson.annotations.SerializedName

data class GPTResponseModel(
    val choices: List<ChoiceModel>,
    val created: Int,
    val id: String,
    val model: String,
    @SerializedName(value = "object")
    val gptObject: String,
    val usage: UsageModel
)
