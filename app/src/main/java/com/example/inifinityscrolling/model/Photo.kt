package com.example.inifinityscrolling.model

import com.google.gson.annotations.SerializedName

 class Photo {
     @SerializedName("url"         ) var url         : String? = null
     @SerializedName("title"       ) var title       : String? = null
     @SerializedName("user"        ) var user        : Int?    = null
     @SerializedName("id"          ) var id          : Int?    = null
     @SerializedName("description" ) var description : String? = null
 }
