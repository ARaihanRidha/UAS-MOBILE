package com.example.uasmobile.data
data class Cat(
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val breeds: List<Breed>
)
data class Breed( // menyimpan data Breed
    val name: String = "",
    val origin: String = "",
    val life_span: String = "",
    val description: String = "",
    val wikipedia_url: String? = null
)


