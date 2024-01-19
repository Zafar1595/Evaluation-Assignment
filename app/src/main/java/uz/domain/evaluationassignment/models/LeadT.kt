package uz.domain.evaluationassignment.models

data class LeadT(
    val id: Int,
    val fullName: String,
    val flag: Int,
    val status: Status,
    val avatar: Int
)
