package com.example.artgallery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.artgallery.viewmodel.ArtGalleryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    artId: String,
    viewModel: ArtGalleryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onBack: () -> Unit,
    onAddToCart: () -> Unit
) {
    val artIndex = artId.toIntOrNull() ?: viewModel.currentImage.intValue
    val artTitle = "Artwork $artIndex"
    val artDesc = "Description for artwork $artIndex"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(artTitle) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Image(
                painter = painterResource(id = com.example.artgallery.R.drawable.image_),
                contentDescription = artTitle,
                modifier = Modifier.height(300.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(12.dp))
            Text(artTitle, style = MaterialTheme.typography.headlineSmall)
            Text("by Unknown Artist", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
            Spacer(Modifier.height(8.dp))
            Text(artDesc, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { viewModel.addToCart(artIndex); onAddToCart() }) {
                    Text("Add to Cart")
                }
                OutlinedButton(onClick = { viewModel.toggleFavorite(artIndex) }) {
                    Text("Favorite")
                }
            }
        }
    }
}