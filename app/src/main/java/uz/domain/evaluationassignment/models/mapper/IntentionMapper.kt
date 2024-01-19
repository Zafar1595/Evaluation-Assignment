package uz.domain.evaluationassignment.models.mapper

import uz.domain.entity.IntentionEntity
import uz.domain.evaluationassignment.models.Intention

fun Intention.toEntity(): IntentionEntity {
    return IntentionEntity(
        id = id,
        name = name,
        textColor = textColor,
        backgroundColor = backgroundColor,
        statusCount = statusCount,
        statusColor = statusColor,
        statusPercent = statusPercent
    )
}

fun IntentionEntity.toAppModel(): Intention {
    return Intention(
        id = id,
        name = name,
        textColor = textColor,
        backgroundColor = backgroundColor,
        statusCount = statusCount,
        statusColor = statusColor,
        statusPercent = statusPercent
    )
}