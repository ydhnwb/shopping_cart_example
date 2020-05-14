package com.ydhnwb.shoppingcart.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    var id : Int? = null,
    var name : String? = null,
    var price : Int? = null,
    var image : String? = null,
    var selectedQuantity : Int? = null
) : Parcelable