package app.interview.agromall

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.interview.agromall.data.LatestDataRespose
import app.interview.agromall.utils.NetworkUtils
import app.interview.agromall.utils.PrefsManager
import app.interview.agromall.viewmodel.DBFarmerViewModel
import app.interview.agromall.viewmodel.FarmerViewModel
import kotlinx.android.synthetic.main.activity_app_entry_point.*
import kotlinx.android.synthetic.main.include_app_bar.view.*

class AppEntryPoint : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    lateinit var manager : PrefsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_entry_point)

        //get All Data From Server
        manager = PrefsManager(this)
        if(manager.dataIsDownloaded()){
            launchCorrespondingActivity()
        }else{
            downloadDataFromServer("20", "1")
        }

        main_appbar.toolbar_title.text = "Agromall"
        main_appbar.refresh.visibility = View.VISIBLE
        main_appbar.refresh.setOnClickListener {
            downloadDataFromServer("20", "1")
        }
    }

    fun downloadDataFromServer(limit: String, page:String){
        if (NetworkUtils.isConnected(this)) {
            progressDialog = ProgressDialog.show(
                this,
                "Fetching Data",
                "Processing..."
            )
            val viewmodel = ViewModelProviders.of(this).get(FarmerViewModel::class.java)
            val liveData = viewmodel.fetchFarmers(limit, page)
            liveData.observe(this, Observer<LatestDataRespose> { response ->
                if (response.status!!.toBoolean()) {
                    val data = response.data
                    if (data != null) {
                        val farmers = data.farmers
                        val dbViewmodel = ViewModelProviders.of(this).get(DBFarmerViewModel::class.java)
                        val dbLiveData = dbViewmodel.saveFarmersDataToDB(farmers!!)
                        dbLiveData.observe(this, Observer<Boolean> { result ->
                            if (result) {
                                //Data Saved to DB successfully
                                //navigate to corresponding screen
                                progressDialog.dismiss()
                                launchCorrespondingActivity()

                            } else {
                                progressDialog.dismiss()
                                Toast.makeText(this, "Check your Internet and Refresh", Toast.LENGTH_SHORT).show()
                                Log.d("response:", "Data not saved to DB")
                            }
                        })
                    }


                } else {
                    Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show()
                }

            })
        }else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun launchCorrespondingActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
