package com.pasteleria1000sabores.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pasteleria1000sabores.data.database.AppDatabase
import com.pasteleria1000sabores.data.model.Category

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryDao = AppDatabase.getDatabase(application).categoryDao()
    
    val allCategories: LiveData<List<Category>> = categoryDao.getAllCategories()
}
