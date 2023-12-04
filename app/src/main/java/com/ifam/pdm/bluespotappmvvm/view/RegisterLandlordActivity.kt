package com.ifam.pdm.bluespotappmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifam.pdm.bluespotappmvvm.GlobalClass
import com.ifam.pdm.bluespotappmvvm.MainActivity
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityRegisterLandlordBinding
import com.ifam.pdm.bluespotappmvvm.models.dtos.UserCreationDto
import com.ifam.pdm.bluespotappmvvm.models.enums.CivilState
import com.ifam.pdm.bluespotappmvvm.services.LandlordService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.LandlordViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.LandlordViewModelFactory
import com.santalu.maskara.Mask
import com.santalu.maskara.MaskChangedListener
import com.santalu.maskara.MaskStyle

class RegisterLandlordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterLandlordBinding
    private lateinit var landlordViewModel: LandlordViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterLandlordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        landlordViewModel = ViewModelProvider(
            this,
            LandlordViewModelFactory(LandlordService(retrofitService))
        )[LandlordViewModel::class.java]

        masks()
    }

    override fun onStart() {
        super.onStart()

        binding.spinnerEstadoCivil.adapter = ArrayAdapter<CivilState>(
            this,
            android.R.layout.simple_spinner_item,
            CivilState.values()
        )

        binding.btnCadastrar.setOnClickListener {
            val user = this.userFromForm()
            landlordViewModel.createLandlord(user)
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

    private fun userFromForm(): UserCreationDto {
        return UserCreationDto(
            name = binding.edtNome.text.toString(),
            email = binding.edtEmail.text.toString(),
            cpf = binding.edtCpf.text.toString(),
            rg = binding.edtRg.text.toString(),
            password = binding.edtSenha.text.toString(),
            address = binding.edtEndereco.text.toString(),
            nationality = binding.edtNacionalidade.text.toString(),
            occupation = binding.edtProfissao.text.toString(),
            phone = binding.edtCelular.text.toString(),
            civilState = CivilState.valueOf(binding.spinnerEstadoCivil.selectedItem.toString())
        )
    }

    private fun masks() {
        val maskCPF = Mask(
            value = "___.___.___-__",
            character = '_',
            style = MaskStyle.COMPLETABLE
        )
        val maskPhone = Mask(
            value = "(__) _____-____",
            character = '_',
            style = MaskStyle.COMPLETABLE
        )
        val listenerCPF = MaskChangedListener(maskCPF)
        val listenerPhone = MaskChangedListener(maskPhone)
        binding.edtCpf.addTextChangedListener(listenerCPF)
        binding.edtCelular.addTextChangedListener(listenerPhone)
    }

}