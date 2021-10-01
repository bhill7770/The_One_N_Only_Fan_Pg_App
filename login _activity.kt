package com.example.the_one_n_only

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private const val TAG ="loginActivity"

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val auth = FirebaseAuth.getInstance()
                if(auth.currentUser !=null){
                    goPostsActivity()
                }

        btnLogin.setOnClickListener {
            btnLogin.isEnabled=false
            val email = etEmail.text.toString()
            val password = etPassword.toString()
            if (email.isBlank() || password.isBlank()){
                Toast.makeText( this, "Email/Password Cannot Be Empty", Toast.LENGTH_SHORT).show()
           return@setOnClickListener
            }
            //Firebase authentication check
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                btnLogin.isEnabled=true

                if (task.isSuccessful) {
                    Toast.makeText(this, "Successful Login!", Toast.LENGTH_SHORT).show()
                    goPostsActivity()
                }else{
                    Log.e(TAG, "Sign In with Email Failed", task.exception)
                    Toast.makeText(this, "Authentication Failed Message", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
    private fun goPostsActivity(){
        Log.i(TAG, "goPostActivity")
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
        finish()
    }    }



