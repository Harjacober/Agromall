package app.interview.agromall.utils

import android.content.Context
import android.content.SharedPreferences
import app.interview.agromall.R

class PrefsManager(var context: Context) {
    private lateinit var pref : SharedPreferences
    init {
        getSp()
    }
    companion object {
        const val DATA_DOWNLOADED : String = "data_downloaded_key"
        const val FARMER_ID : String = "farmer_id_key"
    }

    fun checkPref(): Boolean{
        return pref.getString(context.getString(R.string.pref_key),"null") != "null"
    }
    private fun getSp() {
        pref = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    }
    fun writeDownloadStatus(boolean: Boolean){
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putBoolean(DATA_DOWNLOADED, boolean)
        editor.commit()
    }
    fun dataIsDownloaded() : Boolean {
        return pref.getBoolean(DATA_DOWNLOADED, false)
    }
    fun saveCurrentId(farmerId: String){
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putString(FARMER_ID, farmerId)
        editor.commit()
    }
    fun getCurrentId() : String? {
        return pref.getString(FARMER_ID, "")
    }
}