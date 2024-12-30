package com.ikhdaamel.p9.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.p9.model.Mahasiswa
import com.ikhdaamel.p9.ui.customwidget.CustomeTopAppBar
import com.ikhdaamel.p9.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.p9.ui.viewmodel.DetailUiState
import com.ikhdaamel.p9.ui.viewmodel.DetailViewModel
import com.ikhdaamel.p9.ui.viewmodel.PenyediaViewModel

object DestinasiDetail: DestinasiNavigasi{
    override val route = "detail"
    override val titleRes = "Detail Mahasiswa"
    const val NIM = "nim"
    val routesWithArg = "$route/[$NIM]"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    navigateToUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        topBar = {
            CustomeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getMahasiswaById()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToUpdate,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Mahasiswa"
                )
            }
        }
    ){ innerpadding ->
        DetailStatus(
            modifier = Modifier.padding(innerpadding),
            detailUiState = viewModel.mahasiswaDetailState,
            retryAction = {viewModel.getMahasiswaById()}
        )
    }
}

@Composable
fun DetailStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState
){
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailUiState.Success -> {
            if (detailUiState.mahasiswa.nim.isEmpty()){
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Tidak Ada Data")
                }
            } else {
                ItemDetail(
                    mahasiswa = detailUiState.mahasiswa,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetail(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa
){
    Card(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetail(title = "NIM", isi = mahasiswa.nim)
            ComponentDetail(title = "Nama", isi = mahasiswa.nama)
            ComponentDetail(title = "Alamat", isi = mahasiswa.alamat)
            ComponentDetail(title = "Jenis Kelamin", isi = mahasiswa.jenisKelamin)
            ComponentDetail(title = "Kelas", isi = mahasiswa.kelas)
            ComponentDetail(title = "Angkatan", isi = mahasiswa.angkatan)
        }
    }
}

@Composable
fun ComponentDetail(
    modifier: Modifier = Modifier,
    title : String,
    isi : String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "$title :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = isi,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}