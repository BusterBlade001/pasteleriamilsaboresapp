package com.pasteleria1000sabores.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback // Nueva importación
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController // Importación para el tipo de propiedad
import androidx.navigation.fragment.NavHostFragment // Importación CLAVE para el NavController
import androidx.navigation.ui.setupActionBarWithNavController // Importación CLAVE para Toolbar y Drawer
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.pasteleria1000sabores.R
import com.pasteleria1000sabores.viewmodel.CartViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var cartViewModel: CartViewModel
    private lateinit var navController: NavController // Propiedad para NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configurar Toolbar para mostrar el menú (incluyendo el carrito)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // 2. CORRECCIÓN DEL CRASH: Obtener NavController a través del NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup navigation with drawer (Menu Lateral)
        navView.setupWithNavController(navController)

        // Setup Action Bar with NavController (Maneja el ícono de la hamburguesa/flecha y el título)
        setupActionBarWithNavController(navController, drawerLayout)

        // Initialize CartViewModel
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]

        // 3. Solución del Warning: Configurar el manejo de retroceso moderno
        setupOnBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) // Infla el menú donde está el carrito
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                navController.navigate(R.id.nav_cart) // Navega al carrito
                true
            }
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Función para manejar el botón de retroceso de forma moderna
    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    // Intenta navegar hacia arriba. Si navigateUp() devuelve false, no hay más fragmentos y permite que se cierre la actividad.
                    if (!navController.navigateUp()) {
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        })
    }
    // La función DEPRECATED 'override fun onBackPressed()' se elimina
}