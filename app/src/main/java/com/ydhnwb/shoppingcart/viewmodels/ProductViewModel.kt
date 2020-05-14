package com.ydhnwb.shoppingcart.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydhnwb.shoppingcart.models.Product

class ProductViewModel : ViewModel(){
    private var products = MutableLiveData<List<Product>>()
    private var selectedProducts = MutableLiveData<List<Product>>()

    init {
        products.postValue(mutableListOf())
        selectedProducts.postValue(mutableListOf())
    }

    fun fetchDummyProduct(){
        //pura2nya dari API
        val dummies = mutableListOf<Product>().apply {
            add(Product(1, "Beng beng", 3000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
            add(Product(2, "Indomie goreng", 3500, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
            add(Product(3, "Garam", 3000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
            add(Product(4, "Tissue magic", 8000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
            add(Product(5, "Sprite", 6000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
            add(Product(6, "Kopi", 6000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt_400x400.jpg"))
        }
        products.postValue(dummies)

    }

    fun addSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        } ?: kotlin.run {
            tempSelectedProducts.add(product)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun decrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            if(it.selectedQuantity?.minus(1) == 0){
                tempSelectedProducts.remove(it)
            }else{
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun incrementQuantityProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun deleteSelectedProduct(product: Product){
        val tempSelectedProducts : MutableList<Product> = if (selectedProducts.value == null){
            mutableListOf()
        }else{
            selectedProducts.value as MutableList<Product>
        }
        val sameProduct : Product? = tempSelectedProducts.find { p ->
            p.id == product.id
        }
        sameProduct?.let {
            tempSelectedProducts.remove(it)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun listenToProducts() = products
    fun listenToSelectedProduct() = selectedProducts

}