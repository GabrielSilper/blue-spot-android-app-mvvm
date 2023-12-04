package com.ifam.pdm.bluespotappmvvm.models.dtos

import com.ifam.pdm.bluespotappmvvm.models.enums.PropertyType

data class PropertyCreationDto(
    var address: String,
    var description: String,
    var price: Double,
    var propertyType: PropertyType,
    var restrictions: MutableList<String> = ArrayList<String>(),
    var furnishings: MutableList<String> = ArrayList<String>(),
    var hasGarage: Boolean,
)
