package com.example.mvvm_demo.view

import androidx.compose.foundation.layout.* // Importation des outils de mise en page
import androidx.compose.material3.* // Importation des composants Material Design 3
import androidx.compose.runtime.Composable // Annotation pour définir une fonction Composable
import androidx.compose.runtime.livedata.observeAsState // Utilisé pour observer LiveData dans Compose
import androidx.compose.ui.Alignment // Pour aligner les composants
import androidx.compose.ui.Modifier // Pour appliquer des modifications d'affichage
import com.example.mvvm_demo.viewmodel.HomeViewModel // Importation du ViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier, // Modificateur optionnel pour personnaliser l'affichage
    viewModel: HomeViewModel // Le ViewModel utilisé pour interagir avec les données
) {
    // Observer les données utilisateur et l'état de chargement depuis le ViewModel
    val userData = viewModel.userData.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState(initial = false)

    // Disposition verticale des composants
    Column(
        modifier = Modifier.fillMaxSize(), // Prend tout l'espace disponible
        verticalArrangement = Arrangement.Center, // Centre les éléments verticalement
        horizontalAlignment = Alignment.CenterHorizontally // Centre les éléments horizontalement
    ) {
        // Si l'application est en cours de chargement, afficher un indicateur de progression
        if (isLoading.value == true) {
            CircularProgressIndicator() // Indicateur circulaire de chargement
        } else {
            // Sinon, afficher le bouton pour obtenir les données
            Button(onClick = {
                viewModel.getUserData() // Appelle la fonction du ViewModel pour récupérer les données
            }) {
                Text("Get Data") // Texte du bouton
            }
        }

        // Si les données utilisateur sont disponibles, afficher le nom et l'âge
        userData.value?.name?.let {
            Text("Nom : $it") // Affiche le nom de l'utilisateur
        }
        userData.value?.age?.let {
            Text("Âge : $it ans") // Affiche l'âge de l'utilisateur
        }
    }
}
