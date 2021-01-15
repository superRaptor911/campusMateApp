package com.example.campusmate.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campusmate.httpNetwork.HttpNetwork
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class LoginViewModel : ViewModel() {

    private var _gotServerResponse = MutableLiveData<Boolean>(false)
    val gotServerResponse : LiveData<Boolean> get() = _gotServerResponse
    var httpResult : String = ""

    var userName : String = ""
    var password : String = ""

    private  val requestCallback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Login::HttpCallback", e.toString())
            httpResult = "{}"
            _gotServerResponse.postValue(true)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val result = response.body()?.string()
                Log.d("Login:", result!!)
                httpResult = result
            } else {
                httpResult = "{}"
            }
            _gotServerResponse.postValue(true)
        }
    }

    fun submitInfo() {
        if (userName == "" || password == "") {
            return
        }

        val httpRequest = HttpNetwork()
        val request = httpRequest.createRequest("http://cucekmate.ml/php/API/login_api.php", mapOf("fun_name" to "fun_login",
                "arg_c" to "2","arg_0" to userName,"arg_1" to password))
        httpRequest.callRequest(request, requestCallback)
    }

    fun setData(name : String, p1 : String) {
        userName= name
        password = p1
    }
}