package com.geekaid.payload.driver

import androidx.transition.Visibility
import com.geekaid.payload.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(): Repository{
        return Repository()
    }
}