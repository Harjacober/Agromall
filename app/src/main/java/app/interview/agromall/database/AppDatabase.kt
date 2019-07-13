package app.interview.agromall.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.interview.agromall.data.LatestDataRespose

@Database(entities = [Farmer::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun farmerDao(): AppDao

    /**We need static access to the methods inhere*/
    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getAppDatabse(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "Agromall").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}