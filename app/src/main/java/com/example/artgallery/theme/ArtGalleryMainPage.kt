package com.example.artgallery.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.artgallery.R
import com.example.artgallery.viewmodel.ArtGalleryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtGalleryLayout(navController: NavController, viewModel: ArtGalleryViewModel = viewModel()) {
    val currentImage = viewModel.currentImage.intValue

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title),
                        style = MaterialTheme.typography.titleLarge, // Use a large text style
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Button(
                        onClick = { navController.navigate(Screen.Login.rout) },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = stringResource(R.string.logout))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )

            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentImage) {
                1 -> ArtGalleryView(
                    imageId = R.drawable.image_,
                    messageId = R.string.image1_disc,
                    nextImageId = viewModel::nextImage,
                    prevImageId = viewModel::prevImage,
                    modifier = Modifier.padding(16.dp)
                )
                2 -> ArtGalleryView(
                    imageId = R.drawable.image2,
                    messageId = R.string.image2_disc,
                    nextImageId = viewModel::nextImage,
                    prevImageId = viewModel::prevImage,
                    modifier = Modifier.padding(16.dp)
                )
                3 -> ArtGalleryView(
                    imageId = R.drawable.iamge3,
                    messageId = R.string.image3_disc,
                    nextImageId = {},
                    prevImageId = viewModel::prevImage,
                    modifier = Modifier.padding(16.dp)
                )

            }
        }
    }

}

@Composable
fun ArtGalleryView(imageId: Int,
                   messageId:Int,
                   nextImageId:()->Unit,
                   prevImageId:()->Unit,
                   modifier: Modifier){
    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier
                .height(250.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(.10.dp))
        Text(
            text = stringResource(messageId),
            modifier = modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){

            Button(onClick = prevImageId) {
                Text(stringResource(R.string.prev_button))
            }

            Button(
                onClick = nextImageId
            ){
                Text(stringResource((R.string.next_button)))
            }

        }
    }
}



