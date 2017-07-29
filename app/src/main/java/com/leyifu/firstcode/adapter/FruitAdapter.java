package com.leyifu.firstcode.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyifu.firstcode.R;
import com.leyifu.firstcode.activity.FruitActivity;
import com.leyifu.firstcode.bean.Fruit;

import java.util.List;

/**
 * Created by xingxing on 2017/7/4.
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruitList;
    private Context mContext;
    private Fruit fruit;

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra("fruit_name", fruit.getName());
                intent.putExtra("fruit_image_id",fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fruit = fruitList.get(position);
        holder.tv_fruit_name.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.iv_fruit_image);
    }

    @Override
    public int getItemCount() {
        return fruitList == null ? 0 : fruitList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView iv_fruit_image;
        TextView tv_fruit_name;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            iv_fruit_image = ((ImageView) itemView.findViewById(R.id.iv_fruit_image));
            tv_fruit_name = ((TextView) itemView.findViewById(R.id.tv_fruit_name));
        }
    }
}
