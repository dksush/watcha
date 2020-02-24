package com.example.giphy.ui.search

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.DiffUtil
import com.example.giphy.data.model.SearchData
import com.example.giphy.databinding.ItemSearchBinding
import com.example.giphy.ui.base.BaseRecyclerAdapter
import com.example.giphy.ui.base.BaseViewHolder

class SearchAdapter(private val context: Context, itemListener: ItemListener<SearchData>) :
    BaseRecyclerAdapter<SearchData, SearchAdapter.SearchHolder>(itemListener, DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchHolder(
        ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    inner class SearchHolder(private val binding: ItemSearchBinding) :
        BaseViewHolder<SearchData>(binding.root) {

        private lateinit var item: SearchData
        private var width : Int = 0

        init {
            Log.v("dksush", "init")

            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            width = size.x / 2
        }
        //        init {
//            binding.setOnClick {
//                Intent(it.context, WebviewActivity::class.java).apply {
//                    putExtra(StringConst.INTENT_KEY_LINK, item.link)
//                }.run { it.context.startActivity(this) }
//            }
//        }
        override fun bind(item: SearchData) {
            this.item = item
            binding.contentLayout.layoutParams.height = getScaleSizeHeight(item.images.fixed_width_small.width, item.images.fixed_width_small.height,width)
            with(binding) {
                items = item
                Log.v("dksush", "bind")

                executePendingBindings()
            }
        }

    }
}

private class DiffCallback : DiffUtil.ItemCallback<SearchData>() {
    override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
        return oldItem == newItem

    }

    override fun areContentsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
        return oldItem == newItem
    }

}