package com.example.tenantmanagementsystem190050

import java.io.Serializable

// "Serializabilityy"
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {

    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent paid: KSh $rent"
    }
}