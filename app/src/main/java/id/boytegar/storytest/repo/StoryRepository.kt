package id.boytegar.storytest.repo

import id.boytegar.storytest.helper.UseCaseResult
import id.boytegar.storytest.model.DetailStory
import id.boytegar.storytest.service.ApiService
import id.boytegar.storytest.service.RetrofitServices
import java.lang.Exception


class StoryRepository{

    val data = RetrofitServices.cteateService(ApiService::class.java)

    suspend fun getListStory(): UseCaseResult<List<Int>>{
        return try {
            val result = data.getListStory().await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

    suspend fun getDetailStory(ids: String): UseCaseResult<DetailStory>{
        return try {
            val result = data.getDetailStory(ids).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}