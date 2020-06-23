package com.example.shelterapp

class Animal {

    private var id: Int = 0
    private var name: String = ""
    private var kind: String = ""
    private var gender: Char = ' '
    private var age: Int = 0
    private var vaccination: String = ""
    private var adopted: Boolean = false
    private var info: String = ""
    private var image: String = ""
    private var shelterDate: String = ""

    fun setId(id: Int){
        this.id = id
    }
    fun getId(): Int{
        return id
    }

    fun setName(name: String){
        this.name = name
    }
    fun getName(): String{
        return name
    }

    fun setKind(kind: String){
        this.kind = kind
    }
    fun getKind(): String{
        return kind
    }

    fun setGender(gender: Char){
        this.gender = gender
    }
    fun getGender(): Char{
        return gender
    }

    fun setAge(age: Int){
        this.age = age
    }
    fun getAge(): Int{
        return age
    }

    fun setVaccination(vaccination: String){
        this.vaccination = vaccination
    }
    fun getVaccination(): String{
        return vaccination
    }

    fun setAdopted(adopted: Boolean){
        this.adopted = adopted
    }
    fun getAdopted(): Boolean{
        return adopted
    }

    fun setInfo(info: String){
        this.info = info
    }
    fun getInfo(): String{
        return info
    }

    fun setImage(image: String){
        this.image = image
    }
    fun getImage(): String{
        return image
    }

    fun setShelterDate(){
        this.shelterDate = shelterDate
    }
    fun getShelterDate(): String{
        return shelterDate
    }



}