package com.example.assignment.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.assignment.data.model.Item
import com.example.assignment.viewModel.CategoryViewModel

@Composable
fun ItemCardLong(item: Item,viewmodel:CategoryViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.icon,
                contentScale = ContentScale.Inside,
                contentDescription = "Icon",
                modifier = Modifier.size(70.dp)
            )
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(0.8F)) {
                    Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = "\u20B9" + item.price.toString(), fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2F),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = {
                        viewmodel.deleteFavourite(item)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .padding(8.dp),
                            tint = Color(0xFFFF5F5F)
                        )
                    }
                    IconButton(onClick = { /*TODO : Implement addition feature*/ }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Add",
                            Modifier.size(50.dp),
                            tint = Color(0xFFFFC55C)
                        )
                    }
                }
            }
        }
    }
}

