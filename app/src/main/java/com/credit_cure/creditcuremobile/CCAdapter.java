package com.credit_cure.creditcuremobile;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Text;

public class CCAdapter extends RecyclerView.Adapter<CCAdapter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView ccView;

        public ViewHolder(CardView ccView) {
            super(ccView);
            this.ccView = ccView;
        }
    }
}
