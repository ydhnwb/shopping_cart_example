package com.ydhnwb.shoppingcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ydhnwb.shoppingcart.R
import com.ydhnwb.shoppingcart.models.Product
import com.ydhnwb.shoppingcart.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.component_number_picker.view.*
import kotlinx.android.synthetic.main.list_item_selected_product.view.*

class SelectedProductAdapter (private var selectedProducts : MutableList<Product>, private var context: Context,
private var productViewModel: ProductViewModel) : RecyclerView.Adapter<SelectedProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_selected_product, parent, false))
    }

    override fun getItemCount() = selectedProducts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(selectedProducts[position], context, productViewModel)

    fun updateList(ps: List<Product>){
        selectedProducts.clear()
        selectedProducts.addAll(ps)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(product: Product, context: Context, productViewModel: ProductViewModel){
            itemView.product_name.text = product.name
            itemView.product_price.text = product.price.toString()
            itemView.product_selectedQuantity.text = product.selectedQuantity.toString()
            itemView.product_image.load(product.image)
            itemView.product_increment.setOnClickListener {
                productViewModel.incrementQuantityProduct(product)
            }
            itemView.product_decrement.setOnClickListener {
                productViewModel.decrementQuantityProduct(product)
            }
            itemView.product_more.setOnClickListener {
                PopupMenu(context, it).apply {
                    menuInflater.inflate(R.menu.popup_menu, menu)
                    setOnMenuItemClickListener {menuItem ->
                        when(menuItem.itemId){
                            R.id.popup_delete -> {
                                productViewModel.deleteSelectedProduct(product)
                                true
                            }
                            else -> true
                        }
                    }
                }.show()
            }
        }
    }
}