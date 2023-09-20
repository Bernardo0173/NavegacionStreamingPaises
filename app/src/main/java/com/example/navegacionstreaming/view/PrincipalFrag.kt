package com.example.navegacionstreaming.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navegacionstreaming.viewmodel.PrincipalVM
import com.example.navegacionstreaming.R
import com.example.navegacionstreaming.databinding.FragmentPrincipalBinding
//Vista pantalla principal

class PrincipalFrag : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding

    private val viewModel: PrincipalVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_principal, container, false)
        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarEventos()
        registrarObservables()
    }

    private fun registrarObservables() {
        viewModel.listaServicios.observe(viewLifecycleOwner){lista ->
            val arrServicios = lista.toTypedArray()
            binding.spServicios.adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, arrServicios)
            //Apagar el indicador de actividad
            binding.pbDescarga.visibility = View.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.descargarListaServicios()
    }

    private fun registrarEventos() {
        binding.btnContratar.setOnClickListener{
            val tipo = binding.spServicios.selectedItem.toString()
            val action = PrincipalFragDirections.actionPrincipalFragToCostoFrag(tipo)
            findNavController().navigate(action)
            //Registrar LISTENER para recibir un dato de regreso
            setFragmentResultListener("calcularCosto"){requestKey, bundle ->
                //Cuando regresa del segundo fragmento
                val costoCalculado = bundle.getString("costo")
                binding.tvCosto.text = costoCalculado
            }
        }
    }
}