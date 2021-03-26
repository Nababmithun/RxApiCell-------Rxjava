package com.example.rxapicell______rxjava.View.View;

import android.database.Observable;

import com.example.rxapicell______rxjava.View.View.Model.ResponsePojo;

import retrofit2.http.POST;

public interface ApiService {

    @POST("dummy_response.php")
    Observable<ResponsePojo> getDataFromAPI();
}
