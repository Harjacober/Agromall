package app.interview.agromall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.interview.agromall.Repository.Repository
import app.interview.agromall.data.LatestDataRespose

class FarmerViewModel : ViewModel(){
    fun fetchFarmers(limit:String, page:String): LiveData<LatestDataRespose> {
        return Repository.getInstance().fetchFarmers(limit, page)
    }
}