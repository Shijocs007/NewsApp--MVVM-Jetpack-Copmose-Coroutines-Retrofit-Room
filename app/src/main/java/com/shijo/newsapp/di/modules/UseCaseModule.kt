package com.shijo.newsapp.di.modules

import com.shijo.newsapp.domain.repository.CountryListRepository
import com.shijo.newsapp.domain.repository.NewsRepository
import com.shijo.newsapp.domain.repository.OnboardingRepository
import com.shijo.newsapp.domain.usecases.country.GetSelectedCountry
import com.shijo.newsapp.domain.usecases.country.LoadCountryList
import com.shijo.newsapp.domain.usecases.country.UpdateCountrySelectionStatus
import com.shijo.newsapp.domain.usecases.news.DeleteArticle
import com.shijo.newsapp.domain.usecases.news.GetSavedArticle
import com.shijo.newsapp.domain.usecases.news.GetSavedArticleList
import com.shijo.newsapp.domain.usecases.news.GetTopHeadLines
import com.shijo.newsapp.domain.usecases.news.UpsertArticle
import com.shijo.newsapp.domain.usecases.onboarding.GetOnboardingStatus
import com.shijo.newsapp.domain.usecases.onboarding.SaveOnboardingStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideSaveOnboardingStatusUsecase(repository: OnboardingRepository) =
        SaveOnboardingStatus(repository)

    @Provides
    fun provideGetOnboardingStatusUsecase(repository: OnboardingRepository) =
        GetOnboardingStatus(repository)

    @Provides
    fun provideGetTopHeadLinesUsecase(repository: NewsRepository) =
        GetTopHeadLines(repository)

    @Provides
    fun provideGetSelectedCountryUsecase(repository: NewsRepository) =
        GetSelectedCountry(repository)

    @Provides
    fun provideLoadCountryListUseCase(repository: CountryListRepository) =
        LoadCountryList(repository)

    @Provides
    fun providedUpdateCountrySelectionUseCase(repository: CountryListRepository) =
        UpdateCountrySelectionStatus(repository)

    @Provides
    fun providedGetSavedArticleUseCase(repository: NewsRepository) =
        GetSavedArticle(repository)

    @Provides
    fun providedDeleteArticleUseCase(repository: NewsRepository) =
        DeleteArticle(repository)

    @Provides
    fun providedUpsertArticleUseCase(repository: NewsRepository) =
        UpsertArticle(repository)

    @Provides
    fun provideGetSavedArticleListUseCase(repository: NewsRepository) =
        GetSavedArticleList(repository)

}