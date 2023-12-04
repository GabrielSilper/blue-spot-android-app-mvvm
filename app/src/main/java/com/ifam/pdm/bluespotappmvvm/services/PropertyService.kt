package com.ifam.pdm.bluespotappmvvm.services

import com.ifam.pdm.bluespotappmvvm.models.dtos.PropertyCreationDto

class PropertyService(private val retrofitService: RetrofitService) {

    fun createProperty(
        landlordId: String,
        propertyCreationDto: PropertyCreationDto
    ) = retrofitService.createProperty(landlordId, propertyCreationDto)

    fun findAllProperties() = retrofitService.findAllProperties()
    fun findPropertyById(propertyId: String) = retrofitService.findPropertyById(propertyId)

    fun findMyProperties(landlordId: String) = retrofitService.findMyProperties(landlordId)
}
