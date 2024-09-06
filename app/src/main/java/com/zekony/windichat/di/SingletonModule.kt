package com.zekony.windichat.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zekony.windichat.data.localStorage.TokenManager
import com.zekony.windichat.data.localStorage.UserDatastore
import com.zekony.windichat.data.network.apiServices.AuthApiService
import com.zekony.windichat.data.network.apiServices.UserApiService
import com.zekony.windichat.data.network.apiRepositories.AuthRepositoryImpl
import com.zekony.windichat.data.network.apiRepositories.UserApiRepositoryImpl
import com.zekony.windichat.data.network.interceptor.AuthAuthenticator
import com.zekony.windichat.data.network.interceptor.AuthInterceptor
import com.zekony.windichat.domain.repositories.AuthRepository
import com.zekony.windichat.domain.repositories.UserApiRepository
import com.zekony.windichat.utility.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
    }

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Singleton
    @Provides
    fun provideUserDatastore(@ApplicationContext context: Context): UserDatastore =
        UserDatastore(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenManager: TokenManager, apiService: AuthApiService): AuthAuthenticator =
        AuthAuthenticator(tokenManager, apiService)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())


    @Singleton
    @Provides
    fun provideUserApiService(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): UserApiService =
        retrofit
            .client(okHttpClient)
            .build()
            .create(UserApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthApiService  {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return retrofit
            .client(okHttp)
            .build()
            .create(AuthApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authApiService: AuthApiService
    ): AuthRepository = AuthRepositoryImpl(authApiService)

    @Singleton
    @Provides
    fun provideUserApiRepository(
        userApiService: UserApiService
    ): UserApiRepository = UserApiRepositoryImpl(userApiService)
}


