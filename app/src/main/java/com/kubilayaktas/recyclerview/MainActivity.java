package com.kubilayaktas.recyclerview;

import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.doviz.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DovizService service = retrofit.create(DovizService.class);

        // GET yapabilecek hale geldik
        Call<List<Doviz>> request = service.guncelDoviz();

            // farklı bir thread'de çalıştır ve sonuçları işin bittiğinde getir.
            // daha sonra hata varsa onFailure, yoksa onRespnse çalışsın.
            request.enqueue(new Callback<List<Doviz>>() {
                @Override
                public void onResponse(Call<List<Doviz>> call, Response<List<Doviz>> response) {
                    for(Doviz d : response.body()){
                        System.out.println(d.full_name + " " + d.buying);
                    }
                }

                @Override
                public void onFailure(Call<List<Doviz>> call, Throwable t) {
                    System.out.println("hata");
                }
            });


        for(int i = 0; i <= 100; i++){
            ListItem listItem = new ListItem(
                    "heading" + (i + 1),
                    "Lorem ipsum dummy context"
            );
            listItems.add(listItem);
        }

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
