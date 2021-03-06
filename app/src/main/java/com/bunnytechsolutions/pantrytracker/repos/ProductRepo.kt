package com.bunnytechsolutions.pantrytracker.repos

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.bunnytechsolutions.pantrytracker.models.Product
import java.util.*

class ProductRepo(private val dbHelper: DBHelper) {

    fun insertProduct(product: Product.Builder): Long {
        if(product.barcode.isEmpty()) {
            product.barcode = UUID.randomUUID().toString()
        }
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PRODUCT_BARCODE, product.barcode)
        contentValues.put(DBHelper.COL_PRODUCT_NAME, product.name)
        contentValues.put(DBHelper.COL_PRODUCT_BRAND, product.brand)
        contentValues.put(DBHelper.COL_PRODUCT_OBJECT, product.build().toByteArray())
        val insertedID = db.insert(DBHelper.TBL_PRODUCT, null, contentValues)
        db.close()
        return insertedID;
    }

    fun updateProduct(product: Product.Builder): Int {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBHelper.COL_PRODUCT_BARCODE, product.barcode)
        contentValues.put(DBHelper.COL_PRODUCT_NAME, product.name)
        contentValues.put(DBHelper.COL_PRODUCT_BRAND, product.brand)
        contentValues.put(DBHelper.COL_PRODUCT_OBJECT, product.build().toByteArray())
        val result = db.update(DBHelper.TBL_PRODUCT, contentValues, "${DBHelper.COL_PRODUCT_BARCODE} = ?", arrayOf(product.barcode))
        db.close()
        return result;
    }

    fun deleteProduct(product: Product.Builder): Int {
        val db = dbHelper.writableDatabase
        val result = db.delete(DBHelper.TBL_PRODUCT, "${DBHelper.COL_PRODUCT_BARCODE} = ?", arrayOf(product.barcode))
        db.close()
        return result
    }

    fun getProductByBarcode(productBarcode: String): Product.Builder? {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PRODUCT_OBJECT} FROM ${DBHelper.TBL_PRODUCT} WHERE ${DBHelper.COL_PRODUCT_BARCODE} = ?"
        val cursor = db.rawQuery(query, arrayOf(productBarcode))
        val results = parseProductsFromCursor(cursor)

        if(results.isEmpty()) {
            return null
        }
        return results[0]
    }

    fun getAllProducts() : Array<Product.Builder?> {
        val db = dbHelper.readableDatabase
        val query = "SELECT ${DBHelper.COL_PRODUCT_OBJECT} FROM ${DBHelper.TBL_PRODUCT}"
        val cursor = db.rawQuery(query, arrayOf())
        return parseProductsFromCursor(cursor)
    }

    @SuppressLint("Range")
    private fun parseProductsFromCursor(cursor: Cursor) : Array<Product.Builder?> {
        val result = arrayOfNulls<Product.Builder>(cursor.count)

        if (cursor.moveToFirst()){
            for (i in result.indices) {
                val objectBuilder =  Product.newBuilder().mergeFrom(cursor.getBlob(cursor.getColumnIndex(DBHelper.COL_PRODUCT_OBJECT)))
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