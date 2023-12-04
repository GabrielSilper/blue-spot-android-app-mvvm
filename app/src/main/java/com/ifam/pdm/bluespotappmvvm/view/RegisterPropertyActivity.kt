package com.ifam.pdm.bluespotappmvvm.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifam.pdm.bluespotappmvvm.GlobalClass
import com.ifam.pdm.bluespotappmvvm.MainActivity
import com.ifam.pdm.bluespotappmvvm.adapters.ItemListAdapter
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityRegisterPropertyBinding
import com.ifam.pdm.bluespotappmvvm.models.dtos.PropertyCreationDto
import com.ifam.pdm.bluespotappmvvm.models.enums.PropertyType
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertyViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertyViewModelFactory


class RegisterPropertyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPropertyBinding
    private lateinit var propertyViewModel: PropertyViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapterMobilia = ItemListAdapter()
    private val adapterRestricoes = ItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterPropertyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.binding.recyclerViewMobilia.adapter = adapterMobilia
        this.binding.recyclerViewMobilia.layoutManager = LinearLayoutManager(this)
        this.binding.recyclerViewRestricoes.adapter = adapterRestricoes
        this.binding.recyclerViewRestricoes.layoutManager = LinearLayoutManager(this)
        this.binding.spinnerTipoPropriedade.adapter = ArrayAdapter<PropertyType>(
            this,
            android.R.layout.simple_spinner_item,
            PropertyType.values()
        )

        propertyViewModel = ViewModelProvider(
            this,
            PropertyViewModelFactory(
                PropertyService(retrofitService)
            )
        )[PropertyViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        binding.btnAdicionarMobilia.setOnClickListener {
            val item = binding.edtMobilia.text.toString()
            adapterMobilia.addItem(item)
            binding.edtMobilia.text.clear()

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(
                binding.edtMobilia.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            binding.edtMobilia.clearFocus()
        }

        binding.btnAdicionarRestricoes.setOnClickListener {
            val item = binding.edtRestricoes.text.toString()
            adapterRestricoes.addItem(item)
            binding.edtRestricoes.text.clear()

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(
                binding.edtRestricoes.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            binding.edtRestricoes.clearFocus()
        }

        binding.btnCadastrarPropriedade.setOnClickListener {
            val newProperty = this.propertyFromForm()
            val landlordId = GlobalClass.globalUser?.id as String
            propertyViewModel.createProperty(landlordId, newProperty)
        }

        propertyViewModel.property.observe(this, Observer {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        propertyViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun propertyFromForm(): PropertyCreationDto = PropertyCreationDto(
        address = binding.edtEndereco.text.toString(),
        description = binding.edtDescricao.text.toString(),
        price = binding.edtValor.text.toString().toDouble(),
        hasGarage = binding.switchGaragem.isChecked,
        propertyType = PropertyType.valueOf(binding.spinnerTipoPropriedade.selectedItem.toString()),
        furnishings = adapterMobilia.itemList,
        restrictions = adapterRestricoes.itemList
    )
}