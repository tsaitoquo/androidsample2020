package jp.quocard.androidsample2020.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import jp.quocard.androidsample2020.domain.GithubRepository
import jp.quocard.androidsample2020.domain.Project
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GithubRepository.instance
    var projectListLiveData: MutableLiveData<List<Project>> = MutableLiveData()
    var errorMessage: MutableLiveData<String> = MutableLiveData("")

    //ViewModel初期化時にロード
    init {
        loadProjectList()
    }

    private fun loadProjectList() {

        //viewModelScope->ViewModel.onCleared() のタイミングでキャンセルされる CoroutineScope
        viewModelScope.launch {
            try {
                val request = repository.getProjectList("tsaitoquo")
                if (request.isSuccessful) {
                    Log.d("565", "load success! ${request.toString()}")
                    //データを取得したら、LiveDataを更新
                    projectListLiveData.postValue(request.body())
                } else {
                    errorMessage.value = request.toString()
                    Log.d("565", "load failed! ${request.toString()}")
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
                Log.e("565", e.message)
            }
        }
    }
}