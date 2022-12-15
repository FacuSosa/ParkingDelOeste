package com.example.trabajonahufacu

import java.time.LocalTime

class Automovil{
    fun calcularRecarga(valorHora: Double, estadia: Int, hsIngreso: LocalTime): Double {
        val costoTotal: Double
        var nuevoValorHora: Double

        return if(hsIngreso.hour in 9..11 || hsIngreso.hour in 18..20){
            nuevoValorHora = valorHora
            nuevoValorHora += valorHora * 0.05
            costoTotal = nuevoValorHora * estadia
            costoTotal
        }else {
            nuevoValorHora = valorHora
            nuevoValorHora += valorHora * 0.03
            costoTotal = nuevoValorHora * estadia
            costoTotal
        }
    }

}