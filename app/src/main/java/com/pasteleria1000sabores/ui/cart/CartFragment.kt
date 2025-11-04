package com.pasteleria1000sabores.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pasteleria1000sabores.R
import com.pasteleria1000sabores.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        val recyclerView: RecyclerView = view.findViewById(R.id.cart_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        cartAdapter = CartAdapter(
            onQuantityChanged = { cartItem, quantity ->
                cartViewModel.updateCartItem(cartItem, quantity)
            },
            onRemoveItem = { cartItem ->
                cartViewModel.removeFromCart(cartItem)
            }
        )
        recyclerView.adapter = cartAdapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            cartAdapter.submitList(items)
        }
    }
}
