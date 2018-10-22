package com.example.tokgank.net;


import com.example.tokgank.model.Fuli;
import com.example.tokgank.model.GankoEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Hugh on 2018/4/27.
 */

public interface Serre {
    @GET("data/{category}/{count}/{page}")
    Observable<GankoEntity> getTopMovie(@Path("category") String category, @Path("count") int count, @Path("page") int page);

    @GET("data/{category}/{count}/{page}")
    Observable<Fuli> getFuli(@Path("category") String category, @Path("count") int count, @Path("page") int page);
}