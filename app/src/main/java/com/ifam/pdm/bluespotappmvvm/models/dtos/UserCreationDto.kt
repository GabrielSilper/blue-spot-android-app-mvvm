package com.ifam.pdm.bluespotappmvvm.models.dtos

import com.ifam.pdm.bluespotappmvvm.models.enums.CivilState

data class UserCreationDto(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val nationality: String,
    val civilState: CivilState,
    val occupation: String,
    val rg: String,
    val cpf: String,
    val address: String
)