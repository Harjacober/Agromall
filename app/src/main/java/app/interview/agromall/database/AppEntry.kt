package app.interview.agromall.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Agromall_db")
class Farmer {

    //TODO add columifo annotation to all other fields
    @NonNull
    @PrimaryKey
    @SerializedName("farmer_id")
    @Expose
    @ColumnInfo(name = "farmer_id")
    var farmerId: String? = null
    @SerializedName("reg_no")
    @Expose
    var regNo: String? = null
    @SerializedName("bvn")
    @Expose
    var bvn: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("middle_name")
    @Expose
    var middleName: String? = null
    @SerializedName("surname")
    @Expose
    var surname: String? = null
    @SerializedName("dob")
    @Expose
    var dob: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("nationality")
    @Expose
    var nationality: String? = null
    @SerializedName("occupation")
    @Expose
    var occupation: String? = null
    @SerializedName("marital_status")
    @Expose
    var maritalStatus: String? = null
    @SerializedName("spouse_name")
    @Expose
    var spouseName: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("lga")
    @Expose
    var lga: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("mobile_no")
    @Expose
    var mobileNo: String? = null
    @SerializedName("email_address")
    @Expose
    var emailAddress: String? = null
    @SerializedName("id_type")
    @Expose
    var idType: String? = null
    @SerializedName("id_no")
    @Expose
    var idNo: String? = null
    @SerializedName("issue_date")
    @Expose
    var issueDate: String? = null
    @SerializedName("expiry_date")
    @Expose
    var expiryDate: String? = null
    @SerializedName("id_image")
    @Expose
    var idImage: String? = null
    @SerializedName("passport_photo")
    @Expose
    var passportPhoto: String? = null
    @SerializedName("fingerprint")
    @Expose
    var fingerprint: String? = null

}