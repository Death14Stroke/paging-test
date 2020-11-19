package io.interview.productlisttest.paging

import androidx.paging.PagingSource
import io.interview.productlisttest.api.ProductNetworkClient
import io.interview.productlisttest.model.Product
import io.interview.productlisttest.model.State
import io.interview.productlisttest.model.StateEvent
import io.interview.productlisttest.util.JsonProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus

class ProductDataSource(private val api: ProductNetworkClient) : PagingSource<Int, Product>() {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: return LoadResult.Page(emptyList(), null, null)

            val response = withContext(Dispatchers.IO) { api.waitForProductListByPageNo(page) }
            val products = withContext(Dispatchers.Default) { JsonProcessor.getProductsFromJson(response) }

            updateStatus(response)

            LoadResult.Page(
                    data = products,
                    prevKey = null,
                    nextKey = if (page < 3) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun updateStatus(response: String) {
        val loadState = withContext(Dispatchers.Default) { JsonProcessor.getLoadingStatsFromJson(response) }
        if (loadState is State.Loading)
            EventBus.getDefault().post(StateEvent(loadState))
    }
}