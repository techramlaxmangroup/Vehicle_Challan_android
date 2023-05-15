package com.technosales.vehiclechallanmobile.api

import com.google.gson.GsonBuilder
import com.technosales.vehiclechallanmobile.utils.TextConfig.BASE_URL
import okhttp3.CipherSuite.Companion.TLS_DHE_DSS_WITH_AES_128_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_128_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA
import okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_RC4_128_SHA
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object ApiClient {
    private val interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
        .retryOnConnectionFailure(true)
        .build()


     val retrofitBuilder: Retrofit.Builder by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }


    val apiInterface: ApiInterface by lazy {
        retrofitBuilder
            .build()
            .create(ApiInterface::class.java)
    }
}