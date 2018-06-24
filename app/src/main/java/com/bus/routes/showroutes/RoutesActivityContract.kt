package com.bus.routes.showroutes

import com.bus.routes.base.BasePresenter
import com.bus.routes.base.BaseView
import com.bus.routes.domain.Route

import java.util.ArrayList

interface RoutesActivityContract {

    interface View : BaseView<Presenter> {

        fun showSchoolsList(schoolArrayList: ArrayList<Route>)
    }

    interface Presenter : BasePresenter<View>{


    }
}
