package net.irisco.starwars.feature.searchCharacter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.irisco.starwars.R;
import net.irisco.starwars.base.BaseActivity;
import net.irisco.starwars.pojo.PeopleModel;
import net.irisco.starwars.pojo.ResultModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    SearchCharacterViewModel viewmodel;
    EditText edtSearchName;
    RecyclerView recycler;
    List<PeopleModel> peopleModels = new ArrayList<>();
    SearchAdapter searchAdapter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);


        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setHasFixedSize(true);
        searchAdapter = new SearchAdapter();
        searchAdapter.SearchAdapterList(peopleModels);
        recycler.setAdapter(searchAdapter);
        viewmodel = new ViewModelProvider(this).get(SearchCharacterViewModel.class);
        edtSearchName = findViewById(R.id.edtSearchName);


        ImageView imgSearch = findViewById(R.id.imgSearch);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewmodel.setChar(edtSearchName.getText().toString());
               getData();
            }
        });
        getData();
    }

    void getData() {
        viewmodel.getResult().observe(this, new Observer<ResultModel>() {
            @Override
            public void onChanged(ResultModel resultModel) {
                if (resultModel != null) {
                    if (resultModel.getResults() != null) {
                        peopleModels = resultModel.getResults();
                        searchAdapter.SearchAdapterList(peopleModels);

                    }
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycler.setAdapter(null);
    }
}
