package c.june.learning.di

import c.june.learning.viewmodels.GithubViewModel
import c.june.learning.viewmodels.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TodoViewModel(get()) }
    viewModel { GithubViewModel(get()) }
}