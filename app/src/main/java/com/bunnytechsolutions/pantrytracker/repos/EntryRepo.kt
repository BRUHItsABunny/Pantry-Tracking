package com.bunnytechsolutions.pantrytracker.repos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.bunnytechsolutions.pantrytracker.models.Entry
import java.util.*

class EntryRepo(private val dbHelper: DBHelper) {

    fun insertEntry(pantryUUID: String, entry: Entry.Builder): Long {
        entry.id = UUID.randomUUID().toString()
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_ENTRY_UUID, entry.id)
        contentValues.put(DBHelper.COL_ENTRY_EXPIRES, entry.expires.seconds * 1000)
        contentValues.put(DBHelper.COL_ENTRY_PANTRY_UUID, pantryUUID)
        contentValues.put(DBHelper.COL_ENTRY_PRODUCT_BARCODE, entry.productCode)
        contentValues.put(DBHelper.COL_ENTRY_OBJECT, entry.build().toByteArray())
        val insertedID = db.insert(DBHelper.TBL_ENTRY, null, contentValues)
        db.close()
        return insertedID;
    }

    fun updateEntry(entry: Entry.Builder): Int {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_ENTRY_UUID, entry.id)
        contentValues.put(DBHelper.COL_ENTRY_EXPIRES, entry.expires.seconds * 1000)
        contentValues.put(DBHelper.COL_ENTRY_PRODUCT_BARCODE, entry.productCode)
        contentValues.put(DBHelper.COL_ENTRY_OBJECT, entry.build().toByteArray())
        val result = db.update(DBHelper.TBL_ENTRY, contentValues, "${DBHelper.COL_ENTRY_UUID} = ?", arrayOf(entry.id))
        db.close()
        return result;
    }

    fun deleteEntry(entry: Entry): Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(DBHelper.TBL_ENTRY, "${DBHelper.COL_ENTRY_UUID} = ?", arrayOf(entry.id))
        db.close()
        return result
    }

    fun getEntryByUUID(entryUUID: String): Entry.Builder? {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_ENTRY_OBJECT} FROM ${DBHelper.TBL_ENTRY} WHERE ${DBHelper.COL_ENTRY_UUID} = ?"
        val cursor = db.rawQuery(query, arrayOf(entryUUID))
        val results = parseEntriesFromCursor(cursor)

        if(results.isEmpty()) {
            return null
        }
        return results[0]
    }

    fun getEntriesByPantryUUID(pantryUUID: String) : Array<Entry.Builder?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_ENTRY_OBJECT} FROM ${DBHelper.TBL_ENTRY} WHERE ${DBHelper.COL_ENTRY_PANTRY_UUID} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryUUID))
        return parseEntriesFromCursor(cursor)
    }

    fun getEntriesByPantryUUIDAndProductCode(pantryUUID: String, productCode: String) : Array<Entry.Builder?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_ENTRY_OBJECT} FROM ${DBHelper.TBL_ENTRY} WHERE ${DBHelper.COL_ENTRY_PANTRY_UUID} = ? AND ${DBHelper.COL_ENTRY_PRODUCT_BARCODE} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryUUID, productCode))
        return parseEntriesFromCursor(cursor)
    }

    fun getExpiredEntriesByPantryUUID(pantryUUID: String, timeMillis: Long) : Array<Entry.Builder?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_ENTRY_OBJECT} FROM ${DBHelper.TBL_ENTRY} WHERE ${DBHelper.COL_ENTRY_PANTRY_UUID} = ? AND ${DBHelper.COL_ENTRY_EXPIRES} = ?"
        val cursor = db.rawQuery(query, arrayOf(pantryUUID, timeMillis.toString()))
        return parseEntriesFromCursor(cursor)
    }

    @SuppressLint("Range")
    private fun parseEntriesFromCursor(cursor: Cursor) : Array<Entry.Builder?> {
        val result = arrayOfNulls<Entry.Builder>(cursor.count)

        if (cursor.moveToFirst()){
            for (i in result.indices) {
                val objectBuilder =  Entry.newBuilder().mergeFrom(cursor.getBlob(cursor.getColumnIndex(DBHelper.COL_ENTRY_OBJECT)))
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