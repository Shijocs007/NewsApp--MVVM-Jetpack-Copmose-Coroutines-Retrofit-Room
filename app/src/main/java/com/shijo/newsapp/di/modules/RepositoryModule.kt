package com.shijo.newsapp.di.modules

import com.shijo.newsapp.data.repository.CountryListRepositoryImpl
import com.shijo.newsapp.data.repository.NewsRepositoryImpl
import com.shijo.newsapp.data.repository.OnboardingRepositoryImpl
import com.shijo.newsapp.domain.repository.CountryListRepository
import com.shijo.newsapp.domain.repository.NewsRepository
import com.shijo.newsapp.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindOnboardingRepository(onboardingRepositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindCountryListRepository(countryListRepositoryImpl: CountryListRepositoryImpl): CountryListRepository

}