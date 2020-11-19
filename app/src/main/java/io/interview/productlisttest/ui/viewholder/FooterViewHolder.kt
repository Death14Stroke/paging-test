package io.interview.productlisttest.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.interview.productlisttest.databinding.LayoutFooterBinding
import io.interview.productlisttest.model.Footer

class FooterViewHolder(private val binding: LayoutFooterBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): FooterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LayoutFooterBinding.inflate(inflater, parent, false)
            return FooterViewHolder(binding)
        }
    }

    fun bind(footer: Footer) {
        binding.footer = footer
    }
}