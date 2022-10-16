package com.example.myapp.dto.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.R;
import com.example.myapp.application.HomeApplication;
import com.example.myapp.constants.Urls;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryItemDTO> {
    private LayoutInflater inflater;
    private int layout;
    private List<CategoryItemDTO> categorys;

    public CategoryAdapter(Context context, int resource, List<CategoryItemDTO> categoris) {
        super(context, resource, categoris);
        this.categorys = categoris;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView imgView = view.findViewById(R.id.image);
        TextView idView = view.findViewById(R.id.category_id);
        TextView nameView = view.findViewById(R.id.name);

        CategoryItemDTO state = categorys.get(position);

        nameView.setText(state.getName());
        idView.setText(String.valueOf(state.getId()));

        String url = Urls.BASE + state.getImage();
        Glide.with(HomeApplication.getAppContext())
                .load(url)
                .apply((new RequestOptions().override(600)))
                .into(imgView);

        return view;
    }
}
