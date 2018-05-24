package com.credit_cure.creditcuremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ccRecycclerView;
    private RecyclerView.Adapter ccRecyclerAdapter;
    private RecyclerView.LayoutManager ccLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<VirtualCard> vc = new ArrayList<VirtualCard>();
        vc.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
        vc.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
        vc.add(new VirtualCard("6767 6767 7676 7676", "$78.12", "12/21", "512"));

        ccRecycclerView = (RecyclerView) findViewById(R.id.cc_recycler_view);
        ccLayoutManager = new LinearLayoutManager(this);
        ccRecycclerView.setLayoutManager(ccLayoutManager);

        CCAdapter ccAdapter = new CCAdapter(vc);
        ccRecycclerView.setAdapter(ccAdapter);

    }
}
