package com.example.navegacionstreaming.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navegacionstreaming.model.ListaServiciosAPI
import com.example.navegacionstreaming.model.Servicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrincipalVM : ViewModel() {
    //Live data
    val listaServicios = MutableLiveData<List<Servicio>>()

    //El objeto retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")// Servidor remoto
            .addConverterFactory(GsonConverterFactory.create()) //JSON
            .build()
    }

    //Crea el objeto que instancia al objeto que hara la descarga de datos
    private val descargaAPI by lazy{
        retrofit.create(ListaServiciosAPI::class.java)
    }

    //Vista
    fun descargarListaServicios(){
        //Call que es el objeto que descargara los datos
        val call = descargaAPI.descargarListaServicios()
        call.enqueue(object: Callback<List<Servicio>>{
            override fun onResponse(
                call: Call<List<Servicio>>,
                response: Response<List<Servicio>>
            ) {
                if (response.isSuccessful){
                    println("RESPUESTA: ${response.body()}")
                    //Retardo
                    Handler().postDelayed({
                        listaServicios.value = response.body()
                    }, 2000)
                }else{
                    println("FALLA: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<Servicio>>, t: Throwable) {
                println("ERROR: ${t.localizedMessage}")
            }

        })
    }
}