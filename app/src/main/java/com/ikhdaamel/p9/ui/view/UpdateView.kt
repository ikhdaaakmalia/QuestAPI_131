package com.ikhdaamel.p9.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikhdaamel.p9.ui.customwidget.CustomeTopAppBar
import com.ikhdaamel.p9.ui.navigation.DestinasiNavigasi
import com.ikhdaamel.p9.ui.view.DestinasiDetail.NIM
import com.ikhdaamel.p9.ui.viewmodel.PenyediaViewModel
import com.ikhdaamel.p9.ui.viewmodel.UpdateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Mahasiswa"
    const val NIM = "nim"
    val routesWithArg = "$route/[$NIM]"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack
            )
        }
    ){ padding ->
        EntryBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.updateUIState,
            onSiswaValueChange = viewModel::updateInsertState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMahasiswa()
                    delay(600)
                    withContext(Dispatchers.Main){
                        onNavigate()
                    }
                }
            }
        )
    }
}
