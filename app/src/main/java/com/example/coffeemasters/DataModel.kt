package com.example.coffeemasters

class Product(
    var id: Int,
    var name: String,
    var price: Double,
    var image: String) {

    val imageUrl get() = "https://raw.githubusercontent.com/firtman/coffeemasters/refs/heads/master/api/images/${this.image}"
}

class Category(var name: String, var products: MutableList<Product>)

class ItemInCart(var product: Product, var quantity: Int)