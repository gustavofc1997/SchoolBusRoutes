package com.bus.routes.showroutes

import com.bus.routes.module.NetworkModule
import com.bus.routes.net.responses.RoutesResponse
import com.bus.routes.net.responses.StopsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoutesActivityPresenter @Inject internal constructor(var mNetworkModule: NetworkModule) : RoutesActivityContract.Presenter {
    override fun getListRoutes() {
        mCompositeDisposable?.add(
                mNetworkModule.getapi().getRoutes()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::onRoutesResponse, this::onRoutesError))
    }

    override fun getStops(url: String) {
        mCompositeDisposable?.add(
                mNetworkModule.getapi().getStops(url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::onStopResponse, this::onStopError))
    }


    private var mCompositeDisposable: CompositeDisposable? = null

    lateinit var mView: RoutesActivityContract.View

    override fun takeView(view: RoutesActivityContract.View) {
        this.mView = view
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        getListRoutes()
    }

    override fun dropView() {
        //  mView = null
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    fun onRoutesResponse(routesList: RoutesResponse) {
        mView.showSchoolsList(routesList.school_buses)

    }

    fun onStopResponse(routesList: StopsResponse) {
        mView.showStopsByRoute(routesList.stops)

    }

    fun onRoutesError(error: Throwable) {
        mView.showErrorLoadRoutesList()
    }

    fun onStopError(error: Throwable) {
        mView.showErrorLoadRoutesList()
    }


}