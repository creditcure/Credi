package com.credit_cure.creditcuremobile;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtils {

    public static void writeCardToFirebase(VirtualCard v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dRef = database.getReference().push();

        dRef.setValue(v);
    }

    public static void deleteCardFromFirebase(String id) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dRef = database.getReference();

        dRef.child(id).removeValue();
    }

}
