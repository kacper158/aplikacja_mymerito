package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class CampusPhotoCategory(val label: String) {
    ALL("Wszystkie"),
    BUILDING("Budynek"),
    AULA("Sala Wykładowa"),
    LIBRARY("Biblioteka"),
    STUDENT_ZONE("Strefa Studenta"),
    LAB("Laboratorium")
}

@Serializable
data class CampusPhoto(
    val id: String,
    val title: String,
    val description: String,
    val category: CampusPhotoCategory,
    val imageUrl: String
)
