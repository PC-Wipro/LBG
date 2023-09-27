package com.lbg.project.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lbg.project.data.NetworkResult
import com.lbg.project.models.CatsDataModel
import com.lbg.project.repo.CatsRepository
import com.lbg.project.utils.ErrorsMessage
import kotlinx.coroutines.launch

class MainViewModel(
    private val catsRepository: CatsRepository
) : ViewModel() {
    private val _catsData = MutableLiveData<List<CatsDataModel>>()
    val catsData: LiveData<List<CatsDataModel>>
        get() = _catsData

    //UI
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getCatsData() {
        viewModelScope.launch {
            catsRepository.fetchCats().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _isLoading.postValue(false)
                        _catsData.postValue(it.data!!)
                    }

                    is NetworkResult.Error -> {
                        _isLoading.postValue(false)
                        _error.postValue(it.message ?: ErrorsMessage.gotApiCallError)
                    }

                    is NetworkResult.Loading -> {
                        _isLoading.postValue(true)
                    }

                }
            }

        }
    }


}