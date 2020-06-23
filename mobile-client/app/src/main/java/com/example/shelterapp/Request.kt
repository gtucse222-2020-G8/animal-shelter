package com.example.shelterapp

class Request {
    private var id: Int = 0
    private var name: String = ""
    private var status: Int = 0

    fun setId(id: Int){
        this.id = id
    }
    fun getId(): Int {
        return id
    }

    fun setName(name: String){
        this.name = name
    }
    fun getName(): String {
        return name
    }

    fun setStatus(status: Int){
        this.status = status
    }
    fun getStatus(): Int {
        return status
    }

}
