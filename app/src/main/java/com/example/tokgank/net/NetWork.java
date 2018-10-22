package com.example.tokgank.net;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hugh on 2018/4/30.
 */

public class NetWork {
    public static NetWork instance;
    private Serre serre;
    public Serre getSerre(){
        return serre;
    }

    private NetWork(){
        String baseUrl="http://gank.io/api/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        serre=retrofit.create(Serre.class);
    }

//饿汉式单例模式
    public static NetWork getInstance(){
        if (instance==null){
//            synchronized (NetWork.class){
//                if (instance==null){
                    instance=new NetWork();
//                }
            }
//        }
        return instance;
    }
}
