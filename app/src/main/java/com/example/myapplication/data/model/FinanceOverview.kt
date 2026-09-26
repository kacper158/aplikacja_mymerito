package com.example.myapplication.data.model

import kotlinx.serialization.Serializable
import java.util.Locale

@Serializable
enum class InstallmentStatus(val label: String) {
    PENDING("Oczekuje"),
    PLANNED("Zaplanowana"),
    PAID("Opłacona"),
    OVERDUE("Przekroczony termin")
}

@Serializable
enum class PaymentState(val label: String) {
    PENDING("Rata oczekuje na płatność"),
    PAID("Wszystko opłacone"),
    OVERDUE("Zaległość w płatnościach")
}

@Serializable
data class InstallmentItem(
    val id: String,
    val name: String,
    val status: InstallmentStatus,
    val amount: Double,
    val dueDate: String
) {
    val formattedAmount: String
        get() = String.format(Locale.forLanguageTag("pl-PL"), "%.2f zł", amount)
}

@Serializable
data class FinanceOverview(
    val totalToPay: Double,
    val dueDate: String,
    val remainingInstallmentsCount: Int,
    val paymentState: PaymentState,
    val accountNumber: String,
    val installments: List<InstallmentItem>
) {
    val formattedTotalToPay: String
        get() = String.format(Locale.forLanguageTag("pl-PL"), "%.2f zł", totalToPay)
}
