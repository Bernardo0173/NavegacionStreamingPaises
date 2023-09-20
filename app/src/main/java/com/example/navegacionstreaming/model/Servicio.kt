package com.example.navegacionstreaming.model

import com.google.gson.annotations.SerializedName

data class Servicio(
    @SerializedName("population")
    var poblacion: Int,
    @SerializedName("name")
    var nombre: Map<String, Any>,
    @SerializedName("flag")
    var bandera: String
){
    override fun toString(): String {
        return nombre["common"].toString() + bandera
    }
}
