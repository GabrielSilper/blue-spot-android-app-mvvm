package com.ifam.pdm.bluespotappmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifam.pdm.bluespotappmvvm.services.LandlordService
import java.lang.IllegalArgumentException

class LandlordViewModelFactory(private val landlordService: LandlordService): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LandlordViewModel::class.java)) {
            LandlordViewModel(this.landlordService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}