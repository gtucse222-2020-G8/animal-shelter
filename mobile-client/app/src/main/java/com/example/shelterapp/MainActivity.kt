package com.example.shelterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        createListenEvent()
    }

    private fun createListenEvent(){
        val registerButton = findViewById<View>(R.id.register_button)

        registerButton.setOnClickListener {
            val activity2Intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(activity2Intent)
        }

        val loginButton = findViewById<View>(R.id.login_button)
        loginButton.setOnClickListener {
            if(checkInputs()){
                val activity2Intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(activity2Intent)
            }
            else{

            }
        }
    }

    private fun checkInputs(): Boolean{

        //TODO input control and api call for token (write handler class)

        return false
    }
}
