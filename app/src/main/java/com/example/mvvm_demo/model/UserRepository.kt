package com.example.mvvm_demo.model

import kotlinx.coroutines.delay // Utilisé pour simuler un délai (opération longue)

class UserRepository {

    // Fonction pour simuler la récupération des données utilisateur
    suspend fun fetchUserData(): UserData {
        // Simuler un délai de 2 secondes, comme si on faisait une requête réseau ou une tâche longue
        delay(2000)

        // Retourner un objet UserData avec des valeurs fictives
        return UserData("Moataz", 30) // Nom et âge de l'utilisateur
    }
}
