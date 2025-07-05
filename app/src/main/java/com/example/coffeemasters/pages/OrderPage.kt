package com.example.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeemasters.DataManager
import com.example.coffeemasters.Product
import com.example.coffeemasters.ui.theme.CardBackground
import com.example.coffeemasters.ui.theme.Primary

@Composable
fun OrderPage(dataManager: DataManager) {
    LazyColumn {
        item {
            DefaultSpacer()
            if (dataManager.cart.count() == 0){
                Card(
                    elevation =  CardDefaults.cardElevation(2.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Your order is empty",
                        color = Primary,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        items(dataManager.cart) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(2.dp),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .background(CardBackground)
                    .padding(12.dp)
            ) {
                CartItem(
                    it.product,
                    it.quantity,
                    onRemove = {
                    dataManager.cartRemove(it)
                })
            }
        }
    }
}

@Composable
fun CartItem(product: Product, quantity: Int, onRemove: (Product)-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "quantity: $quantity",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    onRemove(product)
                },
            ) {
                Text("Remove")
            }
        }
    }
}