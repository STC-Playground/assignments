package com.ttpkk.assignments.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ttpkk.assignments.R;
import com.ttpkk.assignments.assignment2.DB.AppDatabase;
import com.ttpkk.assignments.assignment2.adapter.MyCustomAdapter;
import com.ttpkk.assignments.assignment2.dao.ItemDao;
import com.ttpkk.assignments.assignment2.entity.Item;

import java.util.List;

public class ResultOneCheckActivity extends AppCompatActivity {

    Button backBtn;
    RecyclerView recyclerView;
    List<Item> itemList;
    MyCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_one_check);

        backBtn = findViewById(R.id.backBtn);
        recyclerView = findViewById(R.id.recyclerView);
        loadItemList();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackAndRestart(v);
            }
        });
    }

    private void loadItemList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        ItemDao itemDao = db.itemDao();
        List<Item> itemList = itemDao.getAllItems();
        Log.d("Items : ", itemList.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myCustomAdapter = new MyCustomAdapter(itemList);
        recyclerView.setAdapter(myCustomAdapter);

    }

    public void goBackAndRestart(View view) {
        Intent intent = new Intent(this, SATOOneCheck.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
    }


}