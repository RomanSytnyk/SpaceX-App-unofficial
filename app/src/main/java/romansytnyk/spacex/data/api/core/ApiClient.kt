package romansytnyk.spacex.data.api.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import romansytnyk.spacex.BuildConfig
import romansytnyk.spacex.data.api.Api
import java.util.concurrent.TimeUnit

/**
 * Created by Roman on 18.02.2018
 */
private const val BASE_URL = "https://api.spacexdata.com"

class ApiClient {
    private val requests: Requests
    var spacexApi: Api

    init {
        val okHttpClient = OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(mHttpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()

        requests = retrofit.create(Requests::class.java)
        spacexApi = Api(requests)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////// INTERCEPTORS /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private val mHttpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.NONE
            }
            return logging
        }
}