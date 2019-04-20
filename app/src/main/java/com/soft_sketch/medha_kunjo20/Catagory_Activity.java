package com.soft_sketch.medha_kunjo20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Catagory_Activity extends AppCompatActivity {

    private Spinner catSpinner, strengthSpinner;
    private static final String[] categories = {"--Select Category--","Math","Science","ICT","Banking","General Knowledge"};
    private static final String[] strength = {"--Select Strength--","Simple","Moderate","Difficult"};

    private String selectedCategory = "";
    private String selectedStrength = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_);

        catSpinner = findViewById(R.id.cat_spiner_ID);
        strengthSpinner = findViewById(R.id.strength_spiner_ID);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,categories);
        catAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(catAdapter);
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        selectedCategory = "Math";
                        break;
                    case 2:
                        selectedCategory = "Science";
                        break;
                    case 3:
                        selectedCategory = "ICT";
                        break;
                    case 4:
                        selectedCategory = "Banking";
                        break;
                    case 5:
                        selectedCategory = "General Knowledge";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Catagory_Activity.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }
        });



        ArrayAdapter<String> strengthAdpter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,strength);
        strengthAdpter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        strengthSpinner.setAdapter(strengthAdpter);
        strengthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        selectedStrength = "Simple";
                        break;
                    case 2:
                        selectedStrength = "Moderate";
                        break;
                    case 3:
                        selectedStrength = "Difficult";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Catagory_Activity.this, "Please select a strength", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onNextClicked(View view) {
        if (!selectedCategory.isEmpty()||!selectedStrength.isEmpty()){
            Intent intent = new Intent(Catagory_Activity.this,QuessActivity.class);
            intent.putExtra("category",selectedCategory);
            intent.putExtra("strength",selectedStrength);
            startActivity(intent);
        }

    }
}
