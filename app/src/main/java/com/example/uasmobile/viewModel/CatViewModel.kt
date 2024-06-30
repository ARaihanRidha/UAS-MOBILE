package com.example.uasmobile.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.api.CatApi
import com.example.uasmobile.data.Cat
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CatViewModel : ViewModel() {
    private val _catList = MutableStateFlow<List<Cat>>(emptyList())
    val catList: StateFlow<List<Cat>> = _catList

    init {
        fetchCatImages()
    }

    private fun fetchCatImages() {
        viewModelScope.launch {
            try {
                val response = CatApi.retrofitService.getCatImages(limit = 20)
                _catList.value = response.map {
                    Cat(it.id, it.url, it.width,it.height, it.breeds)
                }
            } catch (e: Exception) {
                //Teks error
            }
        }
    }
}
