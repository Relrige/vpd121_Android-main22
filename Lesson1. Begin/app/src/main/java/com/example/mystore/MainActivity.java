package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mystore.application.HomeApplication;
import com.example.mystore.category.CategoriesAdapter;
import com.example.mystore.contants.Urls;
import com.example.mystore.dto.category.CategoryItemDTO;
import com.example.mystore.service.ApplicationNetwork;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    CategoriesAdapter adapter;
    RecyclerView rcCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView avatar = (ImageView) findViewById(R.id.myImage);
        String url = Urls.BASE + "/images/1.jpg";
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(avatar);
        rcCategories = findViewById(R.id.rcvCategories);
        rcCategories.setHasFixedSize(true);
        rcCategories.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        rcCategories.setAdapter(new CategoriesAdapter(new ArrayList<>(), MainActivity.this::onClickDelete));


        requestServer();
    }
        void requestServer() {
            ApplicationNetwork
                    .getInstance()
                    .getCategoriesApi()
                    .list()
                    .enqueue(new Callback<List<CategoryItemDTO>>() {
                        @Override
                        public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                            if(response.isSuccessful()) {
                                List<CategoryItemDTO> list = response.body();
                                adapter = new CategoriesAdapter(list, MainActivity.this::onClickDelete);
                                rcCategories.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {

                        }
                    });

    }
    private void onClickDelete(CategoryItemDTO category) {
        ApplicationNetwork.getInstance()
                .getCategoriesApi()
                .delete(category.getId())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "Категорію видалено "+ category.getName(),
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }


}