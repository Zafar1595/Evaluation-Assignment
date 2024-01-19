package uz.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intention_list")
data class Intention(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "text_color")
    val textColor: Int = 0,
    @ColumnInfo(name = "background_color")
    val backgroundColor: Int = 0,
    @ColumnInfo(name = "status_count")
    val statusCount: Int = 0,
    @ColumnInfo(name = "status_color")
    val statusColor: Int = 0,
    @ColumnInfo(name = "status_percent")
    val statusPercent: Int = 0
)