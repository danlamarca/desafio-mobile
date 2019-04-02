package com.prototipo_danilo.tasks.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototipo_danilo.tasks.R;
import com.prototipo_danilo.tasks.entities.CardEntity;
import com.prototipo_danilo.tasks.entities.ProductsT;
import com.prototipo_danilo.tasks.utils.DownloadImagens;
import com.prototipo_danilo.tasks.viewholder.ItemViewHolder;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    //parametros
    private List<CardEntity> cardslist;

    //construtor
    public ItemAdapter(List<CardEntity> cards){
        this.cardslist = cards;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        //prenchendo os cards:
        final CardEntity card = cardslist.get(position);

        holder.title.setText(card.getName());
        holder.textlastprice.setText("R$ " + card.getLastPrice());
        holder.textprice.setText("R$ " + card.getPrice());
        holder.textparcelamento.setText(card.getCount() +"x de R$ "+ card.getValue());
        holder.imgoff.setText("  "+card.getRate()+"%"+"   OFF");
        new DownloadImagens(holder.imgfeaturedImage).execute(card.getImageURL());

        holder.btnLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(card.getImageTAG()));
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cardslist.size();
    }
}
