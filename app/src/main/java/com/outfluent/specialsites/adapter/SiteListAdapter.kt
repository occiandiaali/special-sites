package com.outfluent.specialsites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outfluent.specialsites.R
import com.outfluent.specialsites.databinding.ListItemBinding
import com.outfluent.specialsites.model.Site

class SiteListAdapter(
    private val context: Context
) : RecyclerView.Adapter<SiteListAdapter.SiteViewHolder>() {

    lateinit var siteList: ArrayList<Site>


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SiteViewHolder {
//        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
//        return SiteViewHolder(itemView)
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SiteViewHolder(binding)
    }

    override fun getItemCount(): Int = siteList.size

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val site = siteList[position]
        holder.setData(site.siteurl, site.description)
    }

    fun setSites(sites: ArrayList<Site>) {
        siteList = sites
        notifyDataSetChanged()
    }

//    class SiteViewHolder(listItemBinding: ListItemBinding) : RecyclerView.ViewHolder(listItemBinding) {
//        fun setData(siteUrl: String, created: Long) {
//
//        }
//    }
inner class SiteViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(siteUrl: String, desc: String) {
        binding.tvSiteUrl.text = siteUrl
        binding.tvSiteInfo.text = desc
    }

} // inner class


} // class