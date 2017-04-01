package com.sunilkumar.android.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";
    public static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    List<User.ItemsBean> Users;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_View);

        //OkHTTP client set up
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();

                                return chain.proceed(request);
                            }
                        }
                ).build();

        //Retrofit client setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiEndpointInterface service = retrofit.create(MyApiEndpointInterface.class);

        Call<User> call = service.getUserNameTom("tom");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Log.d(TAG,"onResponce"+response.code());
                if(response.isSuccessful()){
                    Users = new ArrayList<User.ItemsBean>();
                    User result =  response.body();
                    Users = result.getItems();
                    mAdapter = new UserAdapter(Users);

                    //Attach the adapter to recycler view
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}
