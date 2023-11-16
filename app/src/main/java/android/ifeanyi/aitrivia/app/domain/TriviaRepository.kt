package android.ifeanyi.aitrivia.app.domain

import android.ifeanyi.aitrivia.app.data.model.TriviaInfoModel

import android.ifeanyi.aitrivia.app.data.model.TriviaModel
import android.ifeanyi.aitrivia.core.resourse.Resource


interface TriviaRepository {
    suspend fun generateTrivia(info: TriviaInfoModel): Resource<TriviaModel?>
}