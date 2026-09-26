package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentProfile(
    val firstName: String,
    val lastName: String,
    val albumNumber: String,
    val email: String,
    val fieldOfStudy: String,
    val specialization: String,
    val yearOfStudy: Int,
    val semester: Int,
    val status: String,
    val studyMode: String,
    val degree: String,
    val faculty: String,
    val campus: String,
    val avatarUrl: String? = null,
    val initials: String = "${firstName.take(1)}${lastName.take(1)}"
) {
    val fullName: String
        get() = "$firstName $lastName"
}
