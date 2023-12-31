package com.example.mystore.network;

import com.example.mystore.dto.category.CategoryItemDTO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface CategoriesApi {
    @Multipart
    @POST("/api/categories/create")
    public Call<CategoryItemDTO> create(@PartMap Map<String, RequestBody> params,
                                        @Part MultipartBody.Part image);
    @Multipart
    @POST("/api/categories/edit/{categoryId}")
    public Call<CategoryItemDTO> edit(@PartMap Map<String, RequestBody> params,
                                      @Part MultipartBody.Part image);
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> list();
    @DELETE("/api/categories/{id}")
    public Call<Void> delete(@Path("id")int id);
    Call<CategoryItemDTO> editCategory(RequestBody name, RequestBody description, MultipartBody.Part image);


}
