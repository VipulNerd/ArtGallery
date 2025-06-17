package com.example.artgallery.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.collection.IntList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgallery.ArtGalleryLayout
import com.example.artgallery.R
import com.example.artgallery.ui.theme.ArtGalleryTheme

@Composable
fun LogInPage() {
    var email by remember { mutableStateOf(" ") }

    Column(modifier = Modifier.padding(16.dp)) {
        DataEntry(
            email = R.string.login_email,
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DataEntry(
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

@Preview(showBackground = true)
@Composable
fun LogInPreview() {
    ArtGalleryTheme {
        LogInPage()
    }
}