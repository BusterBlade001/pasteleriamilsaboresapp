package com.pasteleria1000sabores.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.pasteleria1000sabores.R
import com.pasteleria1000sabores.data.model.Category
import com.pasteleria1000sabores.viewmodel.CartViewModel
import com.pasteleria1000sabores.viewmodel.CategoryViewModel
import com.pasteleria1000sabores.viewmodel.ProductViewModel

class HomeFragment : Fragment() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModels
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        cartViewModel = ViewModelProvider(requireActivity())[CartViewModel::class.java]

        // Setup RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.products_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        
        productAdapter = ProductAdapter { product ->
            cartViewModel.addToCart(product.id)
            Toast.makeText(requireContext(), getString(R.string.product_added), Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = productAdapter

        // Observe products
        productViewModel.filteredProducts.observe(viewLifecycleOwner) { products ->
            productAdapter.submitList(products)
        }

        // Setup search
        val searchEditText: TextInputEditText = view.findViewById(R.id.search_edit_text)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                productViewModel.setSearchQuery(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Setup category spinner
        val categorySpinner: Spinner = view.findViewById(R.id.category_spinner)
        categoryViewModel.allCategories.observe(viewLifecycleOwner) { categories ->
            val categoryNames = mutableListOf(getString(R.string.all_categories))
            categoryNames.addAll(categories.map { it.name })
            
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
            
            categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position == 0) {
                        productViewModel.setSelectedCategory(null)
                    } else {
                        productViewModel.setSelectedCategory(categories[position - 1].id)
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }
}
