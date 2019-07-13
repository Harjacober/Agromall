package app.interview.agromall.Repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.interview.agromall.data.LatestDataRespose
import app.interview.agromall.database.AppDatabase
import app.interview.agromall.database.Farmer
import app.interview.agromall.remote.APIService
import app.interview.agromall.utils.APIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: APIService = APIUtils.apiService

    private object SingletonHelper {
        val INSTANCE = Repository()
    }

    companion object {
        fun getInstance() : Repository{
            return SingletonHelper.INSTANCE
        }
    }

    fun fetchFarmers(limit:String, page:String) : LiveData<LatestDataRespose> {
        val data = MutableLiveData<LatestDataRespose>()
        apiService.fetchData(limit = limit, page = page).enqueue(
            object : Callback<LatestDataRespose> {
                override fun onResponse(
                    call: Call<LatestDataRespose>,
                    response: Response<LatestDataRespose>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<LatestDataRespose>, t: Throwable) {
                    data.value = null
                }
            }
        )
        return data
    }


}