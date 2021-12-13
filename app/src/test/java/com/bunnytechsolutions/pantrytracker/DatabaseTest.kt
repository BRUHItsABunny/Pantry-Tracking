package com.bunnytechsolutions.pantrytracker

import android.os.Build.VERSION_CODES.LOLLIPOP
import com.bunnytechsolutions.pantrytracker.models.Pantry
import com.bunnytechsolutions.pantrytracker.repos.DBHelper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(LOLLIPOP), packageName = "com.bunnytechsolutions.pantrytracker")
class DatabaseTest {

    lateinit var db: DBHelper

    @Before
    fun init() {
        ShadowLog.stream = System.out
    }

    @Before
    fun setup() {
        val context = RuntimeEnvironment.application
        db = DBHelper(context)
    }

    @Test
    @Throws(Exception::class)
    fun insertPantryTest() {
        val pantryRepo = db.getPantryRepo()
        val pantryObj = Pantry.newBuilder().setName("testPantry1")
        val result = pantryRepo.insertPantry(pantryObj)
        Assert.assertEquals("insertedID (testPantry1)", result, 1)
        Assert.assertNotEquals("pantryUUID (testPantry1)", pantryObj.id, "")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndSelectPantries() {
        val pantryRepo = db.getPantryRepo()
        val pantryObs = arrayListOf<Pantry.Builder>(Pantry.newBuilder().setName("testPantry1"), Pantry.newBuilder().setName("testPantry2"))
        for (pantryObj in pantryObs){
            val result = pantryRepo.insertPantry(pantryObj)
            Assert.assertNotEquals("insertedID (${pantryObj.name})", result, -1)
            ShadowLog.stream.println(pantryObj.toString())
        }

        val pantries = pantryRepo.getAllPantries()
        Assert.assertEquals("pantries in vs pantries out", pantryObs.size, pantries.size)
        for (pantry in pantries){
            ShadowLog.stream.println(pantry.toString())
        }
    }

    @After
    fun tearDown() {
        val context = RuntimeEnvironment.application
        db.deleteDatabase(context)
        db.close()
    }
}