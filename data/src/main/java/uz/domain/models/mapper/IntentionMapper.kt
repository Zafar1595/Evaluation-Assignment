package uz.domain.models.mapper

import uz.domain.entity.IntentionEntity
import uz.domain.models.Intention

fun Intention.toEntity() = IntentionEntity(
    id = id,
    name = name,
    textColor = textColor,
    backgroundColor = backgroundColor,
    statusCount = statusCount,
    statusColor = statusColor,
    statusPercent = statusPercent
)

fun IntentionEntity.toDataModel() = Intention(
    id = id,
    name = name,
    textColor = textColor,
    backgroundColor = backgroundColor,
    statusCount = statusCount,
    statusColor = statusColor,
    statusPercent = statusPercent
)