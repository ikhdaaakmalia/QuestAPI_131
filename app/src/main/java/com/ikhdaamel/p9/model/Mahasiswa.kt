package com.ikhdaamel.p9.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mahasiswa (
    val nim: String,
    val nama: String,
    val alamat: String,

    @SerialName("jenis_kelamin")        //menyamakan dengan nama tabel di db
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String
)