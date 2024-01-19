package uz.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leads_list")
data class Lead(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name")
    var firstName: String = "",
    @ColumnInfo(name = "last_name")
    var lastName: String = "",
    @ColumnInfo(name = "lead_intention")
    var leadIntentionId: Int = 0,
    @ColumnInfo(name = "ad_source")
    var adSource: String = "",
    @ColumnInfo(name = "country")
    var countryId: Int = 0,
    @ColumnInfo(name = "language")
    var languageId: Int = 0,
    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = "",
)
