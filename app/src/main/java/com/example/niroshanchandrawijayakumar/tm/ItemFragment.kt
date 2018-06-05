package com.example.niroshanchandrawijayakumar.tm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_item_list.*


class ItemFragment : Fragment() {


    private var columnCount = 1
    lateinit var viewModel: MainViewModel
     var myAdapter: MyItemRecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)

        myAdapter = MyItemRecyclerViewAdapter(context!!)
        // Set the adapter
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = myAdapter
            }


        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTop5Button.setOnClickListener {
            viewModel.top5Data.observe(this, Observer { list -> myAdapter?.update(list) })
        }
        showAllButton.setOnClickListener {
            viewModel.dataList.observe(this, Observer { list -> myAdapter?.update(list) })
        }
        viewModel.dataList.observe(this, Observer { list -> myAdapter?.update(list) })

    }


    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
