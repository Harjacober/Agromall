package app.interview.agromall.data

import app.interview.agromall.database.Farmer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Data {

    @SerializedName("farmers")
    @Expose
    var farmers: List<Farmer>? = null
    @SerializedName("totalRec")
    @Expose
    var totalRec: Int? = null
    @SerializedName("imageBaseUrl")
    @Expose
    var imageBaseUrl: String? = null

}

class LatestDataRespose {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

}