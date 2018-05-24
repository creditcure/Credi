package com.credit_cure.creditcuremobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ccRecycclerView;
    private CCAdapter ccRecyclerAdapter;
    private RecyclerView.LayoutManager ccLayoutManager;

    private ArrayList<VirtualCard> vcArray;
    CCAdapter ccAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vcArray= new ArrayList<VirtualCard>();
        vcArray.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
        vcArray.add(new VirtualCard("1234 1234 1234 1234", "$78.12", "12/21", "512"));
        vcArray.add(new VirtualCard("6767 6767 7676 7676", "$78.12", "12/21", "512"));

        ccRecycclerView = (RecyclerView) findViewById(R.id.cc_recycler_view);
        ccLayoutManager = new LinearLayoutManager(this);
        ccRecycclerView.setLayoutManager(ccLayoutManager);

        ccAdapter = new CCAdapter(vcArray);
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
            //TODO: Go to activity to add card, preferably at the start
            ccAdapter.addCreditCard(new VirtualCard("5673 1264 9372 8236", "$69.69",
                    "12/12", "123"));
            ccLayoutManager.scrollToPosition(0);
            return true;
        }
        return false;
    }
}
