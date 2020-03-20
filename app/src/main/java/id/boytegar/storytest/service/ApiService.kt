package id.boytegar.storytest.service

import id.boytegar.storytest.model.DetailStory
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("topstories.json?print=pretty")
    fun getListStory(): Deferred<List<Int>>
    @GET("item/{ids}.json?print=pretty")
    fun getDetailStory(@Path("ids") ids: String): Deferred<DetailStory>
}