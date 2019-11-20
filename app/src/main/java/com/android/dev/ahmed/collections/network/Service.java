package com.android.dev.ahmed.collections.network;


import com.android.dev.ahmed.collections.MyApp;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.models.Category;
import com.android.dev.ahmed.collections.models.FacebookLoginResponse;
import com.android.dev.ahmed.collections.models.Favorite;
import com.android.dev.ahmed.collections.models.FlashSale;
import com.android.dev.ahmed.collections.models.NewArrival;
import com.android.dev.ahmed.collections.models.NewTrend;
import com.android.dev.ahmed.collections.models.Notification;
import com.android.dev.ahmed.collections.models.Order;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.models.ProductDetails;
import com.android.dev.ahmed.collections.models.RegisterResponse;
import com.android.dev.ahmed.collections.models.Slider;
import com.android.dev.ahmed.collections.models.TopOffer;
import com.android.dev.ahmed.collections.models.User;
import com.android.dev.ahmed.collections.models.UserCounts;


import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
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

    @POST("login.php")
    Call<ApiResponse<RegisterResponse>> login(@Query("username") String userName,
                                              @Query("user_pass") String userPass,
                                              @Query("fmctoken") String fmcToken,
                                              @Query("lang") String language);

    @POST("register_login_facebook.php")
    Call<FacebookLoginResponse> loginWithFacebook(
            @Query("name") String name,
            @Query("email") String email,
            @Query("fmctoken") String fmcToken,
            @Query("picture") String picture);


    @POST("get_products_top_offers.php")
    Call<ApiResponse<List<TopOffer>>> getTopOffers(
            @Query("lang") String language,
            @Query("currency_id") int currencyId);

    @POST("get_products_flash_sale.php")
    Call<ApiResponse<List<FlashSale>>> getFlashSale(
            @Query("lang") String language,
            @Query("currency_id") int currencyId);


    @POST("get_products_trends.php")
    Call<ApiResponse<List<NewTrend>>> getNewTrends(
            @Query("lang") String language,
            @Query("currency_id") int currencyId);

    @POST("get_products_home.php")
    Call<ApiResponse<List<NewArrival>>> getNewArrivals(
            @Query("lang") String language,
            @Query("currency_id") int currencyId);

    @POST("notifications.php")
    Call<List<Notification>> getNotifications(
            @Query("lang") String language);


    @POST("get_categores_sub.php")
    Call<ApiResponse<List<Category>>> getMainCategories(@Query("lang") String language);


    @POST("basket.php")
    Call<CartItems> getCartItems(
            @Query("lang") String language,
            @Query("mac_address") String macAddress,
            @Query("currency_id") int currencyId);


    @POST("my_favorite.php")
    Call<ApiResponse<List<Favorite>>> getFavoriteList(
            @Query("lang") String language);


    @POST("account_details.php")
    Call<ApiResponse<User>> getUserProfile(
            @Query("lang") String language);


    @POST("my_orders.php")
    Call<ApiResponse<List<Order>>> getMyOrders(
            @Query("lang") String language);


    @POST("proDetails.php")
    Call<ProductDetails> getProductDetails(@Query("pro_id") int productId,

                                           @Query("lang") String language);

    @POST("add_basket.php")
    Call<ApiResponse> addToCart(@Query("pro_id") int productId,

                                @Query("quantity") int quantity,
                                @Query("size_id") int sizeId,
                                @Query("color_id") int colorId);

    @POST("add_favorite.php")
    Call<ApiResponse> addAndDeleteFromFavorite(@Query("pro_id") int productId,

                                               @Query("type") String type);


    @POST("slider.php")
    Call<ApiResponse<List<Slider>>> getSliderImages(@Query("lang") String language,
                                                    @Query("type") String type,
                                                    @Query("branch_id") int branchId);


    @POST("basket_count.php")
    Call<ApiResponse<UserCounts>> getUserCounts(@Query("user_id") int userID);

    @POST("delete_from_basket.php")
    Call<ApiResponse> deleteFromCart(@Query("cart_id") int cartId);

    @POST("basket_update_quantity.php")
    Call<ApiResponse> updateItemQuantity(
            @Query("pro_id") int productId,
            @Query("quantity") int quantity,
            @Query("size_id") int sizeId,
            @Query("color_id") int colorId);

    @POST("get_products.php")
    Call<ApiResponse<List<NewArrival>>> getProductsByCategory(
            @Query("branch_id") int branchId,
            @Query("lang") String language);


    @POST("payment.php")
    Call<ApiResponse<PaymentResponse>> paymentCheckout(
            @Query("payment_type") int paymentType,
            @Query("lang") String language,
            @Query("delivery") Boolean delivery,
            @Query("promo_code") int promoCode,
            @Query("currency_id") int currencyId);


    class Fetcher {


        private static final String BASE_URL = "http://cool-lections.com/json_user/";
        private static Service service = null;

        public static Service getInstance() {

            int userId = SharedPreferencesManager.getIntValue(MyApp.getContext(), Constants.USER_ID);
            String language = SharedPreferencesManager.getStringValue(MyApp.getContext(), Constants.LANGUAGE);

            if (service == null) {


                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                httpClient.addInterceptor(chain -> {


                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
//                            .addQueryParameter("lang", "ar")
                            .addQueryParameter("user_id", userId+"")
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Content-type", "application/json")
                            .addHeader("Accept", "application/json")
                            .url(url);


                    Request request = requestBuilder.build();
                    return chain.proceed(request);


//                        Request request = chain.request().newBuilder()
//                                .addHeader("Content-type", "application/json")
//                                .addHeader("Accept", "application/json")
//                                .build();
//                        return chain.proceed(request);
                });


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
