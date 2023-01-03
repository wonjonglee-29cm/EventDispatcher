package com.wonjong.eventdispatcher.data.remote.client

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by leewonjong@29cm.co.kr on 2023-01-03
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkClient {

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        httpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().run {
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        addInterceptor(HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor(provideRequest())
        addInterceptor(provideResponse())
        build()
    }

    @Provides
    @Singleton
    fun provideRequest(): Interceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().apply {
            header("Content-Type", "application/json")
            header("Accept", "application/json")
            method(chain.request().method, chain.request().body)
        }.build())
    }

    @Provides
    @Singleton
    fun provideResponse(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        return@Interceptor chain.proceed(request)
    }
}

private const val TIMEOUT = 30L // 타임아웃 30초