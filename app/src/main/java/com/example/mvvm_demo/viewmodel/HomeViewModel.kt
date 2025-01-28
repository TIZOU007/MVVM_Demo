package com.example.mvvm_demo.viewmodel

import androidx.lifecycle.LiveData // Utilisé pour exposer des données observables
import androidx.lifecycle.MutableLiveData // Utilisé pour modifier les données observables
import androidx.lifecycle.ViewModel // Classe de base pour le ViewModel
import androidx.lifecycle.viewModelScope // Permet de lancer des coroutines dans le ViewModel
import com.example.mvvm_demo.model.UserData // Importation du modèle UserData
import com.example.mvvm_demo.model.UserRepository // Importation du repository pour accéder aux données
import kotlinx.coroutines.launch // Utilisé pour lancer des coroutines

class HomeViewModel : ViewModel() {

    // Instance du UserRepository pour récupérer les données utilisateur
    private val userRepository: UserRepository = UserRepository()

    // LiveData pour contenir les données utilisateur
    private val _userData = MutableLiveData<UserData>() // Mutable pour permettre les mises à jour
    val userData: LiveData<UserData> = _userData // Exposition en tant que LiveData (lecture seule)

    // LiveData pour suivre l'état de chargement
    private val _isLoading = MutableLiveData<Boolean>() // Mutable pour gérer l'état
    val isLoading: LiveData<Boolean> = _isLoading // Exposition en tant que LiveData (lecture seule)

    // Fonction pour récupérer les données utilisateur
    fun getUserData() {
        // Indiquer que le chargement commence
        _isLoading.value = true

        // Lancer une coroutine dans le scope du ViewModel
        viewModelScope.launch {
            // Récupérer les données utilisateur via le repository (peut être une opération longue)
            val userResult = userRepository.fetchUserData()

            // Mettre à jour les données utilisateur dans le LiveData
            _userData.postValue(userResult)

            // Indiquer que le chargement est terminé
            _isLoading.postValue(false)
        }
    }
}
