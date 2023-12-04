package com.ifam.pdm.bluespotappmvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifam.pdm.bluespotappmvvm.adapters.PropertyAdapter
import com.ifam.pdm.bluespotappmvvm.databinding.ActivityPropertiesBinding
import com.ifam.pdm.bluespotappmvvm.services.PropertyService
import com.ifam.pdm.bluespotappmvvm.services.RetrofitService
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertiesViewModel
import com.ifam.pdm.bluespotappmvvm.viewmodels.PropertiesViewModelFactory


class PropertiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropertiesBinding
    private lateinit var propertiesViewModel: PropertiesViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = PropertyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityPropertiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        propertiesViewModel = ViewModelProvider(
            this@PropertiesActivity, PropertiesViewModelFactory(
                PropertyService(retrofitService)
            )
        )[PropertiesViewModel::class.java]

        binding.propriedadeRv.adapter = adapter
        binding.propriedadeRv.layoutManager = LinearLayoutManager(this@PropertiesActivity)
    }

    override fun onStart() {
        super.onStart()
        propertiesViewModel.propertiesList.observe(this, Observer { properties ->
            adapter.setProperties(properties)
        })

        propertiesViewModel.errorMessage.observe(this, Observer { error ->
            Toast.makeText(this@PropertiesActivity, error, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        propertiesViewModel.findAllProperties()
    }
}