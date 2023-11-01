package com.example.assignment.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.assignment.data.model.Item
import com.example.assignment.data.model.Product
import com.example.assignment.data.roomdatabase.MyDatabase
import com.example.assignment.jsonconverter.JsonParser
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
class CategoryViewModel(context: Context) : ViewModel() {

    private val productDao = MyDatabase.getInstance(context).productDao()

    private var _categorieState = MutableStateFlow(emptyList<Product>())
    val categorieState: StateFlow<List<Product>> = _categorieState.asStateFlow()

    val _favouritelist = mutableStateListOf<Item>()
    val favouriteList: List<Item>
        get() = _favouritelist

    private val _cartList = mutableStateListOf<Item>()
    val cartList: List<Item>
        get() = _cartList

    init {
        GlobalScope.launch {
            _categorieState.value = productDao.getProductList()
        }
        addAllProductsInDB(context)
    }

    fun additemtoCart(itemm: Item) {
        if (_cartList.contains(itemm)) {
            val ind = _cartList.indexOf(itemm)
            _cartList[ind].quantity++
        } else {
            itemm.quantity++
            _cartList.add(itemm)
        }
    }

    fun addItemToFavouriteList(itemm: Item) {
        var repeated = false
        if (_favouritelist.isNotEmpty() && itemm.favourite) {
            for (item in _favouritelist) {
                if (item.id == itemm.id) {
                    repeated = true
                    break
                }
            }
            if (!repeated) {
                _favouritelist.add(itemm)
            }
        } else {
            if (itemm.favourite) {
                itemm.favourite = true
                _favouritelist.add(itemm)
            } else {
                _favouritelist.remove(itemm)
            }
        }
    }

    fun deleteFavourite(item: Item) {
        _favouritelist.remove(item)
    }

    /**
     * Add the data parsed from JSON to the Database
     */
    private fun addAllProductsInDB(context: Context) {
        GlobalScope.launch {
            JsonParser().getQuotesFromJson(context).forEach {
                productDao.insertProduct(it)
            }
        }
    }

    fun decreaseItemCount(item: Item) {
        if (cartList[cartList.indexOf(item)].quantity <= 1) {
            cartList[cartList.indexOf(item)].quantity = 0
            _cartList.remove(item)
        } else {
            if (cartList[cartList.indexOf(item)].quantity >= 1)
                cartList[cartList.indexOf(item)].quantity--
        }
    }

    fun addItemCount(item: Item) {
        cartList[cartList.indexOf(item)].quantity++
    }
}