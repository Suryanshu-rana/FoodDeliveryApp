package com.example.assignment.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.assignment.data.model.Product
import com.example.assignment.viewModel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    categories: List<Product>,
    viewmodel: CategoryViewModel,
    onFavouriteClicked: () -> Unit,
    onCartClicked: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
//    val isButtonVisible = remember { mutableStateOf(false) }
//    val lastScrollPosition = remember { mutableStateOf(0) }
    val scrollState = rememberLazyListState()
    if (showDialog.value) {
        CategoryListPopup(categories, setShowDialog = {
            showDialog.value = it
        })
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .weight(0.8F)
                    ) {
                        Text(text = "My Store")
                    }
                    IconButton(
                        onClick = { onFavouriteClicked() },
                        modifier = Modifier.weight(0.1F)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "notification"
                        )
                    }
                    IconButton(
                        onClick = { onCartClicked() },
                        modifier = Modifier.weight(0.1F)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "notification"
                        )
                    }
                }
            }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Nav Icon",
                    modifier = Modifier.size(30.dp)
                )
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color(0xFFFFC55C),
                scrolledContainerColor = Color(0xFF002c3d),
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black
            )
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
//            if (isButtonVisible.value) {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                containerColor = Color.Black,
                contentColor = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "Categories")
                    Text(text = "Categories", style = MaterialTheme.typography.titleSmall)
                }
            }
//            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(durationMillis = 2, easing = FastOutSlowInEasing)
                    )
            ) {
                items(categories) { product ->
                    DetailList(title = product.name, product, viewmodel)
                }
            }
        }

    }

//    LaunchedEffect(scrollState) {
//        snapshotFlow { scrollState.firstVisibleItemIndex }
//            .collect { firstVisibleItemIndex ->
//                // Check if the user is scrolling upwards
//                if (firstVisibleItemIndex > lastScrollPosition.value) {
//                    isButtonVisible.value = false
//                } else if (firstVisibleItemIndex < lastScrollPosition.value) {
//                    isButtonVisible.value = true
//                }
//                lastScrollPosition.value = firstVisibleItemIndex
//            }
//    }
}
