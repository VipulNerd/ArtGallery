package com.example.artgallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.artgallery.theme.ArtGalleryView
import com.example.artgallery.theme.NavigationBar
import com.example.artgallery.theme.NavigationItemContent
import com.example.artgallery.theme.Screen
import com.example.artgallery.viewmodel.ArtGalleryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtGalleryLayout(
    navController: NavController,
    viewModel: ArtGalleryViewModel = viewModel(),
    cartViewModel: CartViewModel
) {
    val currentImage = viewModel.currentImage.intValue
    val currentTab = remember { mutableStateOf(BottomBarContent.HOME) }
    val navItems = listOf(
        NavigationItemContent(BottomBarContent.HOME, R.drawable.home_24px, "Home"),
        NavigationItemContent(BottomBarContent.CART, R.drawable.add_shopping_cart_24px, "Cart"),
        NavigationItemContent(BottomBarContent.LOGOUT, R.drawable.logout_24px, "Account")
    )

    val artworks = listOf(
        Artwork("art1", "Artwork 1", R.drawable.image_, R.string.image1_disc, 1000.0),
        Artwork("art2", "Artwork 2", R.drawable.image2, R.string.image2_disc, 2000.0),
        Artwork("art3", "Artwork 3", R.drawable.iamge3, R.string.image3_disc, 3000.0)
    )

    val artwork = artworks.getOrNull(currentImage - 1)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Art Gallery",
                        style = MaterialTheme.typography.titleLarge,
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
                currentTab = currentTab.value,
                navigationItems = navItems,
                onTabPressed = { selectedTab -> currentTab.value = selectedTab }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            artwork?.let {
                ArtGalleryView(
                    imageId = it.imageRes,
                    messageId = it.descriptionRes,
                    nextImageId = viewModel::nextImage,
                    prevImageId = viewModel::prevImage,
                    onAddToCart = {
                        cartViewModel.addItem(
                            CartItem(
                                id = it.id,
                                name = it.title,
                                quantity = 1,
                                price = it.price,
                                imageResId = it.imageRes
                            )
                        )
                    },
                    onViewDetails = { navController.navigate(Screen.Details.rout + "?artId=${it.id}") },
                    modifier = Modifier.padding(16.dp),
                    artId = it.id,
                    title = it.title,
                    viewModel = viewModel
                )
            }
        }
    }
}

data class Artwork(
    val id: String,
    val title: String,
    val imageRes: Int,
    val descriptionRes: Int,
    val price: Double
)