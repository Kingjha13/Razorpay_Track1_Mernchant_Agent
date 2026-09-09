package com.mernchant_agent_backend

data class ProductDetails(val name: String,val description : String,val category : String,
    val brand : String,val color : String,val price : Int,val rating : Double,val reviewCount : Int)