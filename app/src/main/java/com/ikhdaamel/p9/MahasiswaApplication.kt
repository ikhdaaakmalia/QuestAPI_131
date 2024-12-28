package com.ikhdaamel.p9

import android.app.Application
import com.ikhdaamel.p9.repository.AppContainer
import com.ikhdaamel.p9.repository.MahasiswaContainer

class MahasiswaApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}