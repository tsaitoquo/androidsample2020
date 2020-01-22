package jp.quocard.androidsample2020

import android.app.Application
import android.content.Context
import dagger.*
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Module
abstract class MyApplicationModule {
    @Binds
    abstract fun provideContext(application: MyApplication): Context
}

@Singleton
@Component(modules = [
    AndroidInjectionModule::class, // よりシンプルにDIできる公式の便利Module(必須)
    MyApplicationModule::class,
    MainActivityBuilder::class
])
interface MyApplicationComponent: AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: MyApplication): MyApplicationComponent
    }
}

