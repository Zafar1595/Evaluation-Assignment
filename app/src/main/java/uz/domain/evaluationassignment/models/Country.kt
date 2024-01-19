package uz.domain.evaluationassignment.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Country(
    val id: Int = 0,
    val name: String = "",
    val code: String = "",
    val flag: String = ""
)
