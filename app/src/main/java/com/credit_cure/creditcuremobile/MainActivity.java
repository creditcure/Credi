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
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView ccRecycclerView;
    private CCAdapter ccAdapter;
    private RecyclerView.LayoutManager ccLayoutManager;
    private ProgressBar pBar;

    private ArrayList<VirtualCard> vcList;

    private DatabaseReference databaseReference;

    public static final String CARD_PARCEL = "card_list_parcel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pBar.setVisibility(View.VISIBLE);

        vcList = new ArrayList<VirtualCard>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addChildEventListener(childEventListener);
        databaseReference.addValueEventListener(cardListener);

        ccRecycclerView = (RecyclerView) findViewById(R.id.cc_recycler_view);
        ccLayoutManager = new LinearLayoutManager(this);
        ccRecycclerView.setLayoutManager(ccLayoutManager);

        if (getIntent().getExtras() != null) {
            VirtualCard vc = (VirtualCard) getIntent().getExtras().getSerializable(MainActivity.CARD_PARCEL);
            FirebaseUtils.writeCardToFirebase(vc);
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
            intent.putExtra(MainActivity.CARD_PARCEL, vcList);
            startActivity(intent);
            return true;
        }
        return false;
    }

    ValueEventListener cardListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            vcList = new ArrayList<VirtualCard>();

            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                VirtualCard vc = postSnapshot.getValue(VirtualCard.class);
                vc.setId(postSnapshot.getKey());
                vcList.add(vc);
            }

            pBar.setVisibility(View.INVISIBLE);
            Collections.reverse(vcList);
            ccAdapter.setVcArray(vcList);
            ccAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.print("Bad");
        }
    };

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            VirtualCard vc = dataSnapshot.getValue(VirtualCard.class);

            for (int i = 0; i < vcList.size(); i++) {
                if (vcList.get(i).equals(vc))
                    vcList.remove(i);
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
