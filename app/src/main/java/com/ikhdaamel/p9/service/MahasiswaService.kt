package com.ikhdaamel.p9.service


import retrofit2.http.Headers


interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application.json",
    )
}