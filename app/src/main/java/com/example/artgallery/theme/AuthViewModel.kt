package com.example.artgallery.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthenticationState>()
    val authState: LiveData<AuthenticationState> = _authState

    init {
        authenticateStatus()
    }

    fun authenticateStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthenticationState.Unauthenticate
        } else {
            _authState.value = AuthenticationState.Authenticate
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthenticationState.Error("Email or password is empty")
            return
        }

        _authState.value = AuthenticationState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthenticationState.Authenticate
                } else {
                    _authState.value = AuthenticationState.Error("Authentication Failed")
                }
            }
    }


    fun signup(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthenticationState.Error("Email or password is empty")
            return
        }

        _authState.value = AuthenticationState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    _authState.value =AuthenticationState.Authenticate
                }else{
                    _authState.value = AuthenticationState.Error("Authentication Failed")
                }
            }
    }

//    fun signout(){
//        auth.signOut()
//        _authState.value = AuthenticationState.Unauthenticate
//    }
}
sealed class AuthenticationState(){
    object Authenticate: AuthenticationState()
    object Unauthenticate: AuthenticationState()
    object Loading: AuthenticationState()
    data class Error(val message : String): AuthenticationState()
}