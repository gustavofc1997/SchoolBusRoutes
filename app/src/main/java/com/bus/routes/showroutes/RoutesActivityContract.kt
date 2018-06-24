package com.bus.routes.showroutes

import com.bus.routes.base.BasePresenter
import com.bus.routes.base.BaseView
import com.bus.routes.domain.Route
import com.bus.routes.domain.Stop

import java.util.ArrayList

interface RoutesActivityContract {

    interface View : BaseView<Presenter> {

        fun showSchoolsList(schoolArrayList: ArrayList<Route>)
        fun showStopsByRoute(stopsArrayList: ArrayList<Stop>)
    }

    interface Presenter : BasePresenter<View> {
        fun getListRoutes()
        fun getStops(url: String)
    }
}
