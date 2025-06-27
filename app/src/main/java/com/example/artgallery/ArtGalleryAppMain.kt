package com.example.artgallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.artgallery.theme.ArtGalleryView
import com.example.artgallery.theme.NavigationBar
import com.example.artgallery.theme.NavigationItemContent
import com.example.artgallery.viewmodel.ArtGalleryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtGalleryLayout(navController: NavController, viewModel: ArtGalleryViewModel = viewModel()) {
    val currentImage = viewModel.currentImage.intValue
    val currentTab = remember { mutableStateOf(BottomBarContent.HOME) }
    val navItems = listOf(
        NavigationItemContent(BottomBarContent.HOME, R.drawable.home_24px, "Home"),
        NavigationItemContent(BottomBarContent.CART, R.drawable.add_shopping_cart_24px, "Cart"),
        NavigationItemContent(BottomBarContent.LOGOUT, R.drawable.logout_24px, "Account")
    )
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
        },
        bottomBar = {
            NavigationBar(
                navController = navController,
                currentTab=currentTab.value,
                navigationItems = navItems,
                onTabPressed = {selectedTab ->
                    currentTab.value = selectedTab
                }
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