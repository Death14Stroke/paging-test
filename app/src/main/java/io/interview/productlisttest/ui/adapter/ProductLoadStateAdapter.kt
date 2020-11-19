package io.interview.productlisttest.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import io.interview.productlisttest.R
import io.interview.productlisttest.model.Footer
import io.interview.productlisttest.ui.viewholder.FooterViewHolder

class ProductLoadStateAdapter : LoadStateAdapter<FooterViewHolder>() {
    private var totalItems = 0
    private var loadedItems = 0

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.NotLoading
    }

    fun updateLoadingCount(loaded: Int, total: Int) {
        loadedItems = loaded
        totalItems = total

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
            FooterViewHolder.from(parent)

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        if (loadState.endOfPaginationReached) {
            val message = holder.itemView.context.getString(R.string.no_more_products)
            holder.bind(Footer(false, message))
        } else if (loadState is LoadState.Loading) {
            val message = holder.itemView.context.getString(R.string.loading_items, loadedItems, totalItems)
            holder.bind(Footer(true, message))
        }
    }
}