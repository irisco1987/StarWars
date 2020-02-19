package net.irisco.starwars.feature.detailCharacter;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.irisco.starwars.R;
import net.irisco.starwars.pojo.PeopleModel;

public class DetailActivity extends AppCompatActivity {
    DetailViewModel detailViewModel;
    TextView txtNameValue, txtHeightValue, txtMassValue, txtHaircolorValue, txtSkincolorValue, txtEyecolorValue, txtBirthdayValue, txtGenderValue;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        txtNameValue = findViewById(R.id.txtNameValue);
        txtHeightValue = findViewById(R.id.txtHeightValue);
        txtMassValue = findViewById(R.id.txtMassValue);
        txtHaircolorValue = findViewById(R.id.txtHaircolorValue);
        txtSkincolorValue = findViewById(R.id.txtSkincolorValue);
        txtEyecolorValue = findViewById(R.id.txtEyecolorValue);
        txtBirthdayValue = findViewById(R.id.txtBirthdayValue);
        txtGenderValue = findViewById(R.id.txtGenderValue);
        getData();
        if (getIntent().hasExtra("url")) {
            url = getIntent().getStringExtra("url");
            detailViewModel.setDetail(url);
        }
    }


    void getData(){
        detailViewModel.getDetail().observe(this, new Observer<PeopleModel>() {
            @Override
            public void onChanged(PeopleModel peopleModel) {
                txtNameValue.setText(peopleModel.getName());
                txtHeightValue.setText(peopleModel.getHeight());
                txtMassValue.setText(peopleModel.getMass());
                txtHaircolorValue.setText(peopleModel.getHairColor());
                txtSkincolorValue.setText(peopleModel.getSkinColor());
                txtEyecolorValue.setText(peopleModel.getEyeColor());
                txtBirthdayValue.setText(peopleModel.getBirthYear());
                txtGenderValue.setText(peopleModel.getGender());
            }
        });
    }
}
