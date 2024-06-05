package com.palash.retrofitwithhilt_mvvm.api_service

import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.AddNewCartRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_cart.response.AddNewCartResponse
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductRequest
import com.palash.retrofitwithhilt_mvvm.models.add_new_product.AddNewProductResponse
import com.palash.retrofitwithhilt_mvvm.models.delete_product.DeleteProductResponse
import com.palash.retrofitwithhilt_mvvm.models.get_all_products.GetAllProducts
import com.palash.retrofitwithhilt_mvvm.models.limit_skip_product.LimitAndSkipProductResponse
import com.palash.retrofitwithhilt_mvvm.models.login.request.LoginRequest
import com.palash.retrofitwithhilt_mvvm.models.login.response.LoginResponse
import com.palash.retrofitwithhilt_mvvm.models.product_category.ProductCategoryResponse
import com.palash.retrofitwithhilt_mvvm.models.seaech_product_response.ProductSearchResponse
import com.palash.retrofitwithhilt_mvvm.models.single_product.SingleProductResponse
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.Product
import com.palash.retrofitwithhilt_mvvm.models.update_cart.request.UpdateCartRequest
import com.palash.retrofitwithhilt_mvvm.models.update_cart.response.UpdateCartResponse
import com.palash.retrofitwithhilt_mvvm.models.update_product.UpdateProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UnauthorisedAPI {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getAllProducts(): Response<GetAllProducts>

    //https://dummyjson.com/products/1
    @GET("products/{productId}")
    suspend fun singleProduct(@Path("productId") productId: Int): Response<SingleProductResponse>

    // https://dummyjson.com/products/search?q=Cooking
    @GET("products/search")
    suspend fun searchProduct(@Query("q") query: String): Response<ProductSearchResponse>

    //https://dummyjson.com/products?limit=10&skip=0&select=title,price
    @GET("products")
    suspend fun limitAndSkipProduct(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
        @Query("select") select: CharSequence
    ): Response<LimitAndSkipProductResponse>

    //https://dummyjson.com/products/categories
    @GET("products/categories")
    suspend fun getProductCategories(): Response<ProductCategoryResponse>

    //https://dummyjson.com/products/add
    @POST("products/add")
    suspend fun productAdd(@Body addNewProductRequest: AddNewProductRequest): Response<AddNewProductResponse>

    //https://dummyjson.com/products/1
    @PUT("products/{productId}")
    suspend fun updateProduct(@Path("productId") productId: Int): Response<UpdateProductResponse>

    //https://dummyjson.com/products/1
    @DELETE("products/{productId}")
    suspend fun deleteProduct(@Path("productId") productId: Int): Response<DeleteProductResponse>

    //https://dummyjson.com/carts/add
    @POST("carts/add")
    suspend fun addToCart(@Body addNewCartRequest: AddNewCartRequest): Response<AddNewCartResponse>

    //https://dummyjson.com/carts/1
    @POST("carts/{cartId}")
    suspend fun updateCart(
        @Path("cartId") cartId: Int,
        @Body product: Product
    ): Response<UpdateCartResponse>

}