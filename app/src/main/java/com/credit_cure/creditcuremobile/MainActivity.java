package com.credit_cure.creditcuremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ccRecycclerView;
    private RecyclerView.Adapter ccRecyclerAdapter;
    private RecyclerView.LayoutManager ccLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccRecycclerView = (RecyclerView) findViewById(R.id.cc_recycler_view);
        ccLayoutManager = new LinearLayoutManager(this);
        ccRecycclerView.setLayoutManager(ccLayoutManager);
    }
}
