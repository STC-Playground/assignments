//package com.ttpkk.assignments.assignment2;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import com.ttpkk.assignments.R;
//import com.ttpkk.assignments.assignment2.DB.AppDatabase;
//import com.ttpkk.assignments.assignment2.adapter.MyCustomAdapter;
//import com.ttpkk.assignments.assignment2.dao.ItemDao;
//import com.ttpkk.assignments.assignment2.entity.Item;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListSATOOneCheck extends AppCompatActivity {
//
//    Button backBtn;
//    RecyclerView recyclerView;
//
//    List<Item> itemList;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        backBtn = findViewById(R.id.backBtn);
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        getRoomData();
//
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void getRoomData() {
//
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "room_db").allowMainThreadQueries().build();
//        ItemDao itemDao = db.itemDao();
//
//        itemList = itemDao.getAllItems();
//        MyCustomAdapter adapter = new MyCustomAdapter(itemList);
//        recyclerView.setAdapter(adapter);
//
//    }
//}
