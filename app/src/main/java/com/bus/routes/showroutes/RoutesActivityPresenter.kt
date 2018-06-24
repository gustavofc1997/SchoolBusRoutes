package com.bus.routes.showroutes

import com.bus.routes.module.NetworkModule
import com.bus.routes.net.responses.RoutesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RoutesActivityPresenter @Inject internal constructor(var mNetworkModule: NetworkModule) : RoutesActivityContract.Presenter {

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

    fun onRoutesError(error: Throwable) {


    }

    fun getListRoutes() {
        mCompositeDisposable?.add(
                mNetworkModule.getapi().getRoutes()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::onRoutesResponse, this::onRoutesError))
    }
}