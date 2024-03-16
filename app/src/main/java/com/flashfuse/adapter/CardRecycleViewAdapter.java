package com.flashfuse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flashfuse.R;
import com.flashfuse.data.dao.CardDAO;
import com.flashfuse.data.entity.Card;

import java.util.List;

public class CardRecycleViewAdapter extends RecyclerView.Adapter<CardRecycleViewAdapter.ViewHolder> {
    Context context;
    List<Card> list;
    CardDAO cardDAO;

    public void setList(List<Card> list) {
        this.list = list;
    }

    public CardRecycleViewAdapter(Context context, List<Card> list) {
        this.context = context;
        this.list = list;
        cardDAO = new CardDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = list.get(position);
        holder.front.setText(card.getFront());
        holder.back.setText(card.getBack());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView front;
        public TextView back;

        public ViewHolder(View itemView) {
            super(itemView);
            front = (TextView) itemView.findViewById(R.id.tv_card_front);
            back = (TextView) itemView.findViewById(R.id.tv_card_back);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, v);
                    popupMenu.getMenuInflater().inflate(R.menu.option, popupMenu.getMenu());
                    popupMenu.show();
                    return true;
                }
            });

        }
    }
}
