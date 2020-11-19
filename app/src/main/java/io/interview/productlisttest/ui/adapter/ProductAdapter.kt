package io.interview.productlisttest.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.interview.productlisttest.model.Product
import io.interview.productlisttest.ui.viewholder.ProductViewHolder

const val VIEW_TYPE_PRODUCT = 1

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
}

class ProductAdapter : PagingDataAdapter<Product, ProductViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemViewType(position: Int) = VIEW_TYPE_PRODUCT
}