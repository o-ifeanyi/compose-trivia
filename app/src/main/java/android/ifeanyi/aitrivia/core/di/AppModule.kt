package android.ifeanyi.aitrivia.core.di

import android.content.Context
import android.ifeanyi.aitrivia.app.data.repository.TriviaRepositoryImpl
import android.ifeanyi.aitrivia.app.domain.TriviaRepository
import android.ifeanyi.aitrivia.core.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder().baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTriviaRepository(apiService: ApiService): TriviaRepository =
        TriviaRepositoryImpl(apiService)
}