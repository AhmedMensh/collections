package com.android.collections.network;

import com.android.collections.models.RegisterResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {


    @POST("register_mem.php")
    Call<RegisterResponse> register(@Query("username") String userName,
                                    @Query("user_mobile") String userMobile,
                                    @Query("user_pass") String userPass,
                                    @Query("email") String userEmail,
                                    @Query("fmctoken") String fmcToken,
                                    @Query("lang") String language);
    class Fetcher {

        private static final String BASE_URL = "http://cool-lections.com/json_user/";
        private static Service service = null;

        public static Service getInstance() {

            if (service == null) {


//                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//                httpClient.addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request().newBuilder()
//                                .addHeader("Content-type", "application/json")
//                                .addHeader("Accept", "application/json")
//                                .build();
//                        return chain.proceed(request);
//                    }
//                });


                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                service = retrofit.create(Service.class);
            }

            return service;
        }

    }
}
