package app.interview.agromall

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import app.interview.agromall.data.LatestDataRespose
import app.interview.agromall.viewmodel.DBFarmerViewModel
import app.interview.agromall.viewmodel.FarmerViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_app_bar.*
import kotlinx.android.synthetic.main.include_app_bar.view.*

class MainActivity : AppCompatActivity() {

    lateinit var toolBarTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        toolBarTitle = main_toolbar.toolbar_title

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination,_ ->
            when(destination.id) {
                R.id.allFarmersList -> {
                    //Do something with your toolbar or BottomAppBar
                    toolBarTitle.text = "Agro Farmers"
                }

            }
        }

    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

}
