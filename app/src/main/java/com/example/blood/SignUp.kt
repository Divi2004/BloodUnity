package com.example.blood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Toast
import com.example.blood.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    lateinit var signUpBinding: ActivitySignUpBinding

    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding= ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        signUpBinding.txtSignin.setOnClickListener {
            val intent = Intent(this@SignUp,SignIn::class.java)
            startActivity(intent)
            finish()

        }

        signUpBinding.buttonProceed.setOnClickListener {


            val email = signUpBinding.edtEmail.text.toString()
            val password = signUpBinding.edtPassword.text.toString()


            signUpWithFirebase(email , password)

        }

        signUpBinding.buttonGoback.setOnClickListener {
            val intent = Intent(this@SignUp,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun signUpWithFirebase(email : String , password : String) {


        signUpBinding.buttonProceed.isClickable = false

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if(task.isSuccessful) {

                Toast.makeText(applicationContext, "your account has been created", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUp,SignIn::class.java)
                startActivity(intent)

                signUpBinding.buttonProceed.isClickable = true

            } else {
                Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        }

    }
}