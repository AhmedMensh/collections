package com.android.dev.ahmed.collections.network;


import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.AddAddressResponse;
import com.android.dev.ahmed.collections.models.Address;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.models.Category;
import com.android.dev.ahmed.collections.models.SocialLoginResponse;
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


import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {



    @POST("edit_account.php")
    Call<ApiResponse<User>> updateProfile(
            @Query("ID") String id,
            @Query("username") String userName,
            @Query("user_mobile") String userMobile,
            @Query("birthday") String birthday,
            @Query("user_pass") String userPass,
            @Query("email") String userEmail
    );
    @POST("register_mem.php")
    Call<RegisterResponse> register(@Query("username") String userName,
                                    @Query("user_mobile") String userMobile,
                                    @Query("birthday") String birthday,
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
    Call<SocialLoginResponse> loginWithFacebook(
            @Query("name") String name,
            @Query("email") String email,
            @Query("fmctoken") String fmcToken,
            @Query("picture") String picture);

    @POST("register_login_google.php")
    Call<SocialLoginResponse> loginWithGoogle(
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
            @Query("user_id") String userId,
            @Query("currency_id") int currencyId);


    @POST("get_products_trends.php")
    Call<ApiResponse<List<NewTrend>>> getNewTrends(
            @Query("lang") String language,
            @Query("user_id") String userId,
            @Query("currency_id") int currencyId);

    @POST("get_products_home.php")
    Call<ApiResponse<List<NewArrival>>> getNewArrivals(
            @Query("lang") String language,
            @Query("user_id") String userId,
            @Query("currency_id") int currencyId);

    @POST("notifications.php")
    Call<List<Notification>> getNotifications(
            @Query("lang") String language,
            @Query("user_id") String userId);


    @POST("get_categores_sub.php")
    Call<ApiResponse<List<Category>>> getMainCategories(
            @Query("lang") String language,
            @Query("user_id") String userId);


    @POST("basket.php")
    Call<CartItems> getCartItems(
            @Query("lang") String language,
            @Query("user_id") String userId,
            @Query("mac_address") String macAddress,
            @Query("currency_id") int currencyId,
            @Query("isregister") boolean isRegister);


    @POST("my_favorite.php")
    Call<ApiResponse<List<Favorite>>> getFavoriteList(
            @Query("user_id") String userId,
            @Query("lang") String language);


    @POST("account_details.php")
    Call<ApiResponse<User>> getUserProfile(
            @Query("user_id") String userId,
            @Query("lang") String language);


    @POST("my_orders.php")
    Call<ApiResponse<List<Order>>> getMyOrders(
            @Query("user_id") String userId,
            @Query("lang") String language);


    @POST("proDetails.php")
    Call<ProductDetails> getProductDetails(@Query("pro_id") int productId,
                                           @Query("user_id") String userId,
                                           @Query("lang") String language);

    @POST("add_basket.php")
    Call<ApiResponse> addToCart(@Query("pro_id") int productId,
                                @Query("user_id") String userId,
                                @Query("quantity") int quantity,
                                @Query("size_id") int sizeId,
                                @Query("color_id") int colorId,
                                @Query("isregister") boolean isRegister,
                                @Query("mac_address") String macAddress);

    @POST("add_favorite.php")
    Call<ApiResponse> addAndDeleteFromFavorite(@Query("pro_id") int productId,
                                               @Query("user_id") String userId,
                                               @Query("type") String type);


    @POST("slider.php")
    Call<ApiResponse<List<Slider>>> getSliderImages(@Query("lang") String language,
                                                    @Query("type") String type,
                                                    @Query("branch_id") int branchId);


    @POST("basket_count.php")
    Call<ApiResponse<UserCounts>> getUserCounts(@Query("user_id") int userID);

    @POST("delete_from_basket.php")
    Call<ApiResponse> deleteFromCart(
            @Query("user_id") String userId,
            @Query("cart_id") int cartId);

    @POST("basket_update_quantity.php")
    Call<ApiResponse> updateItemQuantity(
            @Query("user_id") String userId,
            @Query("pro_id") int productId,
            @Query("quantity") int quantity,
            @Query("size_id") int sizeId,
            @Query("color_id") int colorId,
            @Query("mac_address") String macAddress,
            @Query("isregister") boolean isRegister);

    @POST("get_products.php")
    Call<ApiResponse<List<NewArrival>>> getProductsByCategory(
            @Query("user_id") String userId,
            @Query("branch_id") int branchId,
            @Query("lang") String language);


    @POST("payment.php")
    Call<ApiResponse<PaymentResponse>> paymentCheckout(
            @Query("user_id") String userId,
            @Query("payment_type") int paymentType,
            @Query("lang") String language,
            @Query("delivery") Boolean delivery,
            @Query("promo_code") int promoCode,
            @Query("currency_id") int currencyId);

    @POST("get_address.php")
    Call<ApiResponse<List<Address>>> getUserAddress(
            @Query("user_id") String userId,
            @Query("lang") String language,
            @Query("isregister") boolean isregister,
            @Query("mac_address") String macAddress);


    @POST("add_edit_address.php")
    Call<ApiResponse<List<AddAddressResponse>>> addNewAddress(
            @Query("user_id") String userId,
            @Query("full_name") String fullName,
            @Query("country") String country,
            @Query("city") String city,
            @Query("Building_no") String Building_no,
            @Query("floor_no") String floorNo,
            @Query("mobile") String mobile,
            @Query("address_name") String addressName,
            @Query("address_id") int addressId,
            @Query("type") String type,
            @Query("default_address") String defaultAddress,
            @Query("lang") String lang,
            @Query("isregister") boolean isRegister,
            @Query("mac_address") String mac_address,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude);

//    http://cool-lections.com/json_user/search_all_products.php?product_name=dress&user_id=1&lang=en&currency_id=2

    @POST("search_all_products.php")
    Call<ApiResponse<List<TopOffer>>> searchInProducts(@Query("product_name") String productName,
                                @Query("user_id") String userId,
                                @Query("lang") String language,
                                @Query("currency_id") int currencyId);
    class Fetcher {


        private static final String BASE_URL = "http://cool-lections.com/json_user/";
        private static Service service = null;

        public static Service getInstance() {

            int userId = SharedPreferencesManager.getIntValue(CollectionApp.getContext(), Constants.USER_ID);
            String language = SharedPreferencesManager.getStringValue(CollectionApp.getContext(), Constants.LANGUAGE);

            if (service == null) {


                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                httpClient.addInterceptor(chain -> {


                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
//                            .addQueryParameter("lang", "ar")
//                            .addQueryParameter("user_id", userId + "")
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
