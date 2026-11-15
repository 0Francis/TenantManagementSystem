package com.example.tenantmanagementsystemgroup1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tenants")
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

) : Serializable {

    fun getFormattedRent(): String {
        val amount = rent.replace(",", "").toDoubleOrNull() ?: 0.0
        return "KSh %,.0f".format(amount)
    }

    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: ${getFormattedRent()}"
    }
}