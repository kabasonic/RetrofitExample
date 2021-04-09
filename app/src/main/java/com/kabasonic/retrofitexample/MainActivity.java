package com.kabasonic.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiPlaceHolder jsonApiPlaceHolder = retrofit.create(JsonApiPlaceHolder.class);

        Call<List<Post>> postsList = jsonApiPlaceHolder.getPosts();

        postsList.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    Log.d(getClass().getSimpleName(),"onResponse|postList|Message" + response.message());
                    return;
                }
                List<Post> callPosts = response.body();
                for(Post post:callPosts){
                    Log.i("#","##########");
                    Log.i("#","--User ID: " + post.getUserId());
                    Log.i("#","--ID: " + post.getId());
                    Log.i("#","--Title: " + post.getTitle());
                    Log.i("#","--Body: " + post.getText());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getSimpleName(),"onFailure|postsList|Message: " + t.getMessage());
            }
        });

    }
}