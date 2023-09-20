package com.example.navegacionstreaming.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navegacionstreaming.viewmodel.CostoVM
import com.example.navegacionstreaming.R
import com.example.navegacionstreaming.databinding.FragmentCostoBinding
import com.example.navegacionstreaming.model.Constantes

class CostoFrag : Fragment() {

    //Binding
    private lateinit var binding: FragmentCostoBinding

    private val args: CostoFragArgs by navArgs()

    private val viewModel by viewModels<CostoVM> ()

    private var costoCalculado = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrarObervadores()
        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnConfirmar.setOnClickListener{
            setFragmentResult(Constantes.LLAVE_CALCULAR_COSTO, bundleOf(Constantes.LLAVE_COSTO to costoCalculado))
            findNavController().navigateUp()
        }

        binding.btnCancelar.setOnClickListener{
            setFragmentResult(Constantes.LLAVE_CALCULAR_COSTO, bundleOf(Constantes.LLAVE_COSTO to "Cancelado"))
            findNavController().navigateUp()
        }
    }

    private fun registrarObervadores() {
        //Fragmento -> Se usa viewLifecycleOwner
        viewModel.costo.observe(viewLifecycleOwner){resultado ->
            binding.tvCosto.text = resultado
            costoCalculado = resultado
        }
    }

    override fun onStart() {
        super.onStart()
        println("Tipo de servicio: ${args.tipoServicio}")
        //viewModel.calcularCosto(args.tipoServicio)
    }

}