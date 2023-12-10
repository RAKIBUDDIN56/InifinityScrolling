package com.example.inifinityscrolling.network

import com.example.inifinityscrolling.interfaces.IApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    companion object{
        private  var apiService: IApiService?=null
        fun getInstance(): IApiService{
            if(apiService == null){
                apiService =  Retrofit.Builder().baseUrl("https://api.slingacademy.com/v1/sample-data/").addConverterFactory(GsonConverterFactory.create()).build().create(IApiService::class.java)

            }
            return  apiService!!;
        }


    }
}
