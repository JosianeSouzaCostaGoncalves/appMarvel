package com.example.appmarvel.view

import android.app.Application
import com.example.appmarvel.data.di.dataModules
import com.example.appmarvel.domain.di.domainModules
import com.example.appmarvel.view.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppMarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppMarvelApplication)
            modules(dataModules + viewModules  + domainModules)
        }
    }
}interface GenericMapper<A, D> {

    fun mapToDomain(type: A): D

    fun mapFromDomain(type: D): A

    fun mapToDomain(entity: List<A?>): List<D?> {
        return entity.map { if (it == null) null else mapToDomain(it) }
    }

    fun mapFromDomainNonNull(entity: List<A>): List<D> {
        return entity.map { mapToDomain(it) }
    }

    fun mapFromDomain(domain: List<D?>): List<A?> {
        return domain.map { if (it == null) null else mapFromDomain(it) }
    }

    fun mapToDomainNonNull(domain: List<D>): List<A> {
        return domain.map { mapFromDomain(it)!! }
    }
}