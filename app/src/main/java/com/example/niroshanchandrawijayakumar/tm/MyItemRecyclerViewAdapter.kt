package com.example.niroshanchandrawijayakumar.tm

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity

class MyItemRecyclerViewAdapter(val context: Context)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private var iWords: MutableList<WebsiteEntity> = mutableListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(iWords.isNotEmpty()){ holder.bindData(iWords[position]) }
        else{ holder.websiteName.text = context.getString(R.string.BlankList) }
    }

    override fun getItemCount(): Int = iWords.size
    fun update(list: MutableList<WebsiteEntity>?) {
        if(list!=null){
            iWords = list
            notifyDataSetChanged()
        }
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val visitDate: TextView =  mView.findViewById(R.id.visitDate) as TextView
        val websiteName: TextView = mView.findViewById(R.id.websiteName) as TextView
        val totalVisit: TextView = mView.findViewById(R.id.totalVisit) as TextView
        lateinit var item :WebsiteEntity


        override fun toString(): String {
            return super.toString() + " '" + websiteName.text + "'"
        }

        fun bindData(item:WebsiteEntity){
            websiteName.text = item.websiteName
            visitDate.text = item.visitDate
            totalVisit.text = item.totalVisits.toString()
            this.item = item
            with(mView) {
                tag = item
            }
        }
    }
}
