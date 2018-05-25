package com.credit_cure.creditcuremobile;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CCAdapter extends RecyclerView.Adapter<CCAdapter.ViewHolder> {

    private ArrayList<VirtualCard> vcArray;

    public CCAdapter(ArrayList<VirtualCard> vcArray) {
        this.vcArray = vcArray;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView ccView;
        public TextView ccNumberView;
        public TextView cvvDateView;
        public TextView chargeAmountView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ccView = (CardView) itemView.findViewById(R.id.cc_card_view);
            ccNumberView = (TextView) itemView.findViewById(R.id.cardNumber);
            cvvDateView = (TextView) itemView.findViewById(R.id.cvv_date_textview);
            chargeAmountView = (TextView) itemView.findViewById(R.id.cc_charge_tb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final VirtualCard vcInfo = vcArray.get(position);

                    PopupMenu popupMenu = new PopupMenu(itemView.getContext(), itemView);
                    popupMenu.getMenuInflater().inflate(R.menu.delete_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            FirebaseUtils.deleteCardFromFirebase(vcInfo.getId());
                            return true;
                        }
                    });

                    popupMenu.show();
                }
            });
        }
    }

    @Override
    public CCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cc_card_layout,
                parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int i) {
        vh.ccNumberView.setText(vcArray.get(i).getCardNumber());
        vh.cvvDateView.setText(vcArray.get(i).getCvv() + "  " + vcArray.get(i).getDate());
        vh.chargeAmountView.setText(vcArray.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return vcArray.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void addCreditCard(VirtualCard vc) {
        vcArray.add(0, vc);
        notifyItemInserted(0);
    }

    public void deleteCreditCard(String cardNumber) {
        for (int i = 0; i < vcArray.size(); i++) {
            if (vcArray.get(i).getCardNumber().equals(cardNumber))
                vcArray.remove(i);
            notifyDataSetChanged();
        }
    }

    public void setVcArray(ArrayList<VirtualCard> vcArray) {
        this.vcArray = vcArray;
    }
}
