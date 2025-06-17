package com.example.artgallery.theme


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInPage() {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

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

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ArtGalleryTheme {
        LogInPage()
    }
}