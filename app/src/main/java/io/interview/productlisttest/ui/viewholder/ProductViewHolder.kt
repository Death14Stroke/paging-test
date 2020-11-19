package io.interview.productlisttest.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.interview.productlisttest.databinding.LayoutItemBinding
import io.interview.productlisttest.model.Product

class ProductViewHolder(private val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutItemBinding.inflate(inflater, parent, false)
            return ProductViewHolder(binding)
        }
    }

    fun bind(product: Product) {
        binding.product = product
    }
}