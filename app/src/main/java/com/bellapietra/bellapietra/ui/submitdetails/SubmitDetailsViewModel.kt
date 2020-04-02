package com.bellapietra.bellapietra.ui.submitdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellapietra.bellapietra.network.BellaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SubmitDetailsViewModel : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _detailsSubmitted = MutableLiveData<String>()
    val detailsSubmitted:LiveData<String>
    get() = _detailsSubmitted

    fun submitDetails(name:String, email:String, phone:String){
        uiScope.launch {

            val getMessageDeferred = BellaApi.retrofitService.sendCustomerDetailsAsync(name, email, phone)
            try {
                val details = getMessageDeferred.await()
                _detailsSubmitted.value = details.message
            }catch (e:Exception){
                Timber.e("Error submitted details")
                _detailsSubmitted.value = "Failed to submit details, try again"
            }
        }
    }

    //clear live data value after dialog is closed
    fun disMissDialog(){
        _detailsSubmitted.value = null
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
