package com.kabasonic.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);

        // Get all posts /posts
        //getAllPosts();

        //Get post by id /posts/1 (id)
//        int postId = 3;
//        getPostById(postId);

//        Get posts/1/comments , where 1 it is postId
//        int postId = 23;
//        getCommentsForPost(postId);

        //Get post by user Id sort by id, order desc
//        int userId = 3;
//        getPostByUserId(userId);

        //Get list post by user id
        //empty list get all records
//        List<Integer> userId = new ArrayList<>();
//        userId.add(1);
//        userId.add(2);
//        userId.add(3);
//        getListPostByUserId(userId);

        //Get list post by user id using map
        //getMapPostByUserId();

        //Get list post using url
//        getPostUrl();

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