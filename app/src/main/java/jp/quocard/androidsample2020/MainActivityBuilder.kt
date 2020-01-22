package jp.quocard.androidsample2020

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}