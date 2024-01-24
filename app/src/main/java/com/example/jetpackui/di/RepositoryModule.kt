package com.example.jetpackui.di

import com.example.jetpackui.data.repository.FeaturesRepoImpl
import com.example.jetpackui.data.repository.MovieDetailsRepoImpl
import com.example.jetpackui.data.repository.MovieRepoImpl
import com.example.jetpackui.screens.domain.repository.FeaturesRepo
import com.example.jetpackui.screens.domain.repository.MovieDetailsRepo
import com.example.jetpackui.screens.domain.repository.MovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideFeatureRepoImpl(repo: FeaturesRepoImpl): FeaturesRepo

    @Binds
    abstract fun provideMovieRepoImpl(repo: MovieRepoImpl): MovieRepo

    @Binds
    abstract fun provideMovieDetailRepoImpl(repo: MovieDetailsRepoImpl): MovieDetailsRepo
}