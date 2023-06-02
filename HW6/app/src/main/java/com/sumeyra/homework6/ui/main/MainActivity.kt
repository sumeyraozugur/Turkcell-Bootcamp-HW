package com.sumeyra.homework6.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sumeyra.homework6.configs.ApiClient
import com.sumeyra.homework6.configs.Util
import com.sumeyra.homework6.databinding.ActivityMainBinding
import com.sumeyra.homework6.model.JWTData
import com.sumeyra.homework6.model.JWTUser
import com.sumeyra.homework6.services.DummyService
import com.sumeyra.homework6.ui.home.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var dummyService: DummyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dummyService = ApiClient.getClient().create(DummyService::class.java)
        with(binding) {
            binding.btnLogin.setOnClickListener {
                val emailResult =
                    etUsername.text.toString().trim()
                val passwordResult = etPassword.text.toString().trim()


                if (emailResult != "" && passwordResult != "") {
                    login(emailResult, passwordResult)
                } else {
                    Toast.makeText(this@MainActivity, "Boş Bırakmayın", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun login(emailResult: String, passwordResult: String) {

        val jwtUser = JWTUser(emailResult, passwordResult)
        dummyService.login(jwtUser).enqueue(object : Callback<JWTData> {
            override fun onResponse(call: Call<JWTData>, response: Response<JWTData>) {
                val jwtUser = response.body()
                Log.d("status", response.code().toString())
                if (jwtUser != null) {
                    Util.user = jwtUser
                    Log.d("jwtUser", jwtUser.toString())

                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@MainActivity,"Kullanıcı adınız veya Şifraniz Hatalı",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JWTData>, t: Throwable) {
                Log.e("login", t.toString())
                Toast.makeText(
                    this@MainActivity,
                    "Internet or Server Fail",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }


}
