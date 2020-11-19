package io.interview.productlisttest.api;

import java.io.IOException;

import io.reactivex.Single;

/** Makes network requests to fetch data. */
public interface ProductNetworkClient {

    // ========== Network calls ==========

    /**
     * @return The list of Product to display.
     * @throws IOException If an error was encountered during the network call.
     */
    String waitForProductListByPageNo(int pageNumber) throws IOException;

    /** @return An async operation for the list of Product to display. */
    Single<String> getProductListByPageNumber(int pageNumber);

    /** @param callback The listener that will be called after the async operation for the list of Product finishes. */
    void getProductListByPageNumber(int pageNumber, Callback<String> callback);


    /** Callbacks on the main thread when execution has completed. */
    interface Callback<T> {
        void onResult(T result);
        void onError(Throwable error);
    }
}
