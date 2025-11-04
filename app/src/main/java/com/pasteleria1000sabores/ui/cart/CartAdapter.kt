package com.pasteleria1000sabores.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pasteleria1000sabores.R
import com.pasteleria1000sabores.data.model.CartItem
import com.pasteleria1000sabores.data.model.CartItemWithProduct
import java.text.NumberFormat
import java.util.*

class CartAdapter(
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onRemoveItem: (CartItem) -> Unit
) : ListAdapter<CartItemWithProduct, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view, onQuantityChanged, onRemoveItem)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartViewHolder(
        itemView: View,
        private val onQuantityChanged: (CartItem, Int) -> Unit,
        private val onRemoveItem: (CartItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.cart_product_image)
        private val productName: TextView = itemView.findViewById(R.id.cart_product_name)
        private val productPrice: TextView = itemView.findViewById(R.id.cart_product_price)
        private val quantityText: TextView = itemView.findViewById(R.id.cart_quantity_text)
        private val subtotalText: TextView = itemView.findViewById(R.id.cart_subtotal_text)
        private val decreaseButton: ImageButton = itemView.findViewById(R.id.decrease_quantity_button)
        private val increaseButton: ImageButton = itemView.findViewById(R.id.increase_quantity_button)
        private val removeButton: ImageButton = itemView.findViewById(R.id.remove_item_button)

        fun bind(cartItemWithProduct: CartItemWithProduct) {
            val cartItem = cartItemWithProduct.cartItem
            val product = cartItemWithProduct.product
            
            productName.text = product.name
            
            val format = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
            productPrice.text = format.format(product.price)
            
            quantityText.text = cartItem.quantity.toString()
            
            val subtotal = product.price * cartItem.quantity
            subtotalText.text = format.format(subtotal)
            
            Glide.with(itemView.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(productImage)
            
            decreaseButton.setOnClickListener {
                if (cartItem.quantity > 1) {
                    onQuantityChanged(cartItem, cartItem.quantity - 1)
                }
            }
            
            increaseButton.setOnClickListener {
                onQuantityChanged(cartItem, cartItem.quantity + 1)
            }
            
            removeButton.setOnClickListener {
                onRemoveItem(cartItem)
            }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartItemWithProduct>() {
        override fun areItemsTheSame(oldItem: CartItemWithProduct, newItem: CartItemWithProduct): Boolean {
            return oldItem.cartItem.id == newItem.cartItem.id
        }

        override fun areContentsTheSame(oldItem: CartItemWithProduct, newItem: CartItemWithProduct): Boolean {
            return oldItem == newItem
        }
    }
}
