package com.example.navegacionstreaming.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ListaServiciosAPI {
    @GET("all") //Servicio, ruta, endpoint, path
    fun descargarListaServicios(): Call<List<Servicio>>
}