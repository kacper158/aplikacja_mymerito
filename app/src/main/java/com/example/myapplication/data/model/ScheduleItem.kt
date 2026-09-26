package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class ScheduleType(val label: String) {
    WYKLAD("Wykład"),
    LABORATORIUM("Laboratorium"),
    CWICZENIA("Ćwiczenia"),
    PROJEKT("Projekt"),
    SEMINARIUM("Seminarium")
}

@Serializable
enum class AttendanceStatus(val label: String) {
    PRESENT("Obecny"),
    ABSENT("Nieobecny"),
    EXCUSED("Usprawiedliwiony"),
    PENDING("Zaplanowano"),
    CANCELLED("Odwołane")
}

@Serializable
data class ScheduleItem(
    val id: String,
    val title: String,
    val type: ScheduleType,
    val room: String,
    val instructor: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val dayOfWeek: String,
    val attendanceStatus: AttendanceStatus = AttendanceStatus.PENDING
) {
    val timeInterval: String
        get() = "$startTime - $endTime"
}
