package com.example.kursach.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kursach.Network
import com.example.kursach.R
import com.google.gson.JsonElement
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bF1= view.findViewById<Button>(R.id.login_button)
        val bF2= view.findViewById<TextView>(R.id.signup)
        val loginEditText = view.findViewById<EditText>(R.id.login_user)
        val passwordEditText = view.findViewById<EditText>(R.id.login_password)
        val controller = findNavController()

        loginEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && loginEditText.text.toString() == "Login") {
                loginEditText.setText("")
            }
        }
        passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && passwordEditText.text.toString() == "Password") {
                passwordEditText.setText("")
            }
        }
        bF1.setOnClickListener{
            val login = loginEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (login.isEmpty() || password.isEmpty() || login == "Login" || password == "Password") {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = Network.httpClient.request {
                            url(Network.LOGIN_URL)
                            parameter("login", login)
                            parameter("password", password)
                            method = HttpMethod.Get
                            contentType(ContentType.Application.Json)
                        }
                        val data: JsonElement = response.body()
                        if (data.asJsonObject["status"].asBoolean) {
                            controller.navigate(R.id.fragment2)
                        }
                    }catch (err: NoTransformationFoundException){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Неверный логин или пароль",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (err: ServerResponseException){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Ошибка серверва", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

            }
        }
        bF2.setOnClickListener{controller.navigate(R.id.fragment1) }
    }
}