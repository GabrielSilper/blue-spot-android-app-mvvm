package com.ifam.pdm.bluespotappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ifam.pdm.bluespotappmvvm.models.dtos.LoginDto
import com.ifam.pdm.bluespotappmvvm.models.dtos.UserCreationDto
import com.ifam.pdm.bluespotappmvvm.models.entities.Landlord
import com.ifam.pdm.bluespotappmvvm.services.LandlordService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LandlordViewModel(private val landlordService: LandlordService) : ViewModel() {

    val landlord = MutableLiveData<Landlord>()
    val errorMessage = MutableLiveData<String>()

    fun createLandlord(userCreationDto: UserCreationDto) {
        val request = landlordService.createLandlord(userCreationDto)
        request.enqueue(object : Callback<Landlord> {
            override fun onResponse(call: Call<Landlord>, response: Response<Landlord>) {
                landlord.postValue(response.body())
            }

            override fun onFailure(call: Call<Landlord>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun loginLandlord(loginDto: LoginDto) {
        val request = landlordService.loginLandlord(loginDto)
        request.enqueue(object : Callback<Landlord> {
            override fun onResponse(call: Call<Landlord>, response: Response<Landlord>) {
                if (response.code() != 200) {
                    errorMessage.postValue("Algo de errado! Verifique as informações")
                } else {
                    landlord.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Landlord>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}