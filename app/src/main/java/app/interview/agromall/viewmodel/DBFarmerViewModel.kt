package app.interview.agromall.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import app.interview.agromall.Repository.DBRepository
import app.interview.agromall.database.Farmer

class DBFarmerViewModel(application: Application): AndroidViewModel(application){
    fun saveFarmersDataToDB(farmers: List<Farmer>) : LiveData<Boolean> {
        return DBRepository(getApplication()).saveFarmersDataToDB(farmers, getApplication())
    }

    fun getAllFarmers( ) : LiveData<List<Farmer>> {
        return DBRepository(getApplication()).getAllFarmers( )
    }
    fun getLimitedFarmers(size: String ) : LiveData<List<Farmer>> {
        return DBRepository(getApplication()).getLimitedFarmers(size )
    }
    fun getSngleFarmerData(farmerId: String ) : LiveData<Farmer> {
        return DBRepository(getApplication()).getSingleFarmerData(farmerId )
    }
}