package rest;

import object.Student;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mari on 16/11/2016.
 */

public interface RetrofitService {

    @GET("student")
    Call<Student> converterUnidade(@Query("userCode") int userCode);


}
