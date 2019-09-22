package com.android.collections.network;



import com.android.collections.models.ApiResponse;
import com.android.collections.models.CartItem;
import com.android.collections.models.Category;
import com.android.collections.models.FlashSale;
import com.android.collections.models.NewArrival;
import com.android.collections.models.NewTrend;
import com.android.collections.models.Notification;
import com.android.collections.models.RegisterResponse;
import com.android.collections.models.TopOffer;


import java.util.List;

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

    @POST("login.php")
    Call<RegisterResponse> login(@Query("username") String userName,
                                 @Query("user_pass") String userPass,
                                 @Query("fmctoken") String fmcToken,
                                 @Query("lang") String language);


    @POST("get_products_top_offers.php")
    Call<ApiResponse<List<TopOffer>>> getTopOffers(@Query("user_id") int userId,
                                                   @Query("lang") String language,
                                                   @Query("currency_id") int currencyId);

    @POST("get_products_flash_sale.php")
    Call<ApiResponse<List<FlashSale>>> getFlashSale(@Query("user_id") int userId,
                                                    @Query("lang") String language,
                                                    @Query("currency_id") int currencyId);



    @POST("get_products_flash_sale.php")
    Call<ApiResponse<List<NewTrend>>> getNewTrends(@Query("user_id") int userId,
                                                   @Query("lang") String language,
                                                   @Query("currency_id") int currencyId);
    @POST("get_products_home.php")
    Call<ApiResponse<List<NewArrival>>> getNewArrivals(@Query("user_id") int userId,
                                                       @Query("lang") String language,
                                                       @Query("currency_id") int currencyId);

    @POST("notifications.php")
    Call<List<Notification>> getNotifications(@Query("user_id") int userId,
                                                           @Query("lang") String language);


    @POST("get_categores_sub.php")
    Call<ApiResponse<List<Category>>> getMainCategories(@Query("lang") String language);


    @POST("basket.php")
    Call<ApiResponse<List<CartItem>>> getCartItems(@Query("user_id") int userId,
                                                   @Query("lang") String language,
                                                   @Query("currency_id") int currencyId);


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
