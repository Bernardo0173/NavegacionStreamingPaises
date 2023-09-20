package com.example.navegacionstreaming.model

//
class ServicioStreaming {
    fun calcularCosto(tipo: String): Double {
        return when(tipo) {
            "Netflix" -> 190.0
            "HBO" -> 160.0
            "Amazon" -> 145.0
            else -> 0.0
        }
    }
}