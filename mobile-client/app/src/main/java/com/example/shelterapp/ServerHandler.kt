package com.example.shelterapp

class ServerHandler {

    fun login(user: User): String{
        return ""
    }

    fun register(user: User): String{
        return ""
    }

    fun createAnimalRequest(name: String): List<Animal>{
        return listOf()
    }

    fun getOwnagesRequests(name: String): List<Request>{
        return listOf()
    }

    fun getOwnagesAnimals(): List<Animal> {
        return listOf()
    }
    fun getFilteredAnimals(filter: Filter): List<Animal>{
        return listOf()
    }

    fun updateUserInfo(user: User): Boolean{
        return false
    }
    fun favoriteAnimals(user: User): List<Animal>{
        return listOf()
    }
}