package com.ikhdaamel.p9.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ikhdaamel.p9.MahasiswaApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(MahasiswaApp().container.mahasiswaRepository) }
        initializer { InsertViewModel(MahasiswaApp().container.mahasiswaRepository) }
        initializer { UpdateViewModel(createSavedStateHandle(), MahasiswaApp().container.mahasiswaRepository) }
        initializer { DetailViewModel(createSavedStateHandle(), MahasiswaApp().container.mahasiswaRepository) }
    }

    fun CreationExtras.MahasiswaApp(): MahasiswaApplication =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplication)
}