package net.irisco.starwars.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGenerator {

//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl("https://swapi.co/api/")
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    private static Retrofit retrofit = builder.build();
//
//    private static HttpLoggingInterceptor logging =
//            new HttpLoggingInterceptor()
//                    .setLevel(HttpLoggingInterceptor.Level.BODY);
//
//    private static OkHttpClient.Builder httpClient =
//            new OkHttpClient.Builder();
//
//    public static <S> S createService(
//            Class<S> serviceClass) {
//        if (!httpClient.interceptors().contains(logging)) {
//            httpClient.addInterceptor(logging);
//            builder.client(httpClient.build());
//            retrofit = builder.build();
//        }
//
//        return retrofit.create(serviceClass);
//    }



    private static OkHttpClient httpClient =
            new OkHttpClient.Builder().build();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("https://swapi.co/api/")
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

}
