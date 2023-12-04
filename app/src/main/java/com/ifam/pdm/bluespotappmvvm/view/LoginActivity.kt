package com.ifam.pdm.bluespotappmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifam.pdm.bluespotappmvvm.GlobalClass
import com.ifam.pdm.bluespotappmvvm.MainActivity
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityLoginBinding
import com.ifam.pdm.bluespotappmvvm.models.dtos.LoginDto
import com.ifam.pdm.bluespotappmvvm.services.LandlordService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.LandlordViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.LandlordViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var landlordViewModel: LandlordViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        landlordViewModel = ViewModelProvider(
            this,
            LandlordViewModelFactory(LandlordService(retrofitService))
        )[LandlordViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        binding.btnLogin.setOnClickListener {
            landlordViewModel.loginLandlord(
                LoginDto(
                    email = binding.edtEmail.text.toString(),
                    password = binding.edtSenha.text.toString()
                )
            )
        }

        landlordViewModel.landlord.observe(this, Observer {
            GlobalClass.globalUser = it
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        landlordViewModel.errorMessage.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }
}