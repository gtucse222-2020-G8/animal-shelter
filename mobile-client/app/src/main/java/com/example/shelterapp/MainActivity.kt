package com.example.shelterapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.content.DialogInterface


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
                val activity2Intent = Intent(applicationContext, MainPageActivity::class.java)
                startActivity(activity2Intent)
            }
            else{
                createAnAlert()
            }

        }
    }

    private fun createAnAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Username or password can not be empty!")
            .setTitle("Warning")

        var alert: AlertDialog ?= null

        builder.setPositiveButton("Ok") { dialog: DialogInterface, id:Int ->
            alert?.hide()
        }
        alert = builder.create()
        alert.show()
    }

    private fun checkInputs(): Boolean{

        val userName = findViewById<EditText>(R.id.userNameField).text.toString()
        val password = findViewById<EditText>(R.id.passwordField).text.toString()
        if(userName.isEmpty() || password.isEmpty()){
            return false
        }
        return true
    }
}
