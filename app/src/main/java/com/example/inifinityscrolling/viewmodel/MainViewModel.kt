package com.example.inifinityscrolling.viewmodel

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.inifinityscrolling.model.Photo
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.inifinityscrolling.network.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

 enum class STATE{
     LOADING,
     SUCCESS,
     FAILED

 }
class MainViewModel : ViewModel() {
     var photoResponse : List<Photo> by mutableStateOf(listOf())
    private var lastOffSet:Long  by mutableStateOf(0.toLong())
     private var errorMessage :String by mutableStateOf("ERROR")
var state  by mutableStateOf(STATE.LOADING)
     fun getPhotoList(){
      viewModelScope.launch {
          state = STATE.LOADING
          val apiService = RetrofitClient.getInstance()

          try{
              val apiResponse = apiService.getPhotos(lastOffSet)
              photoResponse = apiResponse.photos
              lastOffSet = photoResponse.size.toLong()
              state=STATE.SUCCESS
          }catch(e: Exception){
              errorMessage=e.message.toString()
              state= STATE.FAILED
          }
      }
    }
    suspend fun loadMorePhotoList(){
        viewModelScope.launch {
            state =STATE.LOADING
            delay(5000)
            val apiService = RetrofitClient.getInstance()

            try{
                val apiResponse = apiService.getPhotos(lastOffSet)
                photoResponse = photoResponse.plus(apiResponse.photos)
                lastOffSet = photoResponse.size.toLong()
                state =STATE.SUCCESS
            }catch(e: Exception){
                errorMessage=e.message.toString()
                state =STATE.FAILED
            }
        }
    }

}