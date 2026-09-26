package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class AffairStatus(val label: String) {
    IN_PROGRESS("W trakcie weryfikacji"),
    APPROVED("Zatwierdzony"),
    REJECTED("Odrzucony"),
    AWAITING_DOCS("Wymaga uzupełnienia")
}

@Serializable
data class AffairItem(
    val id: String,
    val caseNumber: String,
    val category: String,
    val title: String,
    val status: AffairStatus,
    val submissionDate: String,
    val timeAgo: String = "2 godziny temu",
    val description: String? = null
)

@Serializable
data class FaqItem(
    val id: String,
    val category: String,
    val question: String,
    val answer: String
)
