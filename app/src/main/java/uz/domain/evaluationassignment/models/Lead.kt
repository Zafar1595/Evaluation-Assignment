package uz.domain.evaluationassignment.models

data class Lead(
    val id: Int,
    val fullName: String,
    val flag: Int,
    val status: Status,
    val avatar: Int
)
