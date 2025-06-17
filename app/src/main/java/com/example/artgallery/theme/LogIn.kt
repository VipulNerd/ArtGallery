package com.example.artgallery.theme


import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artgallery.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage(navController: NavController, authViewModel: AuthViewModel) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    val authState = authViewModel.authState
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthenticationState.Authenticate -> navController.navigate(Screen.MainScreen)
            is AuthenticationState.Error -> Toast.makeText(context,(authState.value as AuthenticationState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Scaffold (
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.login_page),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            )
        }
    ){innerPadding->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding).safeContentPadding()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            EmailEntry(
                email = R.string.login_email,
                value = emailInput,
                onValueChange = { emailInput = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            PasswordEntry(
                password = R.string.login_pass,
                value = passwordInput,
                onValueChange = {passwordInput = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go),
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            ElevatedButton(onClick = {authViewModel.login(emailInput, passwordInput)},
                enabled = authState.value != AuthenticationState.Loading) {
                Text("Log In")
            }
            TextButton(onClick = {navController.navigate(Screen.Signup.rout)}) {
                Text("Don't Have account, Sign Up")
            }
        }
    }

}

@Composable
fun EmailEntry(
    @StringRes email: Int,
    onValueChange: (String) -> Unit,
    value: String,
    keyboardOptions : KeyboardOptions,
    modifier: Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text (stringResource(id = email)) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun PasswordEntry(
    @StringRes password: Int,
    onValueChange: (String) -> Unit,
    value: String,
    keyboardOptions : KeyboardOptions,
    modifier: Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text (stringResource(id = password)) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
    )
}
