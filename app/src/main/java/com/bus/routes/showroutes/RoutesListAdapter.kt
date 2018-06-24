package com.bus.routes.showroutes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bus.routes.R
import com.bus.routes.domain.Route
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.route_item_list.view.*

class RoutesListAdapter : RecyclerView.Adapter<RoutesListAdapter.RouteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.route_item_list, parent, false)
        return RouteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        holder.bindViews(mListRoutes!![position])
    }

    private var mListRoutes: ArrayList<Route>? = null

    init {
        this.mListRoutes = ArrayList()
    }

    fun updateList(list: ArrayList<Route>?) {
        this.mListRoutes = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mListRoutes!!.size
    }


    class RouteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labName = view.lab_route_name
        val labDescription = view.lab_route_description
        val imgRoute = view.img_route

        fun bindViews(route: Route) {
            labName.text = route.name
            labDescription.text = route.description
            Picasso.get().load(route.img_url).into(imgRoute)
        }
    }
}