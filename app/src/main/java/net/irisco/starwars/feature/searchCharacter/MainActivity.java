package net.irisco.starwars.feature.searchCharacter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.widget.RxTextView;

import net.irisco.starwars.R;
import net.irisco.starwars.base.BaseActivity;
import net.irisco.starwars.pojo.PeopleModel;
import net.irisco.starwars.pojo.ResultModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MainActivity extends BaseActivity {
    SearchCharacterViewModel viewmodel;
    EditText edtSearchName;
    RecyclerView recycler;
    List<PeopleModel> peopleModels = new ArrayList<>();
    SearchAdapter searchAdapter;
    CompositeDisposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgSearch = findViewById(R.id.imgSearch);
        recycler = findViewById(R.id.recycler);
        edtSearchName = findViewById(R.id.edtSearchName);

        disposable = new CompositeDisposable();


        viewmodel = new ViewModelProvider(this).get(SearchCharacterViewModel.class);


        disposable.add(RxTextView
                .textChanges(edtSearchName)
                .debounce(2, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 3)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {

                    }
                }));

        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        //TODO: Change this
        searchAdapter = new SearchAdapter();
        searchAdapter.SearchAdapterList(peopleModels);
        recycler.setAdapter(searchAdapter);

        viewmodel.getResult().observe(this, new Observer<ResultModel>() {
            @Override
            public void onChanged(ResultModel resultModel) {
                if (resultModel.getResults() != null) {
                    peopleModels = resultModel.getResults();
                    searchAdapter.SearchAdapterList(peopleModels);
                }
            }
        });


        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewmodel.setChar(edtSearchName.getText().toString());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycler.setAdapter(null);
        disposable.dispose();
    }

}
