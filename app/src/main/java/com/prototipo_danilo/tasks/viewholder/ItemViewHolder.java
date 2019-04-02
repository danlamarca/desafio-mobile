package com.prototipo_danilo.tasks.viewholder;

import android.graphics.Paint;
import android.media.Image;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prototipo_danilo.tasks.R;

import org.w3c.dom.Text;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgfeaturedImage;
    public TextView title;
    public TextView textlastprice;
    public TextView textprice;
    public TextView textparcelamento;
    public TextView imgoff;
    public AppCompatButton btnLink;
    private ImageView imgfavorito1;
    private ImageView imgfavorito2;

    public ItemViewHolder(View itemView) {
        super(itemView);

        //inicializando as views
        this.title = (TextView) itemView.findViewById(R.id.title);
        this.textlastprice = (TextView)itemView.findViewById(R.id.textlastprice);
        this.textprice = (TextView)itemView.findViewById(R.id.textprice);
        this.textparcelamento = (TextView)itemView.findViewById(R.id.textparcelamento);
        this.imgfeaturedImage = (ImageView)itemView.findViewById(R.id.imgfeaturedImage);
        this.imgoff = (TextView)itemView.findViewById(R.id.imgoff);
        this.btnLink =(AppCompatButton)itemView.findViewById(R.id.btnLink);
        imgfavorito1 = (ImageView) itemView.findViewById(R.id.imgfavorito1);
        imgfavorito2 = (ImageView) itemView.findViewById(R.id.imgfavorito2);
        imgfavorito1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgfavorito1.setVisibility(View.GONE);
                imgfavorito2.setVisibility(View.VISIBLE);
            }
        });

        //tachado no lastprice
        this.textlastprice.setPaintFlags(this.textlastprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
