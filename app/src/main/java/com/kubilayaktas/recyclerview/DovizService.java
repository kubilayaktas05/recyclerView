package com.kubilayaktas.recyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by MSI on 23.07.2018 at 23:08.
 */
public interface DovizService {
    @GET("currencies/all/latest")
    Call<List<Doviz>> guncelDoviz();
}
