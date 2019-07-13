package app.interview.agromall.destinations


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import app.interview.agromall.R
import app.interview.agromall.adapter.FarmersAdapter
import app.interview.agromall.data.LatestDataRespose
import app.interview.agromall.database.Farmer
import app.interview.agromall.utils.OnFarmerFragmentInteraction
import app.interview.agromall.utils.PrefsManager
import app.interview.agromall.viewmodel.DBFarmerViewModel
import app.interview.agromall.viewmodel.FarmerViewModel

class AllFarmersList : Fragment(), OnFarmerFragmentInteraction {


    lateinit var recyclerView: RecyclerView
    lateinit var loadIndicator: ProgressBar
    lateinit var adapter: FarmersAdapter
    lateinit var manager : PrefsManager
    var data: List<Farmer> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =inflater.inflate(R.layout.fragment_all_farmers_list, container, false)
        manager = PrefsManager(view.context)
        loadIndicator = view.findViewById(R.id.load_indicator)
        setUpRecyclerView(view)
        recyclerView.visibility  = View.INVISIBLE
        loadIndicator.visibility = View.VISIBLE
        return view
    }


    private fun setUpRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_farmers)
        adapter = FarmersAdapter(data, this )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fetchFarmersDataFromDB()
    }

    fun fetchFarmersDataFromDB(){
        val dbViewmodel =
            ViewModelProviders.of(this).get(DBFarmerViewModel::class.java)
        val liveData =
            dbViewmodel.getAllFarmers( )
        liveData.observe(this, Observer<List<Farmer>>{ farmers ->
            //Update the RecyclerView Here
            recyclerView.visibility = View.VISIBLE
            loadIndicator.visibility = View.INVISIBLE
            adapter.update(farmers)
        })
    }




    override fun onItemClicked(farmer_id: String) {
        manager.saveCurrentId(farmer_id)
        Navigation.findNavController(this.view!!).navigate(R.id.action_allFarmersList_to_farmerInfoFragment)
    }
}
