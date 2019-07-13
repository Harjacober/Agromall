package app.interview.agromall.destinations


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import app.interview.agromall.R
import app.interview.agromall.database.Farmer
import app.interview.agromall.utils.PrefsManager
import app.interview.agromall.viewmodel.DBFarmerViewModel
import kotlinx.android.synthetic.main.fragment_farmer_info.*
import kotlinx.android.synthetic.main.fragment_farmer_info.view.*
import kotlinx.android.synthetic.main.include_location_info.*
import kotlinx.android.synthetic.main.include_more_user_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FarmerInfoFragment : Fragment() {

    lateinit var manager : PrefsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_farmer_info, container, false)

        manager = PrefsManager(view.context)
        val farmerId = manager.getCurrentId()
        fetchFarmerInfo(view, farmerId!!)
        view.editInfo.setOnClickListener {
            //navigate to edit info screen
            Navigation.findNavController(this.view!!).navigate(R.id.action_farmerInfoFragment_to_editFarmerInfo)
        }
        return view
    }

    private fun fetchFarmerInfo(view: View?, farmerId: String) {
        val viewModel = ViewModelProviders.of(this).get(DBFarmerViewModel::class.java)
        val liveData = viewModel.getSngleFarmerData(farmerId = farmerId)
        liveData.observe(this, Observer<Farmer> {farmer ->
            tv_user_name.text = farmer.surname + " " + farmer.firstName
            tv_user_location.text = farmer.address
            tv_occupation.text = farmer.occupation
            tv_marital_status.text = farmer.maritalStatus
            tv_user_email.text = farmer.emailAddress
            tv_user_phone.text = farmer.mobileNo
            tv_state.text = farmer.state
            tv_city.text = farmer.city

        })


    }


}
