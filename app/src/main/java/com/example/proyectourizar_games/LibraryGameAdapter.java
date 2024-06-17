package com.example.proyectourizar_games;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class LibraryGameAdapter extends BaseAdapter {

    Context context_;
    List<String> img_Games = new ArrayList<>();
    List<String> game_Names = new ArrayList<>();
    List<String> game_Genres = new ArrayList<>();
    List<String> game_Prices = new ArrayList<>();
    List<String> general_Reviews = new ArrayList<>();
    List<String> all_Reviews = new ArrayList<>();
    List<ArrayList<String>> game_Tags = new ArrayList<>();
    List<Integer> background_Drawable = new ArrayList<>();
    LayoutInflater inflater;

    public LibraryGameAdapter(Context context, ArrayList<String> img_Games, ArrayList<String> game_Names, ArrayList<String> game_Genres, ArrayList<String> game_Prices, ArrayList<String> general_Reviews, ArrayList<String> all_Reviews, ArrayList<ArrayList<String>> game_Tags, ArrayList<Integer> background_drawable)
    {
        this.context_ = context;
        this.img_Games = img_Games;
        this.game_Names = game_Names;
        this.game_Genres = game_Genres;
        this.game_Prices = game_Prices;
        this.general_Reviews = general_Reviews;
        this.all_Reviews = all_Reviews;
        this.game_Tags = game_Tags;
        this.background_Drawable = background_drawable;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return img_Games.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position_, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.custom_library_list_view, null);

        ConstraintLayout gameContainer = convertView.findViewById(R.id.gameContainer);
        ImageView img_Game = convertView.findViewById(R.id.game_Image);
        TextView game_Name = convertView.findViewById(R.id.gameName);
        TextView game_Genre = convertView.findViewById(R.id.gameGenre);
        TextView game_Price = convertView.findViewById(R.id.gamePrice);
        TextView tv_general_Reviews = convertView.findViewById(R.id.general_reviews);
        TextView tv_all_Reviews = convertView.findViewById(R.id.all_reviews);
        TextView Tag1 = convertView.findViewById(R.id.Tag1);
        TextView Tag2 = convertView.findViewById(R.id.Tag2);
        TextView Tag3 = convertView.findViewById(R.id.Tag3);
        TextView Tag4 = convertView.findViewById(R.id.Tag4);

        gameContainer.setBackground(ContextCompat.getDrawable(context_, background_Drawable.get(position_)));
        img_Game.setImageResource(Integer.parseInt(img_Games.get(position_)));
        game_Name.setText(game_Names.get(position_));
        game_Genre.setText(game_Genres.get(position_));

        game_Price.setText(String.format("%sMXN", game_Prices.get(position_)));
        tv_general_Reviews.setText(String.format("%s %s", tv_general_Reviews.getText(), general_Reviews.get(position_)));
        tv_all_Reviews.setText(String.format("%s %s", tv_all_Reviews.getText(), all_Reviews.get(position_)));

        Tag1.setText(game_Tags.get(position_).get(0));
        Tag2.setText(game_Tags.get(position_).get(1));
        Tag3.setText(game_Tags.get(position_).get(2));
        Tag4.setText(game_Tags.get(position_).get(3));

        return convertView;
    }
}
