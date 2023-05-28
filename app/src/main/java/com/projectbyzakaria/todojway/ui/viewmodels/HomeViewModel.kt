package com.projectbyzakaria.todojway.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.projectbyzakaria.data.home_repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {




    fun auth() = repository.auth()


}