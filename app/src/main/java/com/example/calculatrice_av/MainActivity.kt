package com.example.calculatrice_av

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.calculatrice_av.ui.theme.Calculatrice_AVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculatrice_AVTheme {
                CalculatriceApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun CalculatriceApp(modifier: Modifier = Modifier){
    val calculatrice = remember{Calculatrice()}
    //Mise en place d'une variable d'état afin d'afficher la saisie dynamiquement
    var displaySaisie by remember{mutableStateOf(calculatrice.getSaisie())}

    //Mise à jour de l'affichage
    fun updateSaisie(){
        displaySaisie= calculatrice.getSaisie()
    }

    Column(
        modifier= modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Affichage de la saisie
        Text(text=displaySaisie, modifier= Modifier.padding(30.dp))

        //---- Boutons
        //1ere ligne boutons
        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
            listOf("1", "2", "3").forEach{chiffre->
                Button(onClick={
                    calculatrice.addNum(chiffre)
                    updateSaisie()
                },
                    modifier= Modifier.weight(1f)
                ){
                    Text(text=chiffre)
                }
            }
        }

        //2eme ligne boutons
        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
            listOf("4", "5", "6").forEach{chiffre->
                Button(onClick={
                    calculatrice.addNum(chiffre)
                    updateSaisie()
                },
                    modifier= Modifier.weight(1f)
                ){
                    Text(text=chiffre)
                }
            }
        }

        //3ème ligne boutons
        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
            listOf("7", "8", "9").forEach{chiffre->
                Button(onClick={
                    calculatrice.addNum(chiffre)
                    updateSaisie()
                },
                    modifier= Modifier.weight(1f)
                ){
                    Text(text=chiffre)
                }
            }
        }

        //4ème ligne boutons
        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)){
            //Bouton effacer
            Button(onClick={
                calculatrice.resetCalc()
                updateSaisie()
            },
                modifier= Modifier.weight(1f)
            ){
                Text(text="C")
            }

            Button(onClick={
                calculatrice.addNum("0")
                updateSaisie()
            },
                modifier= Modifier.weight(1f)
            ){
                Text(text="0")
            }

            //Bouton pour calculer
            Button(onClick={
                calculatrice.calculer()
                updateSaisie()
            },
                modifier= Modifier.weight(1f)
            ){
                Text(text="=")
            }
        }

        //Boutons d'opérateurs
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)){
            listOf("+", "-", "*", "/", "%").forEach{operateur->
                Button(onClick={
                    calculatrice.addOpe(operateur)
                    updateSaisie()
                }){
                    Text(text=operateur)
                }
            }
        }
    }
}
