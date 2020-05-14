package com.ydhnwb.shoppingcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ydhnwb.shoppingcart.R
import com.ydhnwb.shoppingcart.models.Product
import com.ydhnwb.shoppingcart.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductAdapter (private var products : MutableList<Product>, private var context: Context, private var productViewModel: ProductViewModel)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false))
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position], context, productViewModel)


    fun updateList(ps : List<Product>){
        products.clear()
        products.addAll(ps)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(product: Product, context: Context, productViewModel: ProductViewModel){
            itemView.product_name.text = product.name
            itemView.product_price.text = product.price.toString()
            itemView.product_image.load(product.image)
            itemView.setOnClickListener {
                val p = product.copy()
                p.selectedQuantity = 1
                productViewModel.addSelectedProduct(p)
            }
        }
    }

}