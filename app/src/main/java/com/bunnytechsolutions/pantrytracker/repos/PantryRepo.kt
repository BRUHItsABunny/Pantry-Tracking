package com.bunnytechsolutions.pantrytracker.repos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.bunnytechsolutions.pantrytracker.models.Pantry
import com.bunnytechsolutions.pantrytracker.models.Product

class PantryRepo(private val dbHelper: DBHelper) {

    fun insertPantry(pantry: Pantry): Long {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PANTRY_UUID, pantry.id)
        contentValues.put(DBHelper.COL_PANTRY_NAME, pantry.name)
        contentValues.put(DBHelper.COL_PANTRY_OBJECT, pantry.toByteArray())
        val insertedID = db.insert(DBHelper.TBL_PANTRY, null, contentValues)
        db.close()
        return insertedID;
    }

    fun updateProduct(pantry: Pantry): Int {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PANTRY_UUID, pantry.id)
        contentValues.put(DBHelper.COL_PANTRY_NAME, pantry.name)
        contentValues.put(DBHelper.COL_PANTRY_OBJECT, pantry.toByteArray())
        val result = db.update(DBHelper.TBL_PANTRY, contentValues, "${DBHelper.COL_PANTRY_UUID} = ?", arrayOf(pantry.id))
        db.close()
        return result;
    }

    fun deleteProduct(pantry: Pantry): Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(DBHelper.TBL_PANTRY, "${DBHelper.COL_PANTRY_UUID} = ?", arrayOf(pantry.id))
        db.close()
        return result
    }

    fun getPantryByUUID(pantryUUID: String): Pantry? {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PANTRY_OBJECT} FROM ${DBHelper.TBL_PANTRY} WHERE ${DBHelper.COL_PANTRY_UUID} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryUUID))
        val results = parsePantriesFromCursor(cursor)

        if(results.isEmpty()) {
            return null
        }
        return results[0]
    }

    fun getAllPantries() : Array<Pantry?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PRODUCT_OBJECT} FROM ${DBHelper.TBL_PRODUCT}"
        val cursor = db.rawQuery(query, arrayOf())
        return parsePantriesFromCursor(cursor)
    }

    @SuppressLint("Range")
    private fun parsePantriesFromCursor(cursor: Cursor) : Array<Pantry?> {
        val result = arrayOfNulls<Pantry>(cursor.count)

        if (cursor.moveToFirst()){
            for (i in result.indices) {
                val objectBuilder =  Pantry.newBuilder().mergeFrom(cursor.getBlob(cursor.getColumnIndex(DBHelper.COL_PANTRY_OBJECT)))
                result[i] = objectBuilder.build()
                if(!cursor.moveToNext()){
                    break
                }
            }
        }
        cursor.close()

        return result
    }
}