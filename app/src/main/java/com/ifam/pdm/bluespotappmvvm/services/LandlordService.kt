package com.ifam.pdm.bluespotappmvvm.services

import com.ifam.pdm.bluespotappmvvm.models.dtos.LoginDto
import com.ifam.pdm.bluespotappmvvm.models.dtos.UserCreationDto

class LandlordService(private val retrofitService: RetrofitService) {
    fun createLandlord(userCreationDto: UserCreationDto) = retrofitService
        .createLandlord(userCreationDto)

    fun loginLandlord(loginDto: LoginDto) = retrofitService.loginLandlord(loginDto)
}