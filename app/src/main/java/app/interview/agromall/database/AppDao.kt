package app.interview.agromall.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import app.interview.agromall.data.LatestDataRespose



@Dao
interface AppDao {

    @Insert
    fun insertAllFarmers(subjectLocal: List<Farmer>)
    //Update Farmers Data
    @Update
    fun update(farmer: Farmer)

    //Get All Farmers in DB
    @Query("SELECT * FROM Agromall_db")
    fun getAllFarmers(): List<Farmer>

    //Get specific number of Farmers
    @Query("SELECT * FROM Agromall_db LIMIT :size")
    fun getLimitedFarmers(size : Int) : List<Farmer>

    //Get specific number of Farmers
    @Query("SELECT * FROM Agromall_db WHERE farmer_id=:farmerId")
    fun getSingleFarmer(farmerId : String) : Farmer


    //delete all data
    @Query("DELETE FROM Agromall_db")
    fun deleteAllData()

}
