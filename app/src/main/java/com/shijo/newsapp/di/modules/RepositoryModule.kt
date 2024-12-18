package com.shijo.newsapp.di.modules

import com.shijo.newsapp.data.repository.OnboardingRepositoryImpl
import com.shijo.newsapp.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewsRepository(onboardingRepositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

}