package io.interview.productlisttest

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.interview.productlisttest.model.State
import io.interview.productlisttest.util.JsonProcessor
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JsonProcessorTest {
    @Test
    fun jsonProcessor_StatusSuccess() {
        val json = "{" +
                "  \"status\": \"success\",\n" +
                "  \"message\": \"Success\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"response\": {\n" +
                "    \"product_count\": 20,\n" +
                "    \"total_found\": 60,\n" +
                "    \"offset\": 0,\n" +
                "    \"products\": [\n" +
                "      {\n" +
                "        \"rating\": 5,\n" +
                "        \"is_kit_combo\": null,\n" +
                "        \"object_type\": \"product\",\n" +
                "        \"is_saleable\": false,\n" +
                "        \"explore_more\": null,\n" +
                "        \"pack_size\": null,\n" +
                "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
                "        \"id\": \"278678\",\n" +
                "        \"sku\": \"9788184001471\",\n" +
                "        \"rating_count\": 1,\n" +
                "        \"from_gludo\": 1,\n" +
                "        \"fbn\": 0,\n" +
                "        \"gludo_stock\": false,\n" +
                "        \"offers\": null,\n" +
                "        \"mrp_freeze\": null,\n" +
                "        \"primary_categories\": {\n" +
                "          \"l2\": {\n" +
                "            \"id\": \"6619\",\n" +
                "            \"name\": \"Books\"\n" +
                "          },\n" +
                "          \"l3\": {\n" +
                "            \"id\": \"6621\",\n" +
                "            \"name\": \"Non Fiction\"\n" +
                "          },\n" +
                "          \"l1\": {\n" +
                "            \"id\": \"4362\",\n" +
                "            \"name\": \"Jewellery & Accessories\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"type\": \"simple\",\n" +
                "        \"final_price\": 299,\n" +
                "        \"expdt\": null,\n" +
                "        \"price\": 299,\n" +
                "        \"button_text\": \"ADD TO BAG\",\n" +
                "        \"brand_name\": \"\",\n" +
                "        \"discount\": 0,\n" +
                "        \"show_wishlist_button\": 0,\n" +
                "        \"slug\": \"from-xl-to-xs-a-fitness-guru-s-guide-to-changing-your-body-by-payal-gidwani-tiwari-paperback/p/278678\",\n" +
                "        \"is_lux\": 0,\n" +
                "        \"name\": \"From XL To XS: A Fitness Guru's Guide To Changing Your Body by Payal Gidwani Tiwari - Paperback\",\n" +
                "        \"pro_flag\": 0,\n" +
                "        \"dynamic_tags\": [],\n" +
                "        \"brand_ids\": \"6631\",\n" +
                "        \"is_backorder\": 0,\n" +
                "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184001471.jpg\",\n" +
                "        \"catalog_tag\": [\n" +
                "          \"nykaa\"\n" +
                "        ],\n" +
                "        \"quantity\": 0\n" +
                "      },\n" +
                "      {\n" +
                "        \"rating\": 4.5,\n" +
                "        \"is_kit_combo\": null,\n" +
                "        \"object_type\": \"product\",\n" +
                "        \"is_saleable\": false,\n" +
                "        \"explore_more\": null,\n" +
                "        \"pack_size\": null,\n" +
                "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
                "        \"id\": \"278690\",\n" +
                "        \"sku\": \"9780143420613\",\n" +
                "        \"rating_count\": 2,\n" +
                "        \"from_gludo\": 1,\n" +
                "        \"fbn\": 0,\n" +
                "        \"gludo_stock\": false,\n" +
                "        \"offers\": null,\n" +
                "        \"mrp_freeze\": null,\n" +
                "        \"primary_categories\": {\n" +
                "          \"l2\": {\n" +
                "            \"id\": \"6619\",\n" +
                "            \"name\": \"Books\"\n" +
                "          },\n" +
                "          \"l3\": {\n" +
                "            \"id\": \"6621\",\n" +
                "            \"name\": \"Non Fiction\"\n" +
                "          },\n" +
                "          \"l1\": {\n" +
                "            \"id\": \"4362\",\n" +
                "            \"name\": \"Jewellery & Accessories\"\n" +
                "          }\n" +
                "        },\n" +
                "        \"type\": \"simple\",\n" +
                "        \"final_price\": 299,\n" +
                "        \"expdt\": null,\n" +
                "        \"price\": 299,\n" +
                "        \"button_text\": \"ADD TO BAG\",\n" +
                "        \"brand_name\": \"\",\n" +
                "        \"discount\": 0,\n" +
                "        \"show_wishlist_button\": 0,\n" +
                "        \"slug\": \"the-book-of-woman-by-osho-paperback/p/278690\",\n" +
                "        \"is_lux\": 0,\n" +
                "        \"name\": \"The Book Of Woman by Osho - Paperback\",\n" +
                "        \"pro_flag\": 0,\n" +
                "        \"dynamic_tags\": [],\n" +
                "        \"brand_ids\": \"6631\",\n" +
                "        \"is_backorder\": 0,\n" +
                "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143420613.jpg\",\n" +
                "        \"catalog_tag\": [\n" +
                "          \"nykaa\"\n" +
                "        ],\n" +
                "        \"quantity\": 0\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}"
        val products = JsonProcessor.getProductsFromJson(json)
        println("products list size = ${products.size}")
        assertTrue("Status success - products loaded", products.isNotEmpty())
    }

    @Test
    fun jsonProcessor_StatusFailed() {
        val json = "{\n" +
                "  \"status\": \"failure\",\n" +
                "  \"message\": \"Success\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"response\": {\n" +
                "    \"product_count\": 0,\n" +
                "    \"total_found\": 0,\n" +
                "    \"offset\": 0\n" +
                "  }\n" +
                "}"
        val products = JsonProcessor.getProductsFromJson(json)
        assertTrue("Status failed - products not loaded", products.isEmpty())
    }

    @Test
    fun jsonProcessor_loadingStatsInProgress() {
        val json = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"message\": \"Success\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"response\": {\n" +
                "    \"product_count\": 20,\n" +
                "    \"total_found\": 60,\n" +
                "    \"offset\": 0\n" +
                "  }\n" +
                "}"
        val state = JsonProcessor.getLoadingStatsFromJson(json)
        assertTrue("More data is still there", state is State.Loading)
    }

    @Test
    fun jsonProcessor_loadingStatsComplete() {
        val json = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"message\": \"Success\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"response\": {\n" +
                "    \"product_count\": 20,\n" +
                "    \"total_found\": 60,\n" +
                "    \"offset\": 40\n" +
                "  }\n" +
                "}"
        val state = JsonProcessor.getLoadingStatsFromJson(json)
        assertTrue("No more data remaining", state !is State.Loading)
    }
}