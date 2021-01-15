package com.example.campusmate.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.campusmate.R
import com.example.campusmate.databinding.RegisterFragmentBinding
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterFragmentBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.nav_registerToLogin)
        }

        binding.submitButton.setOnClickListener { submit() }
        viewModel.gotServerResponse.observe(viewLifecycleOwner ,Observer { onServerResponse() })
    }

    private fun submit() {
        viewModel.setData(binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString(),
                binding.passwordInput2.text.toString())
        viewModel.submitInfo()
        binding.submitButton.isEnabled = false
    }

    private fun onServerResponse() {
        if (viewModel.gotServerResponse.value == true) {
            Log.d("RegisterFrag:Response", viewModel.httpResult)
            val resultJson : Map<String, String> = jacksonObjectMapper().readValue(viewModel.httpResult)
            val result = resultJson["result"]
            if ( result != null && result == "true") {
                Navigation.findNavController(requireView()).navigate(R.id.nav_registerToLogin)
            }
        }
        binding.submitButton.isEnabled = true
    }
}