package android.ifeanyi.aitrivia.app.data.repository

import android.ifeanyi.aitrivia.app.data.model.TriviaInfoModel
import android.ifeanyi.aitrivia.app.data.model.TriviaModel
import android.ifeanyi.aitrivia.app.domain.TriviaRepository
import android.ifeanyi.aitrivia.core.resourse.Resource
import android.ifeanyi.aitrivia.core.services.ApiService
import java.time.LocalDateTime
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    TriviaRepository {
    override suspend fun generateTrivia(info: TriviaInfoModel): Resource<TriviaModel?> {
        return try {
            val res = apiService.getTrivia(
                amount = info.amount!!,
                category = info.category!!.id,
                difficulty = info.difficulty!!.lowercase(),
                type = info.type!!.id
            )
            Resource.Success(data = TriviaModel(
                questions = res.results,
                createdAt = LocalDateTime.now(),
                triviaInfo = info,
            ))
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}