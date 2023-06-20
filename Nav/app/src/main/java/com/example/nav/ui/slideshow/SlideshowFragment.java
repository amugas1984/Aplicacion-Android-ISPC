package com.example.nav.ui.slideshow;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nav.R;
import com.example.nav.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements GalleryAdapter.OnItemClickListener {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView imageGallery = binding.imageGallery;
        imageGallery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        GalleryAdapter galleryAdapter = new GalleryAdapter(createGalleryItems());
        galleryAdapter.setOnItemClickListener(this);
        imageGallery.setAdapter(galleryAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<GalleryItem> createGalleryItems() {
        List<GalleryItem> galleryItems = new ArrayList<>();

        // Agrega aquí tus elementos de la galería (imágenes y títulos)
        // Ejemplo:
        galleryItems.add(new GalleryItem(R.drawable.imagen1, "SUPER"));
        galleryItems.add(new GalleryItem(R.drawable.imagen2, "OZONO"));
        galleryItems.add(new GalleryItem(R.drawable.imagen3, "MAXO"));
        galleryItems.add(new GalleryItem(R.drawable.imagen4, "MOLERO X"));
        galleryItems.add(new GalleryItem(R.drawable.imagen5, "MOLERO Z"));
        galleryItems.add(new GalleryItem(R.drawable.imagen6, "ZAM"));
        return galleryItems;
    }

    @Override
    public void onItemClick(int position, int imageResId) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_image); // Archivo XML para el diálogo personalizado

        ImageView imageView = dialog.findViewById(R.id.dialog_image);
        imageView.setImageResource(imageResId);

        dialog.show();
    }
}

