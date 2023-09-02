package com.example.blood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.blood.databinding.ActivitySignInBinding
import com.example.blood.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    lateinit var signInBinding: ActivitySignInBinding



 val auth = FirebaseAuth.getInstance()


    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        val view = signInBinding.root
        setContentView(view)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        signInBinding.buttonGoback.setOnClickListener {
            val intent = Intent(this@SignIn,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signInBinding.buttonProceed.setOnClickListener {

            val email= signInBinding.edtEmail.text.toString()     //Input
            val password = signInBinding.edtPassword.text.toString()
            signInUser(email,password)

        }

     signInBinding.txtSignup.setOnClickListener {

         val intent =Intent(this@SignIn,SignUp ::class.java)
         startActivity(intent)
         finish()


     }
        signInBinding.forgotPassword.setOnClickListener {

            val intent = Intent(this@SignIn,ForgotPassword::class.java)
            startActivity(intent)
            finish()

        }


    }

    fun signInUser(email : String, password : String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
        if (task.isSuccessful){

            val intent = Intent(this@SignIn,Home::class.java)
            startActivity(intent)
            finish()

        }
        else
        {
            Toast.makeText(applicationContext,task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
        }

        }
    }
}