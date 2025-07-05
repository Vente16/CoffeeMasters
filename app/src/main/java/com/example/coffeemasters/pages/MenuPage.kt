package com.example.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coffeemasters.DataManager
import com.example.coffeemasters.Product
import com.example.coffeemasters.ui.theme.CardBackground
import com.example.coffeemasters.ui.theme.Primary


@Composable
fun MenuPage(dataManager: DataManager) {
    LazyColumn {
        val lastItemIndex = dataManager.menu.size - 1;
        
        item {
            DefaultSpacer()
        }
        itemsIndexed(dataManager.menu) { index, item ->
             Text(
                 text = item.name,
                 color = Primary,
                 modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 10.dp)
             )

            item.products.forEachIndexed { i, product ->

                  Card(
                      elevation =  CardDefaults.cardElevation(2.dp),
                      shape = RoundedCornerShape(12.dp),
                      modifier = Modifier
                          .background(CardBackground)
                          .padding(12.dp)
                  ) {
                      ProductItem(
                          product = product,
                          onAdd = { dataManager.cartAdd(product) }
                      ) {}
                  }
            }

            if(index == lastItemIndex) {
                DefaultSpacer()
            }
        }
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun DefaultSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier
        .background(CardBackground)
        .height(100.dp)
    )
}


@Preview
@Composable
private fun ProductItem_Preview() {
  ProductItem(product = Product(id = 1, name = "Dummy", price = 1.25, image = ""), { }) { }
}


@Composable
fun ProductItem(product: Product, onAdd: (Product) -> Unit, function: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(product.name, fontWeight = FontWeight.Bold)
                    Text("$${product.price.format(2)} ea")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(),
                    onClick = {
                        onAdd(product)
                    },
                ) {
                    Text("Add")
                }
            }
        }
}

