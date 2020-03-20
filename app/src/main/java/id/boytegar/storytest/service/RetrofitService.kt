package id.boytegar.storytest.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hacker-news.firebaseio.com/v0/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createHttpClient())
        .build()
    fun <S> cteateService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
    fun createHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.readTimeout(60, TimeUnit.SECONDS)
        return client.addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            val request = requestBuilder.method(original.method, original.body).build()
            return@addInterceptor it.proceed(request)
        }.build()
    }
}