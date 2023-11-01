package com.example.assignment.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.Product
import com.example.assignment.viewModel.CategoryViewModel

@Composable
fun DetailCard(item: Item, viewModel: CategoryViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(8.dp)
            .requiredSizeIn(maxHeight = 200.dp, maxWidth = 220.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row {
            AsyncImage(
                model = item.icon,
                contentScale = ContentScale.Inside,
                contentDescription = "Image icon",
                modifier = Modifier.weight(0.8F)
            )
            IconButton(onClick = {
                item.favourite = !item.favourite
                viewModel.addItemToFavouriteList(item)

            }) {
                if (viewModel._favouritelist.indexOf(item) != -1 &&
                    viewModel._favouritelist[viewModel._favouritelist.indexOf(item)].favourite
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(0.2F),
                        tint = Color(0xFFFF5F5F)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(0.2F)
                    )
                }
            }
        }
        Text(
            text = item.name,
            Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "\u20B9" + item.price.toString(), fontWeight = FontWeight.Bold)
            IconButton(onClick = { viewModel.additemtoCart(item) }) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add",
                    Modifier.size(70.dp),
                    tint = Color(0xFFFFC55C)
                )
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun DetailList(title: String, product: Product, viewmodel: CategoryViewModel) {
    Title(title = title)
    LazyRow {
        items(product.items) { item ->
            DetailCard(item, viewmodel)
        }
    }
}