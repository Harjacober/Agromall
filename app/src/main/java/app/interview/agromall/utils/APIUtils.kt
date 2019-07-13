package app.interview.agromall.utils

import app.interview.agromall.remote.APIService
import app.interview.agromall.remote.RetrofitClient

object APIUtils {
    val BASE_URL = "https://theagromall.com/api/v2/"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
}