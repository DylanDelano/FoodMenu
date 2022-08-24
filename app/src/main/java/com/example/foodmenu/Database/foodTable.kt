package com.example.foodmenu.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class foodTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name= "foodName")
    val foodName: String,
    @ColumnInfo(name= "chefName")
    val chefName: String,
    @ColumnInfo(name= "priceAmount")
    val priceAmount: String,
): Parcelable
