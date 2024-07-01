package com.example.uasmobile.viewModel
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.api.CatApi
import com.example.uasmobile.data.Cat
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CatViewModel : ViewModel() {//bertanggung jawab untuk mengelola data dan logika bisnis terkait kucing.
    private val _catList = MutableStateFlow<List<Cat>>(emptyList())//MutableStateFlow( memungkinkan data untuk bereaksi terhadap perubahan secara otomatis.) yang menyimpan daftar kucing.
    val catList: StateFlow<List<Cat>> = _catList //StateFlow yang mengekspos daftar kucing kepada UI.

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    init { //Blok inisialisasi yang dipanggil saat ViewModel dibuat
        fetchCatImages()
    }

    private fun fetchCatImages() { //Fungsi ini mengambil gambar kucing dari API dan memperbarui `_catList`.
        viewModelScope.launch {
                val response = CatApi.retrofitService.getCatImages(limit = 20) //Memanggil API untuk mengambil 20 gambar kucing.
                _catList.value = response.map {
                    Cat(it.id, it.url, it.width,it.height, it.breeds) //Memperbarui `_catList` dengan data yang diterima dari API.
                }
        }
    }
}
