package app.interview.agromall.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.interview.agromall.R
import app.interview.agromall.database.Farmer
import app.interview.agromall.utils.OnFarmerFragmentInteraction
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_farmer.view.*
import java.lang.Exception

class FarmersAdapter(var data: List<Farmer>,
                       var listener: OnFarmerFragmentInteraction )
    : RecyclerView.Adapter<FarmersAdapter.DataObjectHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataObjectHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_farmer, parent, false
        )
        return DataObjectHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) {

        holder.bind(data[position], listener)
    }

    fun update(allFarmmers: List<Farmer>? ) {
        data = allFarmmers!!
        notifyDataSetChanged()
    }


    inner class DataObjectHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(farmer: Farmer,
                 listener: OnFarmerFragmentInteraction
        ){
            itemView.farmerName.text = farmer.surname + " " + farmer.firstName
            itemView.address.text = farmer.address
            //format Date
            itemView.issuedDate.text = farmer.issueDate
            val profileImage = itemView.findViewById<ImageView>(R.id.profileImage)
            Picasso.get().load(farmer.idImage)
                .resize(100, 100).into(profileImage,
                    object : com.squareup.picasso.Callback {
                        override fun onError(e: Exception?) {
                           profileImage.setImageResource(R.drawable.ic_boy)
                            Log.e("dddddd", e.toString())

                        }

                        override fun onSuccess() {
                            //DO nothing
                        }
                    })
            itemView.setOnClickListener {
                listener.onItemClicked(farmer.farmerId.toString())
            }
        }
    }
}