package com.ifam.pdm.bluespotappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifam.pdm.bluespotappmvvm.GlobalClass
import com.ifam.pdm.bluespotappmvvm.adapters.PropertyAdapter
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityMyPropertiesBinding
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertiesViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertiesViewModelFactory

class MyPropertiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPropertiesBinding
    private lateinit var propertiesViewModel: PropertiesViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = PropertyAdapter {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPropertiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        propertiesViewModel = ViewModelProvider(
            this, PropertiesViewModelFactory(
                PropertyService(retrofitService)
            )
        )[PropertiesViewModel::class.java]

        val landlordName = GlobalClass.globalUser?.name
        val activityTitle = "Propriedades de $landlordName"
        binding.txtAutorPropriedade.text = activityTitle
        binding.recyclerViewMyProperties.adapter = adapter
        binding.recyclerViewMyProperties.layoutManager = LinearLayoutManager(this)

    }

    override fun onStart() {
        super.onStart()
        propertiesViewModel.propertiesList.observe(this, Observer { properties ->
            adapter.setProperties(properties)
        })

        propertiesViewModel.errorMessage.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        val landlordId = GlobalClass.globalUser?.id as String
        propertiesViewModel.findMyProperties(landlordId)
    }
}