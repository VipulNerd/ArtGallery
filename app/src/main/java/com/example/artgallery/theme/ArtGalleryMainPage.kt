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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.artgallery.R
import com.example.artgallery.viewmodel.ArtGalleryViewModel
import kotlinx.coroutines.selects.select


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtGalleryLayout(navController: NavController, viewModel: ArtGalleryViewModel = viewModel()) {
    val currentImage = viewModel.currentImage.intValue
    val currentTab = remember { mutableStateOf("Home") }
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
            NavigationBar(currentTab = currentTab.value) { selectedTab ->
                currentTab.value = selectedTab
            }
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



@Composable
fun NavigationBar(
    currentTab: String,
    onTabPressed: (String) -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        listOf("Home", "Cart", "Account").forEach { tab ->
            val iconRes = when (tab) {
                "Home" -> R.drawable.home_24px
                "Cart" -> R.drawable.add_shopping_cart_24px
                "Account" -> R.drawable.logout_24px
                else -> R.drawable.home_24px
            }

            NavigationBarItem(
                selected = currentTab == tab,
                onClick = { onTabPressed(tab) },
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = tab
                    )
                },
                label = { Text(tab) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}


