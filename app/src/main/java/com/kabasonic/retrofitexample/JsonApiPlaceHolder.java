package com.kabasonic.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
