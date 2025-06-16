package com.example.artgallery

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {

                    ArtGalleryLayout()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtGalleryLayout() {
    var currentImage by remember { mutableStateOf(1) }


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
                1 -> {
                    ArtGalleryView(
                        imageId = (R.drawable.image_),
                        messagId = (R.string.image1_disc),
                        nextImageId = {
                            currentImage += 1
                        },
                        prevImageId = { if (currentImage > 1) currentImage -= 1 },
                        modifier = Modifier.padding(16.dp)
                    )
                }

                2 -> {
                    ArtGalleryView(
                        imageId = (R.drawable.image2),
                        messagId = (R.string.image2_disc),
                        nextImageId = {
                            currentImage += 1
                        },
                        prevImageId = { if (currentImage > 1) currentImage -= 1 },
                        modifier = Modifier.padding(16.dp)
                    )
                }

                3 -> {
                    ArtGalleryView(
                        imageId = (R.drawable.iamge3),
                        messagId = (R.string.image3_disc),
                        nextImageId = {},
                        prevImageId = { if (currentImage > 1) currentImage -= 1 },
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
        }
    }

}

@Composable
fun ArtGalleryView(imageId: Int,
                   messagId:Int,
                   nextImageId:()->Unit,
                   prevImageId:()->Unit,
                   modifier: Modifier){
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier.size(400.dp)
                .fillMaxSize()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(messagId),
            modifier = modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){

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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGalleryLayout()
    }
}