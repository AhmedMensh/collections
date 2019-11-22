package com.android.dev.ahmed.collections.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dev.ahmed.collections.R;
import com.android.dev.ahmed.collections.models.Address;

import java.util.ArrayList;
import java.util.List;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.ViewHolder> {

    List<Address> mAddressList;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener{
        void onItemClicked(Address address);
    }
    public ShippingAddressAdapter(ItemClickListener itemClickListener) {
        this.mAddressList = new ArrayList<>();
        this.itemClickListener =itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shipping_address_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.addressName.setText(mAddressList.get(position).getAddress());
        holder.userMobile.setText(mAddressList.get(position).getMobile());
        holder.country.setText(mAddressList.get(position).getCountry());
        if (mAddressList.get(position).getDefaultAddress().equals("yes")){
            holder.defaultAddressCB.setChecked(true);
        }
    }

    public void setData(List<Address> addressList){
        mAddressList = addressList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView addressName , userMobile , country;
        CheckBox defaultAddressCB;
        Button editBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressName = itemView.findViewById(R.id.address_name_tv);
            userMobile = itemView.findViewById(R.id.user_mobile_tv);
            country = itemView.findViewById(R.id.country_tv);
            defaultAddressCB = itemView.findViewById(R.id.default_address_cb);
            editBtn = itemView.findViewById(R.id.editBtn);

            editBtn.setOnClickListener(view -> itemClickListener.onItemClicked(mAddressList.get(getAdapterPosition())));
        }
    }
}
