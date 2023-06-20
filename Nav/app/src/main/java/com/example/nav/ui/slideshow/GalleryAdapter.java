package com.example.nav.ui.slideshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nav.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<GalleryItem> galleryItems;
    private OnItemClickListener listener;

    public GalleryAdapter(List<GalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        final GalleryViewHolder viewHolder = new GalleryViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position, galleryItems.get(position).getImageResId());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        GalleryItem galleryItem = galleryItems.get(position);
        holder.imageView.setImageResource(galleryItem.getImageResId());
        holder.textView.setText(galleryItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return galleryItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int imageResId);
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
