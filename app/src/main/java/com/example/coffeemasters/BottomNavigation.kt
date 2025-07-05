package com.example.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeemasters.ui.theme.Alternative1
import com.example.coffeemasters.ui.theme.OnPrimary
import com.example.coffeemasters.ui.theme.Primary

data class NavPage(
    var name: String,
    var icon: ImageVector,
    var route: String
)

object Routes {

  val MenuPage   = NavPage(name = "Menu", icon = Icons.Outlined.Menu, route = "Menu")
  val OffersPage = NavPage(name = "Offers", icon = Icons.Outlined.Star, route = "Menu")
  val OrderPage  = NavPage(name = "My Order", icon = Icons.Outlined.ShoppingCart, route = "Menu")
  val InfoPage   = NavPage(name = "Info", icon = Icons.Outlined.Info, route = "Menu")

  val pages = listOf(MenuPage, OffersPage, OrderPage, InfoPage)
}

@Preview
@Composable
fun NavBarItem_Preview() {
   NavBarItem(
       page = Routes.MenuPage,
       modifier = Modifier.padding(8.dp)
   )
}

@Preview
@Composable
fun NavBar(selectedRoute: String = Routes.MenuPage.name, onChange: (String) -> Unit = {}) {
  Row(
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.SpaceAround,
      modifier = Modifier
        .background(Primary)
        .fillMaxWidth()
        .padding(16.dp)
  ) {
         for (page in Routes.pages) {
             NavBarItem(
                 page = page,
                 selected = selectedRoute == page.name,
                 modifier = Modifier.clickable {
                     onChange(page.name)
                     println("Hello route ${page.route}")
                 }
             )
         }
  }
}

@Composable
fun NavBarItem(page: NavPage, selected: Boolean = false, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 12.dp)
    ) {
        val iconSize =  if (selected) 16 else 12

        Image(
            imageVector = page.icon,
            contentDescription = page.name,
            colorFilter = ColorFilter.tint(
                if (selected) OnPrimary else Alternative1
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size((iconSize).dp)
        )
        Text(
            text = page.name,
            fontSize = if (selected) 14.sp else 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) OnPrimary else Alternative1
        )
    }
}


class BottomNavigation {
}