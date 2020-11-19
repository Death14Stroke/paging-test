package io.interview.productlisttest.util

import android.util.Log
import io.interview.productlisttest.model.Product
import io.interview.productlisttest.model.State
import org.json.JSONObject

object JsonProcessor {
    fun getProductsFromJson(jsonString: String): List<Product> {
        val json = JSONObject(jsonString)
        val status = json.getString("status")
        if (status != "success")
            return emptyList()

        val resp = json.getJSONObject("response")
        val productsJson = resp.getJSONArray("products") ?: return emptyList()

        val products = mutableListOf<Product>()
        for (i in 0 until productsJson.length()) {
            val product = productsJson.getJSONObject(i).run {
                val price = getInt("price")
                val imageUrl = getString("image_url")
                val name = getString("name")

                Product(
                        title = name,
                        price = price,
                        imageUrl = imageUrl
                )
            }

            products.add(product)
        }

        return products.toList()
    }

    fun getLoadingStatsFromJson(jsonString: String): State {
        val json = JSONObject(jsonString)
        val status = json.getString("status")
        if (status != "success")
            return State.Success

        return json.getJSONObject("response").run {
            val totalCount = getInt("total_found")
            val count = getInt("product_count")
            val offset = getInt("offset")

            Log.d("processLog", "$count with offset $offset = $totalCount")

            if (count + offset == totalCount)
                State.Success
            else
                State.Loading(loadedItems = count + offset, totalItems = totalCount)
        }
    }
}