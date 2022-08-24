package com.example.foodmenu.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [foodTable::class], version = 2, exportSchema = false)
abstract class MenuDatabase :RoomDatabase() {
    abstract fun FoodDao():foodDao

    companion object{
        @Volatile
        private var INSTANCE:MenuDatabase? = null
        val MIGRATION_1_2 = object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE food_new_table(id INTEGER NOT NULL ,foodName TEXT NOT NULL, chefName TEXT NOT NULL, priceAmount TEXT NOT NULL , PRIMARY KEY(id)) ")
                //copy
                database.execSQL( "INSERT INTO food_new_table(id, foodName,chefName,priceAmount) SELECT id,foodName,chefName,priceAmount FROM foodTable")
                //remove
                database.execSQL("DROP TABLE foodTable")
                //change
                database.execSQL("ALTER TABLE food_new_table RENAME TO foodTable")
            }
        }


        fun getDatabase(context: Context):MenuDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, MenuDatabase::class.java, "menu_database")
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }


}