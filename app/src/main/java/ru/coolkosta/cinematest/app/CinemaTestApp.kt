package ru.coolkosta.cinematest.app

import android.app.Application
import ru.coolkosta.cinematest.di.AppComponent
import ru.coolkosta.cinematest.di.DaggerAppComponent

class CinemaTestApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}