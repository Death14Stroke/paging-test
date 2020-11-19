package io.interview.productlisttest.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import io.interview.productlisttest.databinding.ActivityMainBinding
import io.interview.productlisttest.model.State
import io.interview.productlisttest.model.StateEvent
import io.interview.productlisttest.ui.adapter.ProductAdapter
import io.interview.productlisttest.ui.adapter.ProductLoadStateAdapter
import io.interview.productlisttest.ui.adapter.VIEW_TYPE_PRODUCT
import io.interview.productlisttest.ui.viewmodel.ProductViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = "${MainActivity::class.java.simpleName}Log"
    }

    private val productAdapter = ProductAdapter()
    private val productLoadStateAdapter = ProductLoadStateAdapter()
    private val productViewModel by lazy {
        ViewModelProvider(this).get(ProductViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        productAdapter.addLoadStateListener { combinedLoadStates ->
            when (combinedLoadStates.refresh) {
                is LoadState.Loading -> {
                    binding.initialProgressBar.visibility = View.VISIBLE
                }
                else ->
                    binding.initialProgressBar.visibility = View.GONE
            }
        }

        productViewModel.products.observe(this) {
            productAdapter.submitData(lifecycle, it)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onStateEvent(event: StateEvent) {
        when (val state = event.state) {
            is State.Loading -> {
                productLoadStateAdapter.updateLoadingCount(state.loadedItems, state.totalItems)
                Log.d(TAG, "Loading ${state.loadedItems} of ${state.totalItems}")
            }
            is State.Success -> {
                Log.d(TAG, "no more items to load")
            }
        }
    }

    private fun initRecyclerView() {
        val concatAdapter = productAdapter.withLoadStateFooter(productLoadStateAdapter)
        val gridLayoutManager = GridLayoutManager(this, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (concatAdapter.getItemViewType(position)) {
                        VIEW_TYPE_PRODUCT -> 1
                        else -> 2
                    }
                }
            }
        }

        binding.recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = concatAdapter
        }
    }
}