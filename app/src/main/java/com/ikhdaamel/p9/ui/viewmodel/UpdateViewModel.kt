package com.ikhdaamel.p9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.p9.repository.MahasiswaRepository
import com.ikhdaamel.p9.ui.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateViewModel (
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
): ViewModel(){
    var updateUIState by mutableStateOf(InsertUiState())
        private set
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUIState = mahasiswaRepository.getMahasiswaById(_nim)
                .toUiStateMhs()
        }
    }
    fun updateInsertState(insertUiEvent: InsertUiEvent){
        updateUIState = InsertUiState(
            insertUiEvent = InsertUiEvent()
        )
    }
    suspend fun updateMahasiswa(){
        viewModelScope.launch {
            try {
                mahasiswaRepository.updateMahasiswa(_nim, updateUIState.insertUiEvent.toMhs() )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}