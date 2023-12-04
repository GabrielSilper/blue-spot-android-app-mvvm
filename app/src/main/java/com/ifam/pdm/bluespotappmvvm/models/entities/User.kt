package com.ifam.pdm.bluespotappmvvm.models.entities

import com.ifam.pdm.bluespotappmvvm.models.enums.CivilState

open class User(
    val id: String?,
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
) {
}