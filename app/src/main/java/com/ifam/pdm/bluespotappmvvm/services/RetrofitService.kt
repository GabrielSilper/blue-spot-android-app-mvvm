package com.ifam.pdm.bluespotappmvvm.services

import com.ifam.pdm.bluespotappmvvm.models.dtos.LoginDto
import com.ifam.pdm.bluespotappmvvm.models.dtos.PropertyCreationDto
import com.ifam.pdm.bluespotappmvvm.models.dtos.UserCreationDto
import com.ifam.pdm.bluespotappmvvm.models.entities.Landlord
import com.ifam.pdm.bluespotappmvvm.models.entities.Property
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {
    @POST("landlords")
    fun createLandlord(@Body userCreationDto: UserCreationDto): Call<Landlord>

    @POST("landlords/{landlordId}/properties")
    fun createProperty(
        @Path(value = "landlordId") landlordId: String,
        @Body propertyCreationDto: PropertyCreationDto
    ): Call<Property>

    @POST("login")
    fun loginLandlord(@Body loginDto: LoginDto): Call<Landlord>

    @GET("properties")
    fun findAllProperties(): Call<List<Property>>

    @GET("landlords/{landlordId}/properties")
    fun findMyProperties(
        @Path(value = "landlordId") landlordId: String,
    ): Call<List<Property>>

    companion object {
        fun getInstance(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://blue-spot-kotlin-api-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RetrofitService::class.java)
        }
    }
}