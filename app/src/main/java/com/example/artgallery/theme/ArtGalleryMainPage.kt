package com.example.artgallery.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artgallery.BottomBarContent
import com.example.artgallery.R


@Composable
fun ArtGalleryView(imageId: Int,
                   messageId:Int,
                   nextImageId:()->Unit,
                   prevImageId:()->Unit,
                   onAddToCart: () -> Unit,
                   modifier: Modifier){
    Column(modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
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

        Button(onClick = onAddToCart) {
            Text("Add to Cart")
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
