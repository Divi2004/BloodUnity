package com.example.blood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        val view= mainBinding.root
        setContentView(view)


       mainBinding.buttonSignup.setOnClickListener {
           val intent = Intent(this@MainActivity,SignUp::class.java)
           startActivity(intent)

    }
        mainBinding.buttonSignin.setOnClickListener {
            val intent = Intent(this@MainActivity,SignIn::class.java)
            startActivity(intent)
        }

    }
}