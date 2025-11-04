package com.pasteleria1000sabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.pasteleria1000sabores.data.database.AppDatabase
import com.pasteleria1000sabores.data.model.Product

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = AppDatabase.getDatabase(application).productDao()
    
    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    
    private val _selectedCategoryId = MutableLiveData<Int?>()
    val selectedCategoryId: LiveData<Int?> = _selectedCategoryId
    
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery
    
    val filteredProducts: LiveData<List<Product>> = _searchQuery.switchMap { query ->
        if (query.isNullOrEmpty()) {
            _selectedCategoryId.switchMap { categoryId ->
                if (categoryId == null) {
                    allProducts
                } else {
                    productDao.getProductsByCategory(categoryId)
                }
            }
        } else {
            productDao.searchProducts(query)
        }
    }
    
    fun setSelectedCategory(categoryId: Int?) {
        _selectedCategoryId.value = categoryId
    }
    
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
