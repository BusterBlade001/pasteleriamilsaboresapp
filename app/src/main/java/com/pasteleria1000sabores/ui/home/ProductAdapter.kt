package com.pasteleria1000sabores.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.pasteleria1000sabores.R
import com.pasteleria1000sabores.data.model.Product
import java.text.NumberFormat
import java.util.*

class ProductAdapter(
    private val onAddToCart: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, onAddToCart)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(
        itemView: View,
        private val onAddToCart: (Product) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.product_image)
        private val productName: TextView = itemView.findViewById(R.id.product_name)
        private val productDescription: TextView = itemView.findViewById(R.id.product_description)
        private val productPrice: TextView = itemView.findViewById(R.id.product_price)
        private val customizableBadge: TextView = itemView.findViewById(R.id.customizable_badge)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.add_to_cart_button)

        fun bind(product: Product) {
            productName.text = product.name
            productDescription.text = product.description
            
            val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
            productPrice.text = format.format(product.price)
            
            customizableBadge.visibility = if (product.customizable) View.VISIBLE else View.GONE
            
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(productImage)
            
            addToCartButton.setOnClickListener {
                onAddToCart(product)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
