package io.interview.productlisttest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import io.interview.productlisttest.api.ProductNetworkClientImpl
import io.interview.productlisttest.paging.ProductDataSource

class ProductViewModel : ViewModel() {
    val products = Pager(
            config = PagingConfig(pageSize = 20),
            initialKey = 1
    ) {
        ProductDataSource(ProductNetworkClientImpl.getInstance())
    }.liveData
}