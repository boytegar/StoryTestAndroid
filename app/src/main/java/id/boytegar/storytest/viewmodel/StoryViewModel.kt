package id.boytegar.storytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.boytegar.storytest.helper.UseCaseResult
import id.boytegar.storytest.model.DetailStory
import id.boytegar.storytest.repo.StoryRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class StoryViewModel : ViewModel(), CoroutineScope{
    val repository = StoryRepository()
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val dataStory = MutableLiveData<List<DetailStory>>()
    val list = MutableLiveData<List<Int>>()
    val showError = MutableLiveData<String>()
    val list_data = ArrayList<DetailStory>()
    var size_list = MutableLiveData<Int>()

     init {
        launch {
            val result = withContext(Dispatchers.IO) { repository.getListStory() }

            when (result) {
                is UseCaseResult.Success -> {
                    size_list.value = result.data.size
                    result.data.forEach {
                        getDetailStory(it.toString())
                    }
                }
                is UseCaseResult.Error -> showError.value = result.exception.message
            }
        }
    }

     fun getDetailStory(ids: String) {
         async {
             val result  = withContext(Dispatchers.IO) { repository.getDetailStory(ids) }
             when(result){
                 is UseCaseResult.Success -> {
                     list_data.add(result.data)
                     dataStory.value = list_data
                 }
                 is UseCaseResult.Error -> showError.value = result.exception.message
             }
         }

    }

    fun getComment(list: List<Int?>){
        list.forEach {
            getDetailStory(it.toString())
        }
    }

}