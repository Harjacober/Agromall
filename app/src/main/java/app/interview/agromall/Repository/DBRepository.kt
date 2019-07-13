package app.interview.agromall.Repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.interview.agromall.database.AppDatabase
import app.interview.agromall.database.Farmer
import app.interview.agromall.utils.PrefsManager
import java.util.concurrent.Executors

class DBRepository(application: Application){
    val farmerData = MutableLiveData<Boolean>()
    val database: AppDatabase = AppDatabase.getAppDatabse(application)!!

    fun saveFarmersDataToDB(farmers: List<Farmer>, application: Application) : LiveData<Boolean> {
        InsertTask(application).execute(farmers)
        return farmerData
    }

    private inner class InsertTask(val application: Application) : AsyncTask<List<Farmer>, Void, Boolean>() {
        override fun doInBackground(vararg farmerList: List<Farmer>): Boolean? {
            try {
                val farmers = farmerList[0]
                database.farmerDao().insertAllFarmers(farmers)
                //save initial download status
                //PrefsManager(application).writeDownloadStatus(true)
                Log.e("jjjjjjjj", "success")
                PrefsManager(application).writeDownloadStatus(true)
                return true
            }catch (e : Exception){
                Log.e("jjjjjjjj", e.message)
                return false
            }
        }

        override fun onPostExecute(result: Boolean?) {
            farmerData.value = result
        }
    }

    fun getAllFarmers( ): LiveData<List<Farmer>> {
        val data = MutableLiveData<List<Farmer>>()
        val service = Executors.newSingleThreadExecutor()
        service.submit {
            val freshData =
                database.farmerDao().getAllFarmers()
            data.postValue(freshData)
        }
        return data
    }
    fun getLimitedFarmers(size: String ): LiveData<List<Farmer>> {
        val data = MutableLiveData<List<Farmer>>()
        val service = Executors.newSingleThreadExecutor()
        service.submit {
            val freshData =
                database.farmerDao().getLimitedFarmers(size.toInt())
            data.postValue(freshData)
        }
        return data
    }
    fun getSingleFarmerData(farmerId: String ): LiveData<Farmer> {
        val data = MutableLiveData<Farmer>()
        val service = Executors.newSingleThreadExecutor()
        service.submit {
            val freshData =
                database.farmerDao().getSingleFarmer(farmerId)
            data.postValue(freshData)
        }
        return data
    }
}