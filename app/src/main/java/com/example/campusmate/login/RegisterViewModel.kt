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

class RegisterViewModel : ViewModel() {

    private var _userName = MutableLiveData<String>("")
    val userName : LiveData<String> get() = _userName

    private var _gotServerResponse = MutableLiveData<Boolean>(false)
    val gotServerResponse : LiveData<Boolean> get() = _gotServerResponse
    var httpResult : String = ""

    private var pass1 : String = ""
    private var pass2 : String = ""

    private  val requestCallback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("Register::HttpCallback", e.toString())
            httpResult = "{}"
            _gotServerResponse.postValue(true)
        }

        override fun onResponse(call: Call, response: Response) {
           if (response.isSuccessful) {
               val result = response.body()?.string()
               Log.d("Register:", result!!)
               httpResult = result
           } else {
           }
           _gotServerResponse.postValue(true)
        }
    }

    fun setData(name : String, p1 : String, p2 : String) {
        _userName.value = name
        pass1 = p1
        pass2 = p2
    }

    fun submitInfo() {
        if (pass1 == "") {
            return
        }
        if (pass1 != pass2) {
            return
        }

        val httpRequest = HttpNetwork()
        val request = httpRequest.createRequest("http://cucekmate.ml/php/API/login_api.php", mapOf("fun_name" to "fun_register",
                "arg_c" to "2","arg_0" to userName.value!!,"arg_1" to pass1))

        httpRequest.callRequest(request, requestCallback)
    }

}