package net.irisco.starwars.feature.searchCharacter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import net.irisco.starwars.R;
import net.irisco.starwars.base.BaseActivity;
import net.irisco.starwars.feature.detailCharacter.DetailActivity;
import net.irisco.starwars.pojo.PeopleModel;
import net.irisco.starwars.pojo.ResultModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements SearchAdapter.ItemClickListener {
    SearchCharacterViewModel viewmodel;
    EditText edtSearchName;
    RecyclerView recycler;
    List<PeopleModel> peopleModels = new ArrayList<>();
    SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgSearch = findViewById(R.id.imgSearch);
        recycler = findViewById(R.id.recycler);
        edtSearchName = findViewById(R.id.edtSearchName);

        viewmodel = new ViewModelProvider(this).get(SearchCharacterViewModel.class);
        getData();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.setChar(edtSearchName.getText().toString());
            }
        });
    }

    void getData() {
        viewmodel.getResult().observe(this, new Observer<ResultModel>() {
            @Override
            public void onChanged(ResultModel resultModel) {
                if (resultModel.getResults() != null) {
                    peopleModels = resultModel.getResults();
                    setAdapter();
                }
            }
        });
    }

    void setAdapter() {
        //TODO: Change this
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //TODO: Change this
        searchAdapter = new SearchAdapter(peopleModels, this);
        recycler.setAdapter(searchAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycler.setAdapter(null);
    }

    @Override
    public void onClick(String url) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
