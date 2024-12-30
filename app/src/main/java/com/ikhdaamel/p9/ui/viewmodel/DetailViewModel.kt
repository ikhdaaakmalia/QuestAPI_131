package com.ikhdaamel.p9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ikhdaamel.p9.model.Mahasiswa
import com.ikhdaamel.p9.repository.MahasiswaRepository
import com.ikhdaamel.p9.ui.view.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState{
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
): ViewModel(){
    var mahasiswaDetailState : DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set
    private val _nim : String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    init {
        getMahasiswaById()
    }

    fun getMahasiswaById(){
        viewModelScope.launch {
            mahasiswaDetailState = DetailUiState.Loading
            mahasiswaDetailState = try{
                val mahasiswa = mhs.getMahasiswaById(_nim)
                DetailUiState.Success(mahasiswa)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}