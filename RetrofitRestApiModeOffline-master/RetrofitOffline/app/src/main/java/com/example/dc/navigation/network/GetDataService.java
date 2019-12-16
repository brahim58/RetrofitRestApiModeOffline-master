package com.example.dc.navigation.network;

import com.example.dc.navigation.models.IconList;
import com.example.dc.navigation.models.PublicService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("spdiana/aula/master/data.json")
    Call<IconList> getInforData();

    @GET("api.php")
    Call<IconList> getInforData2(@Query("apicall") String type);

    @GET("api.php")
    Call<PublicService> getPublicServiceData(@Query("apicall") String type);
}
