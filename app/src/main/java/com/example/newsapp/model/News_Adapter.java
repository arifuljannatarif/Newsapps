package com.example.newsapp.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

public class News_Adapter extends RecyclerView.Adapter<Viewholder> {
    Context mcontext;
    public News_Adapter(Context context) {
        this.mcontext=context;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.news_card,viewGroup,false);
        return new Viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder card, int tt) {
        Article_model mnews= (Article_model) NewsStorage.getitem(tt);
        card.time.setText(mnews.getTime());
        card.title.setText(mnews.getTitle());
        card.author.setText(mnews.getAuthor());
        if(mnews.getImagelink()==null)
            card.image.setVisibility(View.GONE);
        else
            Picasso.get().load(mnews.getImagelink()).placeholder(R.drawable.loading_anim).error(R.drawable.error_image).into(card.image);

            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.bottom_up);
            card.itemView.startAnimation(animation);

    }
    @Override
    public int getItemCount() {
        return NewsStorage.newsarray.size()==0 ? 0:NewsStorage.newsarray.size();
    }

}
