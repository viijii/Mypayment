package com.novisync.mypayment.webservices;


import com.novisync.mypayment.PayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("payment.php")
    Call<PayResponse> payment(@Query("paymentType") String paymentType);





}
