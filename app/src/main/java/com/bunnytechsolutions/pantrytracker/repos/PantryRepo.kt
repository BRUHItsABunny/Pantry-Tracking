package com.bunnytechsolutions.pantrytracker.repos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.bunnytechsolutions.pantrytracker.models.Pantry
import com.bunnytechsolutions.pantrytracker.models.PantryKt
import com.bunnytechsolutions.pantrytracker.models.Product
import java.util.*

class PantryRepo(private val dbHelper: DBHelper) {

    private var entriesRepo: EntryRepo? = null

    fun insertPantry(pantry: Pantry.Builder): Long {
        pantry.id = UUID.randomUUID().toString()
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PANTRY_UUID, pantry.id)
        contentValues.put(DBHelper.COL_PANTRY_NAME, pantry.name)
        contentValues.put(DBHelper.COL_PANTRY_OBJECT, pantry.build().toByteArray())
        val insertedID = db.insert(DBHelper.TBL_PANTRY, null, contentValues)
        db.close()
        return insertedID;
    }

    fun updateProduct(pantry: Pantry.Builder): Int {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PANTRY_UUID, pantry.id)
        contentValues.put(DBHelper.COL_PANTRY_NAME, pantry.name)
        contentValues.put(DBHelper.COL_PANTRY_OBJECT, pantry.build().toByteArray())
        val result = db.update(DBHelper.TBL_PANTRY, contentValues, "${DBHelper.COL_PANTRY_UUID} = ?", arrayOf(pantry.id))
        db.close()
        return result;
    }

    fun deleteProduct(pantry: Pantry.Builder): Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(DBHelper.TBL_PANTRY, "${DBHelper.COL_PANTRY_UUID} = ?", arrayOf(pantry.id))
        db.close()
        return result
    }

    fun getPantryByID(pantryID: Long): Pantry.Builder? {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PANTRY_OBJECT} FROM ${DBHelper.TBL_PANTRY} WHERE ${DBHelper.COL_PANTRY_ID} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryID.toString()))
        val results = parsePantriesFromCursor(cursor)

        if(results.isEmpty()) {
            return null
        }
        return results[0]
    }

    fun getPantryByUUID(pantryUUID: String): Pantry.Builder? {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PANTRY_OBJECT} FROM ${DBHelper.TBL_PANTRY} WHERE ${DBHelper.COL_PANTRY_UUID} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryUUID))
        val results = parsePantriesFromCursor(cursor)

        if(results.isEmpty()) {
            return null
        }
        return results[0]
    }

    fun getAllPantries() : Array<Pantry.Builder?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PRODUCT_OBJECT} FROM ${DBHelper.TBL_PANTRY}"
        val cursor = db.rawQuery(query, arrayOf())
        return parsePantriesFromCursor(cursor)
    }

    @SuppressLint("Range")
    private fun parsePantriesFromCursor(cursor: Cursor) : Array<Pantry.Builder?> {
        // We want an entriesRepo to get pantry entries TODO: Rethink
        if(entriesRepo == null) {
            entriesRepo = EntryRepo(this.dbHelper)
        }

        val result = arrayOfNulls<Pantry.Builder>(cursor.count)

        if (cursor.moveToFirst()){
            for (i in result.indices) {
                val objectBuilder =  Pantry.newBuilder().mergeFrom(cursor.getBlob(cursor.getColumnIndex(DBHelper.COL_PANTRY_OBJECT)))
                val entries = entriesRepo!!.getEntriesByPantryUUID(objectBuilder.id)
                for (element in entries) {
                    objectBuilder.addEntries(element!!.id)
                }
                result[i] = objectBuilder
                if(!cursor.moveToNext()){
                    break
                }
            }
        }
        cursor.close()

        return result
    }
}