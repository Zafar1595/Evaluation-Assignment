package uz.domain.evaluationassignment.models

data class Lead(
    val id: Int = 0,
    var firstName: String = "",
    var lastName: String = "",
    var leadIntentionId: Int = 0,
    var adSource: String = "",
    var countryId: Int = 0,
    var languageId: Int = 0,
    var phoneNumber: String = "",
)