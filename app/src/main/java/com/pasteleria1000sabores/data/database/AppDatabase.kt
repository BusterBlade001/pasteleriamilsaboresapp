package com.pasteleria1000sabores.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pasteleria1000sabores.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Product::class,
        Category::class,
        CartItem::class,
        User::class,
        Order::class,
        OrderItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cartDao(): CartDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pasteleria_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(database: AppDatabase) {
            val categoryDao = database.categoryDao()
            val productDao = database.productDao()

            // Insertar categorías
            val categories = listOf(
                Category(1, "TC", "Tortas Cuadradas", "Deliciosas tortas de forma cuadrada para todas las ocasiones"),
                Category(2, "TT", "Tortas Circulares", "Tortas tradicionales de forma circular"),
                Category(3, "PI", "Postres Individuales", "Postres individuales perfectos para disfrutar en cualquier momento"),
                Category(4, "PSA", "Productos Sin Azúcar", "Opciones saludables sin azúcar añadida"),
                Category(5, "PT", "Pastelería Tradicional", "Recetas tradicionales que han pasado de generación en generación"),
                Category(6, "PG", "Productos Sin Gluten", "Productos aptos para personas con intolerancia al gluten"),
                Category(7, "PV", "Productos Vegana", "Productos 100% veganos sin ingredientes de origen animal"),
                Category(8, "TE", "Tortas Especiales", "Tortas especiales para momentos únicos")
            )
            categories.forEach { categoryDao.insert(it) }

            // Insertar productos
            val products = listOf(
                Product(1, "TC001", 1, "Torta Cuadrada de Chocolate", "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.", 45000, "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=400", "cuadrada", null, true),
                Product(2, "TC002", 1, "Torta Cuadrada de Frutas", "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.", 50000, "https://images.unsplash.com/photo-1565958011703-44f9829ba187?w=400", "cuadrada", null, true),
                Product(3, "TT001", 2, "Torta Circular de Vainilla", "Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.", 40000, "https://images.unsplash.com/photo-1464349095431-e9a21285b5f3?w=400", "circular", null, true),
                Product(4, "TT002", 2, "Torta Circular de Manjar", "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.", 42000, "https://images.unsplash.com/photo-1535141192574-5d4897c12636?w=400", "circular", null, true),
                Product(5, "PI001", 3, "Mousse de Chocolate", "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.", 5000, "https://images.unsplash.com/photo-1541599468348-e96984315921?w=400", "individual", null, false),
                Product(6, "PI002", 3, "Tiramisú Clásico", "Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.", 5500, "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400", "individual", null, false),
                Product(7, "PSA001", 4, "Torta Sin Azúcar de Naranja", "Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.", 48000, "https://images.unsplash.com/photo-1557925923-cd4648e211a0?w=400", "circular", null, false),
                Product(8, "PSA002", 4, "Cheesecake Sin Azúcar", "Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.", 47000, "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=400", "circular", null, false),
                Product(9, "PT001", 5, "Empanada de Manzana", "Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.", 3000, "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400", "otro", null, false),
                Product(10, "PT002", 5, "Tarta de Santiago", "Tradicional tarta española hecha con almendras, azúcar, y huevos, una delicia para los amantes de los postres clásicos.", 6000, "https://images.unsplash.com/photo-1519915212116-7cfef71f1d3e?w=400", "circular", null, false),
                Product(11, "PG001", 6, "Brownie Sin Gluten", "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.", 4000, "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=400", "cuadrada", null, false),
                Product(12, "PG002", 6, "Pan Sin Gluten", "Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida.", 3500, "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400", "otro", null, false),
                Product(13, "PV001", 7, "Torta Vegana de Chocolate", "Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.", 50000, "https://images.unsplash.com/photo-1606890737304-57a1ca8a5b62?w=400", "circular", null, true),
                Product(14, "PV002", 7, "Galletas Veganas de Avena", "Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.", 4500, "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400", "otro", null, false),
                Product(15, "TE001", 8, "Torta Especial de Cumpleaños", "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.", 55000, "https://images.unsplash.com/photo-1558636508-e0db3814bd1d?w=400", "circular", null, true),
                Product(16, "TE002", 8, "Torta Especial de Boda", "Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.", 60000, "https://images.unsplash.com/photo-1519225421980-715cb0215aed?w=400", "circular", null, true)
            )
            products.forEach { productDao.insert(it) }
        }
    }
}
