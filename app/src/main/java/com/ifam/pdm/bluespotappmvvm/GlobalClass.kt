package com.ifam.pdm.bluespotappmvvm

import android.app.Application
import com.ifam.pdm.bluespotappmvvm.models.entities.User

class GlobalClass: Application() {
    companion object {
        var globalUser: User? = null
    }
}