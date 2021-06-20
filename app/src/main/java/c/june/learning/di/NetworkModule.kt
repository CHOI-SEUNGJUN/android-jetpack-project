package c.june.learning.di

import c.june.learning.api.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {

    val baseUrl = "https://api.github.com/"

    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor
        = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
        = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    fun provideGithubService(okHttpClient: OkHttpClient): GithubService
        = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubService::class.java)

    single { provideOkHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideGithubService(get()) }
}