package com.bunnytechsolutions.pantrytracker.barcodelookup

import com.bunnytechsolutions.pantrytracker.models.Product
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class BarcodeAPI(private val apiKey: String) {

    private val client: OkHttpClient = OkHttpClient()

    fun lookup(productBarcode: String) : Product? {
        val reqURL = "https://api.github.help".toHttpUrlOrNull()!!.newBuilder()
            .addQueryParameter("barcode", productBarcode)
            .addQueryParameter("key", apiKey)
            .build()
        val req = Request.Builder().url(reqURL).build()
        val response = client.newCall(req).execute()

        val responseJSON = JSONObject(response.body!!.string())
        val productsJSON = responseJSON.getJSONArray("products")
        if(productsJSON.length() > 0){
            val productJSON = productsJSON.getJSONObject(0)
            val builder = Product.newBuilder()
                .setBarcode(productJSON.getString("barcode_number"))
                .setName(productJSON.getString("title"))
                .setBrand(productJSON.getString("brand"))
                .setNutritionFacts(productJSON.getString("nutrition_facts"))
                .setIngredients(productJSON.getString("ingredients"))

            val productImageURLs = productJSON.getJSONArray("images")
            for (i in 0 until productImageURLs.length()) {
                builder.setImages(i, productImageURLs.getString(i))
            }

            return builder.build()
        }
        return null
    }

}