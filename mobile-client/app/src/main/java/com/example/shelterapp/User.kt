package com.example.shelterapp

class User {
    private var userName: String = ""
    private var surname: String = ""
    private var phoneNumber: Int = 0
    private var email: String = ""

    fun setUserName(username: String){
        this.userName = userName
    }
    fun getUserName(): String{
        return userName
    }

    fun setSurname(username: String){
        this.surname = surname
    }
    fun getSurname(): String{
        return surname
    }

    fun setPhoneNumber(phoneNumber: Int){
        this.phoneNumber = phoneNumber
    }
    fun getPhoneNumber(): Int{
        return phoneNumber
    }

    fun setEmail(email: String){
        this.email = email
    }
    fun getEmail(): String{
        return email
    }

}