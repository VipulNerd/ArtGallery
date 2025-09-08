package com.example.artgallery.theme


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artgallery.BottomBarContent
import com.example.artgallery.R
import com.example.artgallery.viewmodel.ArtGalleryViewModel
import kotlinx.coroutines.launch


@Composable
fun ArtGalleryView(
    artId: String,
    imageId: Int,
    title: String,
    messageId: Int,
    nextImageId: () -> Unit,
    prevImageId: () -> Unit,
    onAddToCart: () -> Unit,
    onViewDetails: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArtGalleryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var isFavorite by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp)),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Animated image transition
                Crossfade(targetState = imageId) { imgRes ->
                    Image(
                        painter = painterResource(imgRes),
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Artwork title
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                // Artwork description
                Text(
                    text = stringResource(messageId),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Navigation buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = prevImageId,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Prev")
                    }

                    Button(
                        onClick = nextImageId,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Next")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action buttons row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onAddToCart()
                            scope.launch {
                                snackbarHostState.showSnackbar("Added to Cart!")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Add to Cart", color = MaterialTheme.colorScheme.onPrimary)
                    }

                    Button(
                        onClick = onViewDetails,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("View Details")
                    }

                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationBar(
    navController: NavController,
    currentTab: BottomBarContent,
    navigationItems: List<NavigationItemContent>,
    onTabPressed: ((BottomBarContent) -> Unit)
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        for(tab in navigationItems){
            NavigationBarItem(
                selected = currentTab == tab.iconType,
                onClick = {
                    onTabPressed(tab.iconType)
                    when (tab.iconType) {
                        BottomBarContent.HOME -> {
                            navController.navigate(Screen.MainScreen.rout) {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        }
                        BottomBarContent.CART -> {
                            navController.navigate(Screen.CartScreen.rout) {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        }
                        BottomBarContent.LOGOUT -> {
                            navController.navigate(Screen.Login.rout) {
                                popUpTo(0)
                                launchSingleTop = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = tab.text
                    )
                },
                label = { Text(tab.text) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

data class NavigationItemContent(
    val iconType: BottomBarContent,
    val icon: Int ,
    val text: String
)