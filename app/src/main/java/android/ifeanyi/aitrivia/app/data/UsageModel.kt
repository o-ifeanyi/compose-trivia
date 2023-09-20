package android.ifeanyi.aitrivia.app.data

import com.google.gson.annotations.SerializedName

data class UsageModel(
    @SerializedName(value = "completion_tokens")
    val completionTokens: Int,
    @SerializedName(value = "prompt_tokens")
    val promptTokens: Int,
    @SerializedName(value = "total_tokens")
    val totalTokens: Int
)
