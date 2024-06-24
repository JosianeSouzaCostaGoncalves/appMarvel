package com.example.appmarvel.data.remote.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.appmarvel.data.remote.service.API_KEY
import com.example.appmarvel.data.remote.service.BASE_URLs
import com.example.appmarvel.data.remote.service.HASH
import com.example.appmarvel.data.remote.service.PRIVATE_KEY
import com.example.appmarvel.data.remote.service.PUBLIC_KEY
import com.example.appmarvel.data.remote.service.ServiceApi
import com.example.appmarvel.data.remote.service.TS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MyRetrofit {
    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URLs)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getApiService(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    fun getOkHttpClient(context: Context): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val timeStamp = System.currentTimeMillis()
                val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter(TS, timeStamp.toString())
                    .addQueryParameter(API_KEY, PUBLIC_KEY)
                    .addQueryParameter(
                        HASH,
                        generatorMd5Hash(timeStamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
                    )
                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    fun generatorMd5Hash(encrypted: String): String {
        var pass = encrypted
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        Timber.d("hash -> $encryptedString")
        return encryptedString ?: ""
    }
}