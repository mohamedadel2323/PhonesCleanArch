package com.example

import android.app.Application

class MyApplication : Application() {
    val dependenciesProvider = DependenciesProvider()
}