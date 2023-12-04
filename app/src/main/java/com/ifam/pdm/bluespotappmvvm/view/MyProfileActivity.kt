package com.ifam.pdm.bluespotappmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ifam.pdm.bluespotappmvvm.GlobalClass
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityMyProfileBinding
import com.ifam.pdm.bluespotappmvvm.viewmodels.GlobalViewModel


class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var globalViewModel: GlobalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        globalViewModel = GlobalViewModel()
    }

    override fun onStart() {
        super.onStart()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnCadastrarLocador.setOnClickListener {
            val intent = Intent(this, RegisterLandlordActivity::class.java)
            startActivity(intent)
        }

        binding.btnCadastrarPropriedade.setOnClickListener {
            val intent = Intent(this, RegisterPropertyActivity::class.java)
            startActivity(intent)
        }

        binding.btnVerMinhasPropriedades.setOnClickListener {
            val intent = Intent(this, MyPropertiesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        if (GlobalClass.globalUser == null) {
            binding.tvId.visibility = View.INVISIBLE
            binding.tvNome.visibility = View.INVISIBLE
            binding.tvEmail.visibility = View.INVISIBLE
            binding.btnVerMinhasPropriedades.visibility = View.INVISIBLE
            binding.btnCadastrarPropriedade.visibility = View.INVISIBLE
        } else {
            binding.tvFacaLogin.visibility = View.INVISIBLE
            binding.btnLogin.visibility = View.INVISIBLE
            binding.btnCadastrarLocador.visibility = View.INVISIBLE
            val idText = "ID: ${globalViewModel.user?.id}"
            val nameText = "Nome: ${globalViewModel.user?.name}"
            val emailText = "Email: ${globalViewModel.user?.email}"
            binding.tvId.text = idText
            binding.tvNome.text = nameText
            binding.tvEmail.text = emailText
        }
    }
}