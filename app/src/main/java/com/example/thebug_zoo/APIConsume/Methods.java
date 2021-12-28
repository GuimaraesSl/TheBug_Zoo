package com.example.thebug_zoo.APIConsume;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Methods {
    @GET("exemplary/{id}")
    Call<Model> getImage(@Path("id") String _id);
}
