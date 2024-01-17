package uz.domain.evaluationassignment.models

data class Status(
    val id: Int,
    val name: String,
    val textColor: Int,
    val backgroundColor: Int,
    val statusCount: Int,
    val statusColor: Int,
    val statusPercent: Int
)