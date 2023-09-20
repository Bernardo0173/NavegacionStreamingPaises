package com.example.navegacionstreaming.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navegacionstreaming.model.CostoServicio
import com.example.navegacionstreaming.model.ListaServiciosAPI
import com.example.navegacionstreaming.model.ServicioStreaming
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//ViewModel de la segunda pantalla
class CostoVM : ViewModel() {
    //Modelo
    private val streaming = ServicioStreaming()

    //Vista
    val costo = MutableLiveData<String>()

    //El objeto retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")// Servidor remoto
            .addConverterFactory(GsonConverterFactory.create()) //JSON
            .build()
    }

    //Crea el objeto que instancia al objeto que hara la descarga de datos
    private val descargaAPI by lazy{
        retrofit.create(ListaServiciosAPI::class.java)
    }
    //Interface
    fun calcularCosto(tipoServicio: String){
     //   val costoServicio = streaming.calcularCosto(tipoServicio)
     //   costo.value = "$tipoServicio $" + "%.2f".format(costoServicio)
        /*
        val call = descargaAPI.descargarCosto(tipoServicio)
        call.enqueue(object: Callback<CostoServicio>{
            override fun onResponse(call: Call<CostoServicio>, response: Response<CostoServicio>) {
                if (response.isSuccessful){
                    val costoServicio = response.body()
                    costo.value = "$tipoServicio $" + "%.2f".format(costoServicio?.costo)
                }else{
                    println("ERROR EN LA RESPUESTA: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<CostoServicio>, t: Throwable) {
                println("ERROR EN LA RED")
            }

        })
        */
    }
}