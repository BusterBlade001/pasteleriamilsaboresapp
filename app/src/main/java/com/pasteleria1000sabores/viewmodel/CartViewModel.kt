package com.pasteleria1000sabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pasteleria1000sabores.data.database.AppDatabase
import com.pasteleria1000sabores.data.model.CartItem
import com.pasteleria1000sabores.data.model.CartItemWithProduct
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartDao = AppDatabase.getDatabase(application).cartDao()
    private val productDao = AppDatabase.getDatabase(application).productDao()
    
    val cartItems: LiveData<List<CartItemWithProduct>> = cartDao.getAllCartItems()
    val cartItemCount: LiveData<Int> = cartDao.getCartItemCount()
    
    fun addToCart(productId: Int, quantity: Int = 1, customMessage: String? = null) {
        viewModelScope.launch {
            val existingItem = cartDao.getCartItemByProductId(productId)
            if (existingItem != null) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
                cartDao.update(updatedItem)
            } else {
                val newItem = CartItem(productId = productId, quantity = quantity, customMessage = customMessage)
                cartDao.insert(newItem)
            }
        }
    }
    
    fun updateCartItem(cartItem: CartItem, quantity: Int) {
        viewModelScope.launch {
            val updatedItem = cartItem.copy(quantity = quantity)
            cartDao.update(updatedItem)
        }
    }
    
    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            cartDao.delete(cartItem)
        }
    }
    
    fun clearCart() {
        viewModelScope.launch {
            cartDao.clearCart()
        }
    }
}
