package com.bunnytechsolutions.pantrytracker.repos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PANTRY_DB"

        const val TBL_PANTRY = "pantries"
        const val COL_PANTRY_ID = "id"
        const val COL_PANTRY_UUID = "uuid"
        const val COL_PANTRY_NAME = "name"
        const val COL_PANTRY_OBJECT = "object"

        const val TBL_PRODUCT = "products"
        const val COL_PRODUCT_ID = "id"
        const val COL_PRODUCT_BARCODE = "barcode"
        const val COL_PRODUCT_NAME = "name"
        const val COL_PRODUCT_BRAND = "brand"
        const val COL_PRODUCT_INGREDIENTS = "ingredients"
        const val COL_PRODUCT_NUTRITION = "nutrition"
        const val COL_PRODUCT_IMAGES = "images"
        const val COL_PRODUCT_OBJECT = "object"

        const val TBL_ENTRY = "entries"
        const val COL_ENTRY_ID = "id"
        const val COL_ENTRY_UUID = "uuid"
        const val COL_ENTRY_PANTRY_UUID = "pantry_uuid"
        const val COL_ENTRY_PRODUCT_BARCODE = "product_barcode"
        const val COL_ENTRY_EXPIRES = "expires"
        const val COL_ENTRY_OBJECT = "object"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var tblQuery = ("CREATE TABLE " + TBL_PRODUCT + "("
                + COL_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PRODUCT_BARCODE + " TEXT,"
                + COL_PRODUCT_NAME + " TEXT,"
                + COL_PRODUCT_BRAND + " TEXT,"
                + COL_PRODUCT_INGREDIENTS + " TEXT,"
                + COL_PRODUCT_NUTRITION + " TEXT,"
                + COL_PRODUCT_IMAGES + " TEXT,"
                + COL_PRODUCT_OBJECT + " BLOB"
                + ")")
        db?.execSQL(tblQuery)
        tblQuery = ("CREATE TABLE " + TBL_PANTRY + "("
                + COL_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_PANTRY_UUID + " TEXT,"
                + COL_PANTRY_NAME + " TEXT,"
                + COL_PANTRY_OBJECT + " BLOB"
                + ")")
        db?.execSQL(tblQuery)
        tblQuery = ("CREATE TABLE " + TBL_ENTRY + "("
                + COL_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_ENTRY_UUID + " TEXT,"
                + COL_ENTRY_PANTRY_UUID + " TEXT,"
                + COL_ENTRY_PRODUCT_BARCODE + " TEXT,"
                + COL_ENTRY_EXPIRES + " INTEGER,"
                + COL_ENTRY_OBJECT + " BLOB"
                + ")")
        db?.execSQL(tblQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun getEntryRepo() : EntryRepo {
        return EntryRepo(this)
    }

    fun getPantryRepo() : PantryRepo {
        return PantryRepo(this)
    }

    fun getProductRepo() : ProductRepo {
        return ProductRepo(this)
    }
}