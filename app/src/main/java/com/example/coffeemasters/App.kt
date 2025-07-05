package com.example.coffeemasters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeemasters.pages.DefaultSpacer
import com.example.coffeemasters.pages.InfoPage
import com.example.coffeemasters.pages.MenuPage
import com.example.coffeemasters.pages.OffersPage
import com.example.coffeemasters.pages.OrderPage
import com.example.coffeemasters.ui.theme.CoffeeMastersTheme
import com.example.coffeemasters.ui.theme.Primary


@Composable
fun AppTitle() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Primary)
            .padding(16.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
        )

    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(dataManager: DataManager) {

    var selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.name)
    }

    CoffeeMastersTheme {
        Scaffold(
            topBar = {
                AppTitle()
            },
            bottomBar = {
                NavBar(
                    selectedRoute = selectedRoute.value,
                    onChange = { newRoute ->
                        selectedRoute.value = newRoute
                    }
                )
            },
            content =  {

               when(selectedRoute.value) {
                    // MenuPage(dataManager)
                    Routes.MenuPage.name ->  MenuPage(dataManager)
                    Routes.OffersPage.name -> OffersPage()
                    Routes.OrderPage.name -> OrderPage(dataManager)
                    Routes.InfoPage.name -> InfoPage()
                }
            }
        )
    }
}

