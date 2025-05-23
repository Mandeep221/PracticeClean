package com.example.core.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL_JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com/"
    private const val BASE_URL_BOOKS = "https://openlibrary.org/"

    @Provides
    fun providesOkhttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @PlaceholderJsonRetrofit
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_JSON_PLACEHOLDER)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @BooksRetrofit
    @Provides
    fun providesBooksRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_BOOKS)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}