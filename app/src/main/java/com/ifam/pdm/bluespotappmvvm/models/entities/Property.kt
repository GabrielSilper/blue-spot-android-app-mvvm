package com.ifam.pdm.bluespotappmvvm.models.entities

import com.ifam.pdm.bluespotappmvvm.models.enums.PropertyType

class Property(
    var id: String?,
    var landlordId: String?,
    var images: List<String>,
    var description: String,
    var address: String,
    var price: Double,
    var propertyType: PropertyType,
    var restrictions: MutableList<String> = ArrayList<String>(),
    var furnishings: MutableList<String> = ArrayList<String>(),
    var hasGarage: Boolean,
    var isAvailable: Boolean,
    var isVerified: Boolean
) {
}