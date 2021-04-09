package com.kabasonic.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private JsonApiPlaceHolder jsonApiPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                //using Interceptor for headers
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                //using once request, if need more, use .addheader()
                                .header("Headers 1: ", "value 1")
                                .build();


                        return chain.proceed(newRequest);
                    }
                })
                .build();

//        Null in empty fields
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        jsonApiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);




//        Get all posts /posts
//        getAllPosts();

//        Get post by id /posts/1 (id)
//        int postId = 3;
//        getPostById(postId);

//        Get posts/1/comments , where 1 it is postId
//        int postId = 23;
//        getCommentsForPost(postId);

//        Get post by user Id sort by id, order desc
//        int userId = 3;
//        getPostByUserId(userId);

//        Get list post by user id
//        empty list get all records
//        List<Integer> userId = new ArrayList<>();
//        userId.add(1);
//        userId.add(2);
//        userId.add(3);
//        getListPostByUserId(userId);

//        Get list post by user id using map
//        getMapPostByUserId();

//        Get list post using url
//        getPostUrl();

//          Create POST
//          createPost();

//        Update using PUT
//        updatePostPut();
//
//        Update using PATCH
//        updatePostPatch();

//        Delete using DELETE
//        deletePost();

    }

    private void deletePost(){
        Call<Void> call = jsonApiPlaceHolder.deletePost(3);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //if response code equals 200, then post has been deleted
                Log.d("#","Delete post code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void updatePostPatch(){
        /*method updatePostPatch() update only  selected records*/

        Post post = new Post (5,"Title", null);

        //Using MAP HEADERS
//        Map<String, String> mapHeaders = new HashMap<>();
//        mapHeaders.put("Header 1: ", "value 1");
//        mapHeaders.put("Header 2: ", "value 2");
//        Call<Post> call = jsonApiPlaceHolder.updatePostPut(mapHeaders, 5 ,post);


        Call<Post> call = jsonApiPlaceHolder.updatePostPatch(5,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                //if code is 200 then post is update (PUT)
                Log.d("#","Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void updatePostPut(){
        /*update records, but rewrite object*/
        Post post = new Post (5,"Title", null);
        Call<Post> call = jsonApiPlaceHolder.updatePostPut(5,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                //if code is 200 then post is update (PUT)
                Log.d("#","Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });

    }

    private void createPost(){

//        Map<String, String> fields = new HashMap<>();
//        fields.put("userId","23");
//        fields.put("title","New Title");
//        fields.put("body","New Text");
//        Call<Post> postCall = jsonApiPlaceHolder.createPost(fields);

        //userId=23&title=New%20Title&body=New%20Text
        //Call<Post> postCall = jsonApiPlaceHolder.createPost(23,"New Title","New Text");

        Post newPost = new Post(23,"New Title","New Text");
        Call<Post> postCall = jsonApiPlaceHolder.createPost(newPost);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                //if code is 201, then post created
                Log.d("#","Code POST: " + response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getPostUrl(){
        Call<List<Post>> call = jsonApiPlaceHolder.getPostUrl("posts");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getMapPostByUserId(){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("userId","1");
        queryMap.put("_sort","id");
        queryMap.put("_order","desc");
        Call<List<Post>> call = jsonApiPlaceHolder.getMapPostByUserId(queryMap);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getListPostByUserId(List<Integer> userId){
        Call<List<Post>> call = jsonApiPlaceHolder.getListPostByUserId(userId,"id","desc");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getPostByUserId(int userId){
        Call<List<Post>> call = jsonApiPlaceHolder.getPostByUserId(userId,"id","desc");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getCommentsForPost(int postId) {
        Call<List<Comment>> callCommentsList = jsonApiPlaceHolder.getCommentsForPost(postId);
        callCommentsList.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Log.d("#", "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    Log.i("#", "##########");
                    Log.i("#", "--Post Id: " + comment.getPostId());
                    Log.i("#", "--Id: " + comment.getId());
                    Log.i("#", "--Name: " + comment.getName());
                    Log.i("#", "--Email: " + comment.getEmail());
                    Log.i("#", "--Text: " + comment.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("#", "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getPostById(int postId) {
        Call<List<Post>> call = jsonApiPlaceHolder.getPostById(postId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d("#", "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                if (callPosts.isEmpty()) {
                    Log.d("#", "callPosts is empty");
                }
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("#", "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }

    private void getAllPosts() {
        Call<List<Post>> postsList = jsonApiPlaceHolder.getPosts();

        postsList.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getSimpleName(), "onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for (Post post : callPosts) {
                    Log.i("#", "##########");
                    Log.i("#", "--User ID: " + post.getUserId());
                    Log.i("#", "--ID: " + post.getId());
                    Log.i("#", "--Title: " + post.getTitle());
                    Log.i("#", "--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure|postsList|Message: " + t.getMessage());
            }
        });
    }
}