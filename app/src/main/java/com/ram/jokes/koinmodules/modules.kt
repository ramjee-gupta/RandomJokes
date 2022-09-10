package com.ram.jokes.koinmodules

import com.ram.jokes.features.JokesAdapter
import com.ram.jokes.utils.retrofit.getGson
import com.ram.jokes.utils.retrofit.getRetrofit
import com.ram.jokes.utils.retrofit.provideRetrofitInstance
import com.ram.jokes.features.JokesRepo
import com.ram.jokes.features.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val retrofitModule = module {

    single { getGson() }
    single { getRetrofit(get()) }

    single { provideRetrofitInstance(get()) }

    viewModel {
        JokesViewModel(get(), get())
    }

    factory {
        JokesRepo(get())
    }

}

