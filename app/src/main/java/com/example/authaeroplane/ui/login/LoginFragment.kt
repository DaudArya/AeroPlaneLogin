package com.example.authaeroplane.ui.login

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authaeroplane.R
import com.example.authaeroplane.databinding.FragmentLoginBinding
import com.example.authaeroplane.wrapper.Resource
import com.example.fpandc13.models.auth.login.LoginRequestBody
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        setOnClickListener()

    }

    private fun observeData() {
        viewModel.postLoginUserResponse.observe(viewLifecycleOwner) {
            EmailLogin.isEnabled = true
            PasswordLog.isEnabled = true

            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), " ${it.data?.message}", Toast.LENGTH_LONG).show()
                    Log.d("loginResponse", it.data.toString())
                    navigateToHome()
                }
                is Resource.Error -> {
                }
                else -> {}
            }
        }
        viewModel.getUserLoginStatus().observe(viewLifecycleOwner) {
            Log.d("getlogin", it.toString())
            if (it) {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        viewModel.setUserLogin(true)
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
    private fun openRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }


    private fun setOnClickListener() {
        regist.setOnClickListener {
            openRegister()
        }
        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        if (validateInput()) {
            val email = EmailLogin.text.toString().trim()
            val password = PasswordLog.text.toString().trim()

            EmailLogin.isEnabled = false
            PasswordLog.isEnabled = false
            viewModel.postLoginUser(parseFormIntoEntity(email, password))
        }
    }

    private fun parseFormIntoEntity(email: String, password: String): LoginRequestBody {
        return LoginRequestBody(email, password)
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val email = EmailLogin.text.toString().trim()
        val password = PasswordLog.text.toString().trim()

        if (email.isEmpty()) {
            isValid = false
            EmailLogin.error = "Email must not be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValid = false
            EmailLogin.error = "Invalid email"
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