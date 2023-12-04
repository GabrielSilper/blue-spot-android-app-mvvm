package com.ifam.pdm.bluespotappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifam.pdm.bluespotappmvvm.adapters.ItemListAdapter
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityPropertyBinding
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertyViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertyViewModelFactory

class PropertyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropertyBinding
    private lateinit var propertyViewModel: PropertyViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapterMobilia = ItemListAdapter()
    private val adapterRestrições = ItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        propertyViewModel = ViewModelProvider(
            this,
            PropertyViewModelFactory(
                PropertyService(retrofitService)
            )
        )[PropertyViewModel::class.java]

        binding.recyclerViewMobilia.adapter = adapterMobilia
        binding.recyclerViewMobilia.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRestricoes.adapter = adapterRestrições
        binding.recyclerViewRestricoes.layoutManager = LinearLayoutManager(this)
        binding.root.visibility = View.INVISIBLE
    }

    override fun onStart() {
        super.onStart()
        if (intent.hasExtra("propertyId")){
            val propertyId = intent.getStringExtra("propertyId") as String
            propertyViewModel.findPropertyById(propertyId)
        }

        propertyViewModel.property.observe(this, Observer {
            val valor = "R$ ${it.price}"
            binding.txtValor.text = valor
            binding.txtTipoPropriedade.text = it.propertyType.value.uppercase()
            binding.txtDescricao.text = it.description
            binding.txtEndereco.text = it.address
            isVerified(it.isVerified)
            hasGarage(it.hasGarage)
            adapterRestrições.setList(it.restrictions)
            adapterMobilia.setList(it.furnishings)
            binding.root.visibility = View.VISIBLE
        })
    }

    private fun isVerified(boolean: Boolean) {
        binding.txtVerificado.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
        binding.imgVerificado.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
    }

    private fun hasGarage(boolean: Boolean) {
        binding.txtGaragem.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
        binding.imgGaragem.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
    }
}