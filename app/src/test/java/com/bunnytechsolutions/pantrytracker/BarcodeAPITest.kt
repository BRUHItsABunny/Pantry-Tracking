package com.bunnytechsolutions.pantrytracker

import android.util.Log
import com.bunnytechsolutions.pantrytracker.barcodelookup.BarcodeAPI
import org.junit.Assert
import org.junit.Test

class BarcodeAPITest {

    companion object {
        const val BARCODE_API_KEY = "";
    }

    @Test
    fun lookup() {
        val client = BarcodeAPI(BARCODE_API_KEY)
        val barcode = "850009942371"
        val product = client.lookup(barcode)
        System.out.println(product.toString())
        Assert.assertNotNull("product", product)
        Assert.assertEquals("brand", product!!.brand, "BODYARMOR")
        Assert.assertEquals("name", product.name, "BODYARMOR Sports Drink Sports Beverage, Watermelon, Natural Flavors with Vitamins, Potassium-Packed Electrolytes, No Preservatives, Perfect for Athlet")
        Assert.assertEquals("barcode", product.barcode, "850009942371")
        Assert.assertEquals("imagesCount", product.imagesCount, 5)
        // Should output:
        /*
        barcode: "850009942371"
        brand: "BODYARMOR"
        name: "BODYARMOR Sports Drink Sports Beverage, Watermelon, Natural Flavors with Vitamins, Potassium-Packed Electrolytes, No Preservatives, Perfect for Athlet"
        images: "https://images.barcodelookup.com/26567/265677542-1.jpg"
        images: "https://images.barcodelookup.com/26567/265677542-2.jpg"
        images: "https://images.barcodelookup.com/26567/265677542-3.jpg"
        images: "https://images.barcodelookup.com/26567/265677542-4.jpg"
        images: "https://images.barcodelookup.com/26567/265677542-5.jpg"
         */
    }

}