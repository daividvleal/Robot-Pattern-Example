package br.imaginefree.robotpattern

class PasswordValidator (){

    private val regexList = listOf(
        Regex(".{8,}"), // Tamanho maior ou igual a 8
        Regex("\\d+"), // Validar Números
        Regex("[a-z]"), // Letra Minúscula
        Regex("[A-Z]"), // Letra Maiúsculas
        Regex("[^a-z0-9]", RegexOption.IGNORE_CASE) // Caracter Especial
    )

    fun validate(password: String): Boolean{
        var result = true
        regexList.forEach{
            if(!password.contains(it)){
                result = false
            }
        }
        return result
    }

}