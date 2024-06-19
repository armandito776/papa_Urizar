package com.example.proyectourizar_games;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Biblioteca extends AppCompatActivity {
    ConstraintLayout barraLateral_ContenedorPrincipal;
    TextView tvTotal;
    ListView gamesListView;
    boolean isBarActive = false;
    ArrayList<String> img_Games = new ArrayList<>();
    ArrayList<String> game_Names = new ArrayList<>();
    ArrayList<String> game_Genres = new ArrayList<>();
    ArrayList<String> game_Prices = new ArrayList<>();
    ArrayList<String> general_Reviews = new ArrayList<>();
    ArrayList<String> all_Reviews = new ArrayList<>();
    ArrayList<ArrayList<String>> game_Tags = new ArrayList<>();
    ArrayList<Integer> background_Drawable = new ArrayList<>();
    String selectQuery_user_selected_game_data, selectQuery_game_tags, selecQuery_game_data, userEmail, userName;
    Cursor Query_tbl_game_data, Query_tbl_game_tags, Query_tbl_user_selected_game_data;
    int tags_count = 0;
    int row = 0;
    int tag_index = 0;
    double total_juegos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);
        userEmail = getIntent().getStringExtra("userEmail");
        userName = getIntent().getStringExtra("userName");
        System.out.println(userEmail);
        Resources res = this.getResources();

        barraLateral_ContenedorPrincipal = findViewById(R.id.barraLateral_ContenededorPrincipal);
        gamesListView = findViewById(R.id.gamesListView);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        if (userEmail.isEmpty() || userName.isEmpty()) {
            gamesListView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Debes iniciar sesion para usar tu biblioteca", Toast.LENGTH_LONG).show();
            return;
        }

        //DATABASES
        AdminOpenHelper Ad_selectedGames = new AdminOpenHelper(this, "selectedGames", null, 1);
        SQLiteDatabase Bd_selectedGames = Ad_selectedGames.getWritableDatabase();

        AdminOpenHelper Ad_Games = new AdminOpenHelper(this, "Games", null, 1);
        SQLiteDatabase Bd_Games = Ad_Games.getWritableDatabase();

        AdminOpenHelper Ad_gameTags = new AdminOpenHelper(this, "gameTags", null, 1);
        SQLiteDatabase Bd_gameTags = Ad_gameTags.getWritableDatabase();

        selectQuery_user_selected_game_data = "SELECT * FROM selectedGames WHERE userEmail="
                + "'"+userEmail+"'";
        Query_tbl_user_selected_game_data = Bd_selectedGames.rawQuery(selectQuery_user_selected_game_data, null);

        if (!Query_tbl_user_selected_game_data.moveToFirst()) {
            System.out.println("No Games");
           return;
        }

        // GAMES (NAME, IMG, GENRE, ETC...)
        for(int index = 0; index < Query_tbl_user_selected_game_data.getCount(); index++)
        {
            selecQuery_game_data = "SELECT * FROM Games WHERE idGame="+ "'"+Query_tbl_user_selected_game_data.getString(1)+"' ORDER BY genreGame";
            Query_tbl_game_data = Bd_Games.rawQuery(selecQuery_game_data,null);

            if (!Query_tbl_game_data.moveToFirst()) {
                return;
            }
            System.out.println(Query_tbl_game_data.getString(2));
            img_Games.add(Query_tbl_game_data.getString(1));
            game_Names.add(Query_tbl_game_data.getString(2));
            game_Genres.add(Query_tbl_game_data.getString(3));
            game_Prices.add(Query_tbl_game_data.getString(4));
            general_Reviews.add(Query_tbl_game_data.getString(5));
            all_Reviews.add(Query_tbl_game_data.getString(6));
            background_Drawable.add(res.getIdentifier("games_gradient_" + Query_tbl_game_data.getString(3).toLowerCase(), "drawable", this.getPackageName()));

            Query_tbl_user_selected_game_data.moveToNext();
            Query_tbl_game_data.moveToNext();

            if (index == Query_tbl_user_selected_game_data.getCount() - 1)
            {
                Query_tbl_game_data.close();
                Bd_Games.close();
                System.out.println("AAAAAAAAA");
            }

        }

        //GAME TAGS
        Query_tbl_user_selected_game_data.moveToFirst();
        for(int index = 0; index < Query_tbl_user_selected_game_data.getCount(); index++)
        {
            selectQuery_game_tags = "SELECT idTag FROM gameTags WHERE idGame="+"'"+Query_tbl_user_selected_game_data.getString(1)+"'";
            Query_tbl_game_tags = Bd_gameTags.rawQuery(selectQuery_game_tags, null);

            tags_count = tags_count + Query_tbl_game_tags.getCount();
            Query_tbl_user_selected_game_data.moveToNext();

        }

        Query_tbl_user_selected_game_data.moveToFirst();
        ArrayList<String> game_Tags_ = new ArrayList<>();

        for(int index = 0; index < tags_count; index++)
        {
            selectQuery_game_tags = "SELECT Tag, idTag FROM gameTags WHERE idGame="+"'"+Query_tbl_user_selected_game_data.getString(1)+"'";
            Query_tbl_game_tags = Bd_gameTags.rawQuery(selectQuery_game_tags, null);

            if (tag_index >= Query_tbl_game_tags.getCount()) {
                row++;
                tag_index = 0;
                Query_tbl_game_tags.moveToFirst();
                Query_tbl_user_selected_game_data.moveToNext();
            }

            Query_tbl_game_tags.moveToPosition(tag_index);
            game_Tags_.add(Query_tbl_game_tags.getString(0));

            if (game_Tags_.size() >= Query_tbl_game_tags.getCount()) {
                game_Tags.add(row, (ArrayList<String>) game_Tags_.clone());
                game_Tags_.clear();
            }

            if (index == tags_count - 1)
            {
                Query_tbl_user_selected_game_data.close();
                Query_tbl_game_tags.close();
                Bd_Games.close();
                Bd_selectedGames.close();
                Bd_gameTags.close();
            }

            tag_index++;
        }

        LibraryGameAdapter library_adapter = new LibraryGameAdapter(getApplicationContext(), img_Games, game_Names, game_Genres, game_Prices, general_Reviews, all_Reviews, game_Tags, background_Drawable);
        gamesListView.setAdapter(library_adapter);

        for (String gamePrice : game_Prices)
        {
            total_juegos = Double.parseDouble(gamePrice) + total_juegos;
        }

        tvTotal.setText(String.format("%sMXN", total_juegos));
    }

    public void crearBarraLateral(View view)
    {
        if (isBarActive)
        {
            barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_to_left));
            barraLateral_ContenedorPrincipal.setVisibility(View.GONE);
            isBarActive = false;
            return;
        }

        barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_to_right));
        barraLateral_ContenedorPrincipal.setVisibility(View.VISIBLE);
        isBarActive = true;

    }

    public void sidebar_Tienda(View view)
    {
        Intent tienda_ = new Intent(this, Tienda.class);
        tienda_.putExtra("userEmail", userEmail);
        tienda_.putExtra("userName", userName);
        startActivity(tienda_);
    }

    public void sidebar_Library(View view)
    {
        Intent library_ = new Intent(this, Biblioteca.class);
        library_.putExtra("userEmail", userEmail);
        library_.putExtra("userName", userName);
        startActivity(library_);
    }

    public void sidebar_User(View view)
    {
        Intent user_ = new Intent(this, Usuario.class);
        user_.putExtra("userEmail", userEmail);
        user_.putExtra("userName", userName);
        startActivity(user_);
    }

    public void sidebar_Login(View view)
    {
        Intent login_ = new Intent(this, Inicio_sesion.class);

        startActivity(login_);
    }

    public void sidebar_register(View view)
    {
        Intent register_ = new Intent(this, Registro.class);

        startActivity(register_);
    }


}