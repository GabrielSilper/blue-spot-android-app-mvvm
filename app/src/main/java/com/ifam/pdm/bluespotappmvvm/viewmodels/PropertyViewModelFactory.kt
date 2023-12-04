package com.ifam.pdm.bluespotappmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import java.lang.IllegalArgumentException

class PropertyViewModelFactory(private val propertyService: PropertyService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PropertyViewModel::class.java)) {
            PropertyViewModel(this.propertyService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}