package com.palash.retrofitwithhilt_mvvm.di

import com.palash.retrofitwithhilt_mvvm.api_service.AuthorisedAPI
import com.palash.retrofitwithhilt_mvvm.api_service.UnauthorisedAPI
import com.palash.retrofitwithhilt_mvvm.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor //for -> authorised token
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS)     // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)    //Write timeout
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor) //for -> authorised token
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideUnauthorisedAPI(retrofitBuilder: Retrofit.Builder): UnauthorisedAPI {
        return retrofitBuilder.build().create(UnauthorisedAPI::class.java)
    }

    ////for authorised -> token
    @Singleton
    @Provides
    fun providesAuthorisedAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): AuthorisedAPI {
        return retrofitBuilder.client(okHttpClient).build().create(AuthorisedAPI::class.java)
    }
}