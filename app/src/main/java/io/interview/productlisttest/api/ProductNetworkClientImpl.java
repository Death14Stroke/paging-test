package io.interview.productlisttest.api;

import android.util.Log;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductNetworkClientImpl implements ProductNetworkClient {

    // <editor-fold defaultstate="collapsed" desc="Only need to look at the interface">
    private static final Random RANDOM = new Random();

    private static ProductNetworkClient INSTANCE;

    public static synchronized ProductNetworkClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProductNetworkClientImpl();
        return INSTANCE;
    }

    @Override
    public String waitForProductListByPageNo(int pageNumber) throws IOException {
        randomDelay();
        maybeThrowError();
        String data =null;
        switch (pageNumber){
            case  1 :
                data = page1;
                break;
            case 2 :
                data = page2;
                break;
            case 3:
                data = getPage3;
                break;
            default:
                data = pageInvalid;
                break;
        }

        return data;
    }

    @Override
    public Single<String> getProductListByPageNumber(final int pageNumber) {
        return Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return waitForProductListByPageNo(pageNumber);
            }
        }).subscribeOn(Schedulers.io());
    }


    // pagenumber can be 1,2,3 for total 60 products
    // per page would be 20 products
    @Override
    public void getProductListByPageNumber(int pageNumber, final Callback<String> callback) {
        getProductListByPageNumber(pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(final Disposable d) {}

                    @Override
                    public void onSuccess(final String response) {
                        callback.onResult(response);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        callback.onError(e);
                    }
                });
    }

    private static void randomDelay() {
        try {
            Thread.sleep(RANDOM.nextInt(5000));
        } catch (InterruptedException e) {
            Log.e(ProductNetworkClientImpl.class.getSimpleName(), "Interrupted sleep");
            Thread.currentThread().interrupt();
        }
    }

    private static void maybeThrowError() throws IOException {
        if (true) {
            return;
        }
        if (RANDOM.nextInt(5) == 0) {
            throw new IOException("fake network error");
        }
    }


    String pageInvalid = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"message\": \"Success\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"response\": {\n" +
            "    \"product_count\": 0,\n" +
            "    \"total_found\": 60,\n" +
            "    \"offset\": 60,\n" +
            "    \"products\": []\n" +
            "  }\n" +
            "}";

    String page1 = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"message\": \"Success\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"response\": {\n" +
            "    \"product_count\": 20,\n" +
            "    \"stop_further_call\": 0,\n" +
            "    \"total_found\": 60,\n" +
            "    \"offset\": 0,\n" +
            "    \"products\": [\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": true,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282534\",\n" +
            "        \"sku\": \"9781578261406\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": true,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"id\": null,\n" +
            "            \"name\": null\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"id\": null,\n" +
            "            \"name\": null\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": null,\n" +
            "            \"name\": null\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 899,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 899,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-body-sculpting-bible-swimsuit-workout-women-s-edition-by-james-villepigue-paperback/p/282534\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Body Sculpting Bible Swimsuit Workout: Women's Edition by James Villepigue - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781578261406.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": true,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278706\",\n" +
            "        \"sku\": \"9780141193182\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": true,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"id\": \"6619\",\n" +
            "            \"name\": \"Books\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"scent-of-a-woman-by-giovanni-arpino-paperback/p/278706\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Scent Of A Woman by Giovanni Arpino - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780141193182.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278644\",\n" +
            "        \"sku\": \"9788184001952\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "        \"slug\": \"dr-mathai-s-holistic-health-guide-for-women-by-dr-issac-mathai-paperback/p/278644\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Dr Mathai's Holistic Health Guide for Women by Dr Issac Mathai - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184001952.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282515\",\n" +
            "        \"sku\": \"9789381431375\",\n" +
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
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"dying-to-be-me-my-journey-from-cancer-by-anita-moorjani-paperback/p/282515\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Dying To Be Me : My Journey from Cancer by Anita Moorjani - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9789381431375.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282517\",\n" +
            "        \"sku\": \"9781601423788\",\n" +
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
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"for-women-only-in-the-workplace-by-shaunti-feldhahn/p/282517\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"For Women Only In The Workplace by Shaunti Feldhahn,\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781601423788.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 1,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278650\",\n" +
            "        \"sku\": \"9788184004618\",\n" +
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
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"hair-yoga-caring-for-your-hair-the-right-way-by-jawed-habib-paperback/p/278650\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Hair Yoga : Caring for Your Hair The Right Way by Jawed Habib - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184004618.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278672\",\n" +
            "        \"sku\": \"9780143417699\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"someone-like-you-by-nikita-singh-and-durjoy-datta-paperback/p/278672\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Someone Like You by Nikita Singh and Durjoy Datta - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143417699.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278647\",\n" +
            "        \"sku\": \"9788184004199\",\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"few-things-left-unsaid-by-sudeep-nagarkar-paperback/p/278647\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Few Things Left Unsaid by Sudeep Nagarkar - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184004199.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278676\",\n" +
            "        \"sku\": \"9780143418658\",\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-day-i-stopped-drinking-milk-by-sudha-murty-paperback/p/278676\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Day I Stopped Drinking Milk by Sudha Murty - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143418658.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278656\",\n" +
            "        \"sku\": \"9788184005967\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"marry-me-stranger-by-novoneel-chakraborty-paperback/p/278656\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Marry Me, Stranger by Novoneel Chakraborty - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184005967.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278701\",\n" +
            "        \"sku\": \"9780241972939\",\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-forty-rules-of-love-by-elif-shafak-paperback/p/278701\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Forty Rules of Love by Elif Shafak - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780241972939.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282533\",\n" +
            "        \"sku\": \"9781578262137\",\n" +
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
            "        \"final_price\": 899,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 899,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-body-sculpting-bible-for-buns-legs-women-s-edition-by-james-villepigue-paperback/p/282533\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Body Sculpting Bible For Buns & Legs: Women's Edition by James Villepigue - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781578262137.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 3,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278667\",\n" +
            "        \"sku\": \"9780143420903\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 199,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 199,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"hold-my-hand-by-durjoy-datta-paperback/p/278667\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Hold My Hand by Durjoy Datta - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143420903.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278665\",\n" +
            "        \"sku\": \"9788184004571\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "        \"slug\": \"everyday-ayurveda-by-dr-bhaswati-bhattacharya-paperback/p/278665\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Everyday Ayurveda by Dr Bhaswati Bhattacharya - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184004571.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278695\",\n" +
            "        \"sku\": \"9780143420361\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"house-of-cards-by-sudha-murty-paperback/p/278695\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"House of Cards by Sudha Murty - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143420361.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278674\",\n" +
            "        \"sku\": \"9780143423003\",\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"pmr-your-dreams-are-mine-now-by-ravinder-singh-paperback/p/278674\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"PMR: Your Dreams Are Mine Now by Ravinder Singh - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143423003.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
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
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282521\",\n" +
            "        \"sku\": \"9780307461704\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"how-remarkable-women-lead-by-joanna-barsh-paperback/p/282521\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"How Remarkable Women Lead by Joanna Barsh - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780307461704.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278673\",\n" +
            "        \"sku\": \"9780143423027\",\n" +
            "        \"rating_count\": 0,\n" +
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
            "            \"id\": \"6620\",\n" +
            "            \"name\": \"Fiction\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"id\": \"4362\",\n" +
            "            \"name\": \"Jewellery & Accessories\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 199,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 199,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"pmr-this-love-that-feels-right-by-ravinder-singh-paperback/p/278673\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"PMR: This Love that Feels Right. . . by Ravinder Singh - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143423027.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";


    String page2 = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"message\": \"Success\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"response\": {\n" +
            "    \"product_count\": 20,\n" +
            "    \"stop_further_call\": 0,\n" +
            "    \"total_found\": 60,\n" +
            "    \"offset\": 20,\n" +
            "    \"products\": [\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282536\",\n" +
            "        \"sku\": \"9780552778527\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"wedding-night-by-sophie-kinsella-paperback/p/282536\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Wedding Night by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552778527.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282518\",\n" +
            "        \"sku\": \"9781784161750\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"girl-on-the-train-by-paula-hawkins-paperback/p/282518\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Girl On The Train by Paula Hawkins - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781784161750.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278697\",\n" +
            "        \"sku\": \"9780143426790\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"me-ma-by-divya-dutta-paperback/p/278697\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Me & Ma by Divya Dutta - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143426790.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278653\",\n" +
            "        \"sku\": \"9788184003192\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"kitchen-clinic-good-health-always-with-charmaine-by-charmaine-d-souza-paperback/p/278653\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Kitchen Clinic : Good Health Always With Charmaine by Charmaine D'souza - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184003192.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278698\",\n" +
            "        \"sku\": \"9780670089635\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-ministry-of-utmost-happiness-by-arundhati-roy-hardback/p/278698\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Ministry of Utmost Happiness by Arundhati Roy - Hardback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780670089635.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278696\",\n" +
            "        \"sku\": \"9780143333630\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-magic-drum-and-other-favourite-stories-by-sudha-murty-paperback/p/278696\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Magic Drum And Other Favourite Stories by Sudha Murty - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143333630.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278654\",\n" +
            "        \"sku\": \"9788184006018\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-lazy-girl-s-guide-to-being-fit-by-namrata-purohit-paperback/p/278654\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Lazy Girl's Guide To Being Fit by Namrata Purohit - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184006018.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282531\",\n" +
            "        \"sku\": \"9781784161170\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"shopaholic-to-the-rescue-by-sophie-kinsella-paperback/p/282531\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Shopaholic To the Rescue by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781784161170.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282525\",\n" +
            "        \"sku\": \"9780593074794\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"my-not-so-perfect-life-by-sophie-kinsella-paperback/p/282525\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"My Not So Perfect Life by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780593074794.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282532\",\n" +
            "        \"sku\": \"9781578262656\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 999,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 999,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-body-sculpting-bible-for-abs-women-s-edition-deluxe-edition-by-james-villepigue-hardback/p/282532\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Body Sculpting Bible For ABS: Women's Edition, Deluxe Edition by James Villepigue - Hardback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781578262656.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282522\",\n" +
            "        \"sku\": \"9780552149525\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-lost-symbol-by-sophie-kinsella-paperback/p/282522\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Lost Symbol by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552149525.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278659\",\n" +
            "        \"sku\": \"9788184003130\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"shut-up-and-train-a-complete-fitness-guide-for-men-and-women-by-deanne-panday-paperback/p/278659\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Shut Up And Train : A Complete Fitness Guide For Men and Women by Deanne Panday - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184003130.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278693\",\n" +
            "        \"sku\": \"9780143028109\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 350,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 350,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"french-lover-by-taslima-nasrin-paperback/p/278693\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"French Lover by Taslima Nasrin - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143028109.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 2,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278702\",\n" +
            "        \"sku\": \"9780241970942\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-architect-s-apprentice-by-elif-shafak-paperback/p/278702\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Architect's Apprentice by Elif Shafak - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780241970942.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282537\",\n" +
            "        \"sku\": \"9781782394860\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"wild-mti-by-cheryl-strayed-paperback/p/282537\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Wild (MTI) by Cheryl Strayed - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781782394860.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278658\",\n" +
            "        \"sku\": \"9788184007459\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"she-swiped-right-into-my-heart-by-sudeep-nagarkar-paperback/p/278658\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"She Swiped Right Into My Heart by Sudeep Nagarkar - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184007459.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278642\",\n" +
            "        \"sku\": \"9788184000733\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"body-goddess-the-complete-guide-on-yoga-for-women-by-payal-gidwani-tiwari-paperback/p/278642\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Body Goddess : The Complete Guide on Yoga for Women by Payal Gidwani Tiwari - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184000733.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278694\",\n" +
            "        \"sku\": \"9780143102663\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-golden-honeycomb-by-kamala-markandaya-paperback/p/278694\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Golden Honeycomb by Kamala Markandaya - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143102663.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278645\",\n" +
            "        \"sku\": \"9788184004403\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"drama-teen-a-cool-headed-guide-for-parents-and-teenagers-by-lina-ashar-paperback/p/278645\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Drama Teen: A Cool-Headed Guide For Parents And Teenagers by Lina Ashar - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184004403.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278641\",\n" +
            "        \"sku\": \"9788184005936\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"blood-sugar-spice-living-with-diabetes-by-charmaine-d-souza-paperback/p/278641\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Blood Sugar & Spice : Living with Diabetes by Charmaine D'souza - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184005936.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    String getPage3 = "{\n" +
            "  \"status\": \"success\",\n" +
            "  \"message\": \"Success\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"response\": {\n" +
            "    \"product_count\": 20,\n" +
            "    \"total_found\": 60,\n" +
            "    \"offset\": 40,\n" +
            "    \"products\": [\n" +
            "      {\n" +
            "        \"rating\": 4.5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282530\",\n" +
            "        \"sku\": \"9780552773485\",\n" +
            "        \"rating_count\": 2,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"shopaholic-ties-the-knot-by-sophie-kinsella-paperback/p/282530\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Shopaholic Ties The Knot by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552773485.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278670\",\n" +
            "        \"sku\": \"9780143419648\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"love-stories-that-touched-my-heart-by-ravinder-singh-paperback/p/278670\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Love Stories That Touched My Heart by Ravinder Singh - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143419648.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816,11214\",\n" +
            "        \"id\": \"278684\",\n" +
            "        \"sku\": \"9780143419396\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-pregnancy-handbook-for-indian-moms-by-dr-vinita-salvi-paperback/p/278684\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Pregnancy Handbook for Indian Moms by Dr Vinita Salvi - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143419396.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282529\",\n" +
            "        \"sku\": \"9780552773478\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"shopaholic-abroad-by-sophie-kinsella-paperback/p/282529\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Shopaholic Abroad by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552773478.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278657\",\n" +
            "        \"sku\": \"9788184005974\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 250,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 250,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"sculpt-and-shape-the-pilates-way-by-yasmin-karachiwala-and-zeena-dhalla-paperback/p/278657\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Sculpt and Shape : The Pilates Way by Yasmin Karachiwala And Zeena Dhalla - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184005974.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 2.5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282528\",\n" +
            "        \"sku\": \"9780552152471\",\n" +
            "        \"rating_count\": 2,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"shopaholic-sister-by-sophie-kinsella-hardback/p/282528\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Shopaholic & Sister by Sophie Kinsella - Hardback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552152471.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278663\",\n" +
            "        \"sku\": \"9788184006711\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 175,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 175,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"you-re-trending-in-my-dreams-by-sudeep-nagarkar-paperback/p/278663\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"You're Trending in My Dreams by Sudeep Nagarkar - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184006711.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278703\",\n" +
            "        \"sku\": \"9788184003536\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"fit-at-40-by-dr-rishma-dhillon-pai-paperback/p/278703\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Fit At 40 by Dr Rishma Dhillon Pai - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184003536.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282524\",\n" +
            "        \"sku\": \"9780552774390\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"mini-shopaholic-by-sophie-kinsella-paperback/p/282524\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Mini Shopaholic by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552774390.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282538\",\n" +
            "        \"sku\": \"9781607749769\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 499,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 499,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"women-in-science-by-rachel-ignotofsky-hardback/p/282538\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Women In Science by Rachel Ignotofsky - Hardback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781607749769.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282535\",\n" +
            "        \"sku\": \"9781440587832\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-little-book-for-moms-stories-recip-by-purohit-namrata-hardback/p/282535\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Little Book For Moms: Stories, Recip by Purohit Namrata - Hardback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781440587832.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282527\",\n" +
            "        \"sku\": \"9780552773461\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"the-secret-dreamworld-of-a-shopaholic-by-sophie-kinsella-paperback/p/282527\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"The Secret Dreamworld Of A Shopaholic by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552773461.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282519\",\n" +
            "        \"sku\": \"9781592407576\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 799,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 799,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"girl-walks-into-a-bar-by-rachel-dratch-paperback/p/282519\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Girl Walks Into A Bar... by Rachel Dratch - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9781592407576.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278689\",\n" +
            "        \"sku\": \"9780143427995\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"bijnis-woman-stories-of-uttar-pradesh-by-tanuja-chandra-paperback/p/278689\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Bijnis Woman : Stories Of Uttar Pradesh by Tanuja Chandra - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143427995.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278683\",\n" +
            "        \"sku\": \"9780143102489\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": null,\n" +
            "            \"id\": null\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 450,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 450,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"pleasure-city-by-kamala-markandaya-paperback/p/278683\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Pleasure City by Kamala Markandaya - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143102489.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 5,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"282520\",\n" +
            "        \"sku\": \"9780753557600\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 599,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 599,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"girls-who-code-by-reshma-saujani-paperback/p/282520\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Girls Who Code by Reshma Saujani - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780753557600.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278643\",\n" +
            "        \"sku\": \"9788184003253\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"bringing-up-your-baby-the-comprehensive-guide-for-your-baby-s-first-year-by-komal-porecha-paperback/p/278643\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Bringing Up Your Baby : The Comprehensive Guide For Your Baby's First Year by Komal Porecha - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184003253.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6621,6631,9816\",\n" +
            "        \"id\": \"278662\",\n" +
            "        \"sku\": \"9788184000993\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Non Fiction\",\n" +
            "            \"id\": \"6621\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"work-it-out-without-a-workout-by-vesna-jacob-paperback/p/278662\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Work It Out Without a Workout by Vesna Jacob - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9788184000993.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"278686\",\n" +
            "        \"sku\": \"9780143415282\",\n" +
            "        \"rating_count\": 1,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
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
            "        \"slug\": \"sita-s-ascent-by-vayu-naidu-paperback/p/278686\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Sita's Ascent by Vayu Naidu - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780143415282.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"rating\": 4,\n" +
            "        \"is_kit_combo\": null,\n" +
            "        \"object_type\": \"product\",\n" +
            "        \"is_saleable\": false,\n" +
            "        \"explore_more\": null,\n" +
            "        \"pack_size\": null,\n" +
            "        \"category_ids\": \"4362,4408,6619,6620,6631,9816\",\n" +
            "        \"id\": \"282526\",\n" +
            "        \"sku\": \"9780552775274\",\n" +
            "        \"rating_count\": 0,\n" +
            "        \"from_gludo\": 1,\n" +
            "        \"fbn\": 0,\n" +
            "        \"gludo_stock\": false,\n" +
            "        \"offers\": null,\n" +
            "        \"mrp_freeze\": null,\n" +
            "        \"primary_categories\": {\n" +
            "          \"l2\": {\n" +
            "            \"name\": \"Books\",\n" +
            "            \"id\": \"6619\"\n" +
            "          },\n" +
            "          \"l3\": {\n" +
            "            \"name\": \"Fiction\",\n" +
            "            \"id\": \"6620\"\n" +
            "          },\n" +
            "          \"l1\": {\n" +
            "            \"name\": \"Jewellery & Accessories\",\n" +
            "            \"id\": \"4362\"\n" +
            "          }\n" +
            "        },\n" +
            "        \"type\": \"simple\",\n" +
            "        \"final_price\": 399,\n" +
            "        \"expdt\": null,\n" +
            "        \"price\": 399,\n" +
            "        \"button_text\": \"ADD TO BAG\",\n" +
            "        \"brand_name\": \"\",\n" +
            "        \"discount\": 0,\n" +
            "        \"show_wishlist_button\": 0,\n" +
            "        \"slug\": \"remember-me-by-sophie-kinsella-paperback/p/282526\",\n" +
            "        \"is_lux\": 0,\n" +
            "        \"name\": \"Remember Me? by Sophie Kinsella - Paperback\",\n" +
            "        \"pro_flag\": 0,\n" +
            "        \"dynamic_tags\": [],\n" +
            "        \"brand_ids\": \"6631\",\n" +
            "        \"is_backorder\": 0,\n" +
            "        \"image_url\": \"https://images-static.nykaa.com/media/catalog/product/tr:h-200,w-200,cm-pad_resize/9/7/9780552775274.jpg\",\n" +
            "        \"catalog_tag\": [\n" +
            "          \"nykaa\"\n" +
            "        ],\n" +
            "        \"quantity\": 0\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    // </editor-fold>
}


