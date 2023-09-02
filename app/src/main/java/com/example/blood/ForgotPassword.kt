package com.example.blood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.blood.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {

    lateinit var forgotPasswordBinding: ActivityForgotPasswordBinding

    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        forgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = forgotPasswordBinding.root
        setContentView(view)


        forgotPasswordBinding.ButtonGoBack.setOnClickListener {
            val intent = Intent(this@ForgotPassword, SignIn::class.java)
            startActivity(intent)
            finish()
        }
            forgotPasswordBinding.buttonProceed.setOnClickListener {
                val email = forgotPasswordBinding.edtEmail.text.toString()

                //function to reset password vai Email
                passwordByEmail(email)

            }

    }

    fun passwordByEmail(email : String ){
        forgotPasswordBinding.buttonProceed.isClickable = false  //Disable button until user enters valid email

        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                //If email is valid then toast button will display and proceed button will enable
                Toast.makeText(applicationContext, "Link has been sent to your Email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ForgotPassword,SignIn::class.java)
                startActivity(intent)

                forgotPasswordBinding.buttonProceed.isClickable = true

            } else {
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        }
    }
}

