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
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun CartItemDetailCard(item: Item, viewmodel: CategoryViewModel) {
    val itemqty = remember { mutableStateOf(item.quantity) }
    val itemprice = remember { mutableStateOf(item.quantity * item.price) }
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
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(0.6f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(0.4f)
                    ) {
                        IconButton(onClick = {
                            if (itemqty.value > 1) {
                                itemqty.value--
                                itemprice.value = itemqty.value * item.price
                            }
                            viewmodel.decreaseItemCount(item)
                        }) {
                            Icon(
                                imageVector = Icons.Default.RemoveCircle,
                                contentDescription = "Add",
                                Modifier.size(50.dp),
                                tint = Color(0xFFFFC55C)
                            )
                        }

                        Text(text = "${itemqty.value}", fontWeight = FontWeight.SemiBold)

                        IconButton(onClick = {
                            itemqty.value++
                            itemprice.value = itemqty.value * item.price
                            viewmodel.addItemCount(item)
                        }) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Add",
                                Modifier.size(50.dp),
                                tint = Color(0xFFFFC55C)
                            )
                        }

                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "\u20B9" + item.price.toString(), fontWeight = FontWeight.Bold)
                    Text(text = "\u20B9${itemprice.value}", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
