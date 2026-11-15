package com.example.tenantmanagementsystemgroup1.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tenantmanagementsystemgroup1.data.local.TenantDatabase
import com.example.tenantmanagementsystemgroup1.data.model.Tenant
import com.example.tenantmanagementsystemgroup1.data.repository.TenantRepository
import com.example.tenantmanagementsystemgroup1.util.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * TEST TYPE: SYSTEM / INTEGRATION TEST
 *  -------------------------------------------------------------------
 * Purpose: Tests the full end-to-end data pipeline system connecting the
 * Room Database, TenantRepository, and LiveData reactive observer streams.
 * Verifies data flow integrity, aggregation, and reactive updates across layers.
 */
@RunWith(AndroidJUnit4::class)
class TenantDatabaseSystemTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: TenantDatabase
    private lateinit var repository: TenantRepository

    @Before
    fun setupSystem() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TenantDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        repository = TenantRepository(db.tenantDao())
    }

    @After
    @Throws(IOException::class)
    fun tearDownSystem() {
        db.close()
    }

    @Test
    fun testFullTenantRepositoryDataPipeline() = runBlocking {
        // Step 1: Verify database starts empty via LiveData stream
        val initialList = repository.tenants.getOrAwaitValue()
        assertTrue("Database should initially be empty", initialList.isEmpty())

        // Step 2: System action — insert multiple tenants through repository
        val t1 = Tenant(name = "Grace Hopper", phone = "0711223344", rent = "35000")
        val t2 = Tenant(name = "Ada Lovelace", phone = "0755667788", rent = "45000")

        repository.addTenant(t1)
        repository.addTenant(t2)

        // Step 3: Observe reactive LiveData output stream update
        val updatedList = repository.tenants.getOrAwaitValue()

        assertEquals("System should contain exactly 2 tenants", 2, updatedList.size)

        // Step 4: System aggregation verification (Total Rent Calculation)
        val totalRent = updatedList.sumOf { it.rent.replace(",", "").toDoubleOrNull() ?: 0.0 }
        assertEquals(80000.0, totalRent, 0.01)

        // Step 5: Verify entity properties retained accurately
        val names = updatedList.map { it.name }
        assertTrue(names.contains("Grace Hopper"))
        assertTrue(names.contains("Ada Lovelace"))
    }

    @Test
    fun testDatabaseClearAndResetSystemFlow() = runBlocking {
        // Step 1: Add records into system
        repository.addTenant(Tenant(name = "Sample Tenant", phone = "0799887766", rent = "18000"))
        val listBefore = repository.tenants.getOrAwaitValue()
        assertEquals(1, listBefore.size)

        // Step 2: Perform system clear operation
        repository.deleteAllTenants()

        // Step 3: Verify system LiveData stream reacts instantly with an empty list
        val listAfter = repository.tenants.getOrAwaitValue()
        assertTrue("LiveData stream should reflect empty state after clearing database", listAfter.isEmpty())
    }
}