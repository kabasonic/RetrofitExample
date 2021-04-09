package com.kabasonic.retrofitexample;

import android.icu.text.CaseMap;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonApiPlaceHolder {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsForPost(@Path("id") int postId);

    @GET("posts")
    Call<List<Post>> getPostById(@Query("id") int postId);

    @GET("posts")
    Call<List<Post>> getPostByUserId(@Query("userId") int userId,
                                     @Query("_sort") String sort,
                                     @Query("_desc") String order);

    @GET("posts")
    Call<List<Post>> getListPostByUserId(@Query("userId") List<Integer> userId,
                                         @Query("_sort") String sort,
                                         @Query("_desc") String order);

    @GET("posts")
    Call<List<Post>> getMapPostByUserId(@QueryMap Map<String, String> parameters );

    @GET
    Call<List<Post>> getPostUrl(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);


    //userId=23&title=New%20Title&body=New%20Text
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@Field("userId") int userId,
                          @Field("title") String title,
                          @Field("body") String body);


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    //Hard code HEADERS
//    @Headers({"Static-headers 1: text" ,"Static-headers 2: text"} )
//    @PUT("posts/{id}")
//    Call<Post> updatePostPut(@Path("id") int id, @Body Post post);

    //Dynamic HEADERS
//    @PUT("posts/{id}")
//    Call<Post> updatePostPut(@Header("Headers") String valuesHeaders,
//            @Path("id") int id,
//            @Body Post post);

    @PUT("posts/{id}")
    Call<Post> updatePostPut(@Path("id") int id,
                             @Body Post post);

    //Headers MAP
//    @PATCH("posts/{id}")
//    Call<Post> updatePostPatch(@HeaderMap Map<String, String> headersMap,
//                               @Path("id") int id,
//                               @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> updatePostPatch(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
