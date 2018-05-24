package com.credit_cure.creditcuremobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ccRecycclerView;
    private CCAdapter ccAdapter;
    private RecyclerView.LayoutManager ccLayoutManager;

    private ArrayList<VirtualCard> vcList;
    ;

    public static final String CARD_PARCEL = "card_list_parcel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ccRecycclerView = (RecyclerView) findViewById(R.id.cc_recycler_view);
        ccLayoutManager = new LinearLayoutManager(this);
        ccRecycclerView.setLayoutManager(ccLayoutManager);

        if (getIntent().getParcelableExtra(CARD_PARCEL) != null) {
            vcList = (ArrayList<VirtualCard>) getIntent().getParcelableExtra(MainActivity.CARD_PARCEL);
        } else {
            Log.d("TEST", "onCreate: Creating the list");
            vcList = new ArrayList<VirtualCard>();
            vcList.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
            vcList.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
            vcList.add(new VirtualCard("6767 6767 7676 7676", "$78.12", "12/21", "512"));
        }

        ccAdapter = new CCAdapter(vcList);
        ccRecycclerView.setAdapter(ccAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_card) {
            Intent intent = new Intent(this, AddCard.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
