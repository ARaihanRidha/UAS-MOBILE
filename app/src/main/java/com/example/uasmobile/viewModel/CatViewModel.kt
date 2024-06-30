package com.example.uasmobile.viewmodel

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

    private val _catDetail = MutableStateFlow<Cat?>(null)
    val catDetail: StateFlow<Cat?> = _catDetail

    private val apiKey = "live_ZHh4vOsMy4Qt7vrjNmpPUY7PYidXS8SBLdI5HBvjO3q5KKPixXNJ2Xz0ejDH2j0O"

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

            }
        }
    }


    fun fetchCatDetail(catId: String) {
        viewModelScope.launch {
            val cat = CatApi.retrofitService.getCatDetail(catId, apiKey)
            _catDetail.value = cat
        }
    }
}
