package com.example.assignment.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignment.data.model.Item
import com.example.assignment.viewModel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(item: List<Item>, viewmodel: CategoryViewModel, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(navigationIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back Icon",
                    modifier = Modifier.clickable {
                        onBackPressed()
                    }
                )
            }, title = { Text(text = "Cart") })
        }

    ) { scaffoldPadding ->
        Box(
            modifier = Modifier
                .padding(scaffoldPadding)
                .padding(8.dp)
        ) {

            LazyColumn {
                items(item) {
                    CartItemDetailCard(it, viewmodel)
                }
            }

        }
    }
}