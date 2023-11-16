package android.ifeanyi.aitrivia.core.services

import android.ifeanyi.aitrivia.core.resourse.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET(value = "/api.php")
    suspend fun getTrivia(
        @Query(value = "amount") amount: String,
        @Query(value = "category") category: Int,
        @Query(value = "difficulty") difficulty: String,
        @Query(value = "type") type: String
    ): TriviaResponse
}