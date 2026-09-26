package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GradeItem(
    val id: String,
    val subjectName: String,
    val grade: Double,
    val ects: Int,
    val date: String,
    val instructor: String,
    val type: String
)
