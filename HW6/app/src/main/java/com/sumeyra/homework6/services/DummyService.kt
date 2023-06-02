package com.sumeyra.homework6.services


import com.sumeyra.homework6.model.JWTData
import com.sumeyra.homework6.model.JWTUser
import com.sumeyra.homework6.model.ProductsModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService {

    @POST("/auth/login")
    fun login( @Body jwtUser: JWTUser): Call<JWTData>

    @GET("/products")
    fun getProduct(@Query("limit") limit:Int =10):Call<ProductsModel>



}