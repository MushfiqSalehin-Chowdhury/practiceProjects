package com.example.mushfiq.practiceprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Retro.Base_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        listView=(ListView)findViewById(R.id.heros);

        Retro api = retrofit.create(Retro.class);
        Call<List<Hero>> call = api.getHeros();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> heroes =response.body();

                String heross[] =new String[heroes.size()];

                for (int i=0; i<heroes.size();i++){
                    heross[i]="Name :" +heroes.get(i).getName()+"\n"+ "Real Name: "+heroes.get(i).getRealname();
                }

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,heross));

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Log.i("Errorr",t.getMessage().toString());

            }
        });


    }



}
