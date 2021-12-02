package com.example.lab8

data class Animal(var name: String=""){
    fun setAnimal(position:Int){
        setAnimalName(position)
    }

    private fun setAnimalName(position:Int){
        when (position) {
            0 -> name="Dog"
            1 -> name="Cat"
            2 -> name="Fish"
        }
    }

}
