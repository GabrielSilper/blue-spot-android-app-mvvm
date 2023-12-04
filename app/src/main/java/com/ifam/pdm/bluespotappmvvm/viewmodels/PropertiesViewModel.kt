package com.ifam.pdm.bluespotappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifam.pdm.bluespotappmvvm.models.entities.Property
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertiesViewModel(private val propertyService: PropertyService): ViewModel() {

    val propertiesList = MutableLiveData<List<Property>>()
    val errorMessage = MutableLiveData<String>()

    fun findAllProperties() {
        val request = propertyService.findAllProperties()

        request.enqueue(object : Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                propertiesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun findMyProperties(landlordId: String) {
        val request = propertyService.findMyProperties(landlordId)

        request.enqueue(object : Callback<List<Property>> {
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                propertiesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Property>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}