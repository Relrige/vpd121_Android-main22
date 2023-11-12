package com.example.mystore.network;

import com.example.mystore.dto.acount.LoginDTO;
import com.example.mystore.dto.acount.LoginResponse;
import com.example.mystore.dto.acount.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<Void> register(@Body RegisterDTO registerDTO);
    @POST("/api/account/login")
    public Call<LoginResponse> login(@Body LoginDTO loginDTO);
}