package com.ifam.pdm.bluespotappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifam.pdm.bluespotappmvvm.models.dtos.PropertyCreationDto
import com.ifam.pdm.bluespotappmvvm.models.entities.Property
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyViewModel(private val propertyService: PropertyService): ViewModel() {

    val property = MutableLiveData<Property>()
    val errorMessage = MutableLiveData<String>()

    fun createProperty(
        landlordId: String,
        propertyCreationDto: PropertyCreationDto
    ) {
        val request = propertyService.createProperty(landlordId, propertyCreationDto)
        request.enqueue(object : Callback<Property>{
            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                property.postValue(response.body())
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun findPropertyById(propertyId: String) {
        val request = propertyService.findPropertyById(propertyId)
        request.enqueue(object : Callback<Property>{
            override fun onResponse(call: Call<Property>, response: Response<Property>) {
                property.postValue(response.body())
            }

            override fun onFailure(call: Call<Property>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}