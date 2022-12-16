package com.example.authaeroplane.ui.register

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authaeroplane.R
import com.example.authaeroplane.databinding.FragmentRegisterBinding
import com.example.authaeroplane.wrapper.Resource
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }


    private fun setOnClickListener() {
        btnReg.setOnClickListener {
            registerUser()
        }
        btnBackLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun registerUser() {
        if (validateInput()) {
            observeData()
            applyRegister()
        }
    }

    private fun registerUser(email: String, password: String) {
        viewModel.postRegisterUser(RegisterRequestBody(email, password))
        Log.d("register", RegisterRequestBody(email, password).toString())
    }

    private fun observeData() {
        viewModel.postRegisterUserResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    if (it.data?.message.equals("User created successfully")) {
                        Toast.makeText(requireContext(), it.data?.message, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                    }
                    Log.d("registerresponse", it.data?.message.toString())
                }
                else -> {}
            }
        }
    }

    private fun applyRegister() {
        val email = EmailReg.text.toString()
        val password = Passwordreg.text.toString()
        btnReg.setOnClickListener {
                registerUser(email, password)
        }
    }




    private fun validateInput(): Boolean {
        var isValid = true
        val email = EmailReg.text.toString().trim()
        val password = Passwordreg.text.toString().trim()


        if (email.isEmpty()) {
            isValid = false
            EmailReg.error = "Email must not be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            EmailReg.error = "Invalid email"
        }
        if (password.isEmpty()) {
            isValid = false
            Toast.makeText(requireContext(), "Password must not be empty", Toast.LENGTH_SHORT)
                .show()
        }
        if (password.length < 6) {
            isValid = false
            Toast.makeText(
                requireContext(),
                "Password should be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

