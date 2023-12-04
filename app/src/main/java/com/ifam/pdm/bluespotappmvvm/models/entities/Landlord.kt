package com.ifam.pdm.bluespotappmvvm.models.entities

import com.ifam.pdm.bluespotappmvvm.models.enums.CivilState

class Landlord(
    id: String?,
    name: String,
    email: String,
    password: String,
    phone: String,
    nationality: String,
    civilState: CivilState,
    occupation: String,
    rg: String,
    cpf: String,
    address: String,
    val properties: MutableList<Property> = ArrayList<Property>()
) : User(id, name, email, password, phone, nationality, civilState, occupation, rg, cpf, address) {

}