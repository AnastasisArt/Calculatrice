package com.example.calculatrice_av

class Calculatrice{
    private var saisie=""
    private var num1= 0
    private var num2= 0
    private var operateur: EOperateur?= null

    //Saisie de chiffre
    fun addNum(chiffre: String){
        saisie+= chiffre
    }

    //Conversion de la chaîne reçue en opérateur
    private fun String.opeSaisi(): EOperateur?{
        return when (this){
            "+"-> EOperateur.Addition
            "-"-> EOperateur.Soustraction
            "*"-> EOperateur.Multiplication
            "/"-> EOperateur.Division
            "%"-> EOperateur.Modulo
            else->null
        }
    }

    //Ajout d'opérateur
    fun addOpe(operateur: String){
        if(num1 == 0 && saisie != ""){
            num1= saisie.toInt()
            saisie= "$num1 $operateur "
            this.operateur= operateur.opeSaisi()
        }else{
            saisie+= " $operateur"
            this.operateur= operateur.opeSaisi()
        }
    }

    //Réinitialiser contenu
    fun resetCalc(){
        saisie=""
        num1= 0
        num2= 0
        operateur= null
    }

    //Calcul du résultat
    fun calculer(): Double?{
        if(num1 != 0 && operateur != null){
            val saisies = saisie.trim().split(" ")
            if(saisies.size > 2){
                num2= saisies.last().toInt()
                return when (operateur){
                    EOperateur.Addition->(num1 + num2).toDouble()
                    EOperateur.Soustraction->(num1 - num2).toDouble()
                    EOperateur.Multiplication->(num1 * num2).toDouble()
                    /*
                    A la base les variables num1/num2 sont en int puisque la calculatrice ne permet pas de saisie de Double de toute façon
                    -> Et comme ça j'ai pas à gérer l'affichage
                    Mais pour la division j'ai passé les variables en double parce que sinon ca ne fonctionnait pas
                    */
                    EOperateur.Division->(num1.toDouble() / num2.toDouble())
                    EOperateur.Modulo->(num1 % num2).toDouble()
                    else->null
                }
            }
        }
        return null
    }

    //Récupération saisie complète pour affichage
    fun getSaisie():String{
        //Calcule si la saisie le permet
        val result=calculer()
        return if(num1 != 0 && num2 != 0){
            "$saisie = $result"
        }else{
            //Sinon affiche la saisie en cours
            saisie
        }
    }
}
