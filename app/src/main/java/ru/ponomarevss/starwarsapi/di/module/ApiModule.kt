package ru.ponomarevss.starwarsapi.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.ponomarevss.starwarsapi.mvp.model.api.IDataSource
import ru.ponomarevss.starwarsapi.mvp.model.network.INetworkStatus
import ru.ponomarevss.starwarsapi.ui.App
import ru.ponomarevss.starwarsapi.ui.network.AndroidNetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    @Singleton
    fun baseUrl(): String = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson) : IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Provides
    @Singleton
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)

}