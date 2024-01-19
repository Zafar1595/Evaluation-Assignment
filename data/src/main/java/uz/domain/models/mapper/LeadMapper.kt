package uz.domain.models.mapper

import uz.domain.entity.LeadEntity
import uz.domain.models.Lead

fun Lead.toEntity(): LeadEntity {
    return LeadEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        leadIntentionId = leadIntentionId,
        adSource = adSource,
        countryId = countryId,
        languageId = languageId,
        phoneNumber = phoneNumber
    )
}

fun LeadEntity.toDataModel(): Lead {
    return Lead(
        id = id,
        firstName = firstName,
        lastName = lastName,
        leadIntentionId = leadIntentionId,
        adSource = adSource,
        countryId = countryId,
        languageId = languageId,
        phoneNumber = phoneNumber
    )
}


