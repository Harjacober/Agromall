package app.interview.agromall.remote

import app.interview.agromall.data.LatestDataRespose
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService{
    @POST("get-sample-farmers")
    @FormUrlEncoded
    fun fetchData(
        @Field("limit") limit: String,
        @Field("page") page: String

    ): Call<LatestDataRespose>
}