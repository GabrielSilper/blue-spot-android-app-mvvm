package com.ifam.pdm.bluespotappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityMainBinding
import com.ifam.pdm.bluespotappmvvm.view.MyProfileActivity
import com.ifam.pdm.bluespotappmvvm.view.PropertiesActivity
import com.ifam.pdm.bluespotappmvvm.view.RegisterLandlordActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.btnVerPropriedades.setOnClickListener {
            val propertiesIntent = Intent(this@MainActivity, PropertiesActivity::class.java)
            startActivity(propertiesIntent)
        }

        binding.btnVerPerfil.setOnClickListener {
            val myProfileIntent = Intent(this, MyProfileActivity::class.java)
            startActivity(myProfileIntent)
        }
    }
}