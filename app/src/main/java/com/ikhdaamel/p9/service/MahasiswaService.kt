package com.ikhdaamel.p9.service

import com.ikhdaamel.p9.model.Mahasiswa
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application.json",
    )
    @GET("bacamahasiswa.php")
    suspend fun getMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswaById(@Query("nim")nim:String):Mahasiswa

}