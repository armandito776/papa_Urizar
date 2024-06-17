package com.example.proyectourizar_games;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tienda extends AppCompatActivity {
    ImageView imgBtnBarra;
    ConstraintLayout barraLateral_ContenedorPrincipal;
    TableLayout tableLayout_class;
    ScrollView scrollGames;
    TextView textView_class;

    int imageCounter = 3;
    boolean isBarActive = false;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        userEmail = getIntent().getStringExtra("userEmail");
        System.out.println(userEmail);
        scrollGames = (ScrollView) findViewById(R.id.scrollGames);
        imgBtnBarra = (ImageView) findViewById(R.id.imgBarra2);
        barraLateral_ContenedorPrincipal = (ConstraintLayout) findViewById(R.id.barraLateral_ContenededorPrincipal);
        tableLayout_class = (TableLayout) findViewById(R.id.gamesAventuraTagsContainer);
        textView_class = (TextView) findViewById(R.id.textView5);


        if (userEmail.isEmpty()) {
            scrollGames.setVisibility(View.GONE);
            Toast.makeText(this, "Debes iniciar sesion para usar la tienda", Toast.LENGTH_LONG).show();
            return;
        }

        AdminOpenHelper admin = new AdminOpenHelper(this, "Games", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        // DATABASE GAMES
            // QUICK SELECT CHECK IF DATABASES HAVE BEEN CREATED
            String id_ = "Aventura1";
            String _query_test = "SELECT * FROM Games WHERE idGame="
                    + "'" + id_ +"'";
            Cursor query_test = dataBase.rawQuery(_query_test, null);

            if (query_test.moveToFirst()) {
                dataBase.close();
                return;
            }
        dataBase.close();
        //AVENTURA
        insertGames("Aventura1", R.drawable.thelegendofzeldaaventura, "The Legend Of Zelda: Breath Of The Wild", "Aventura", 1100, "Positivas", "Muy Positivas");
        insertGameTags("Aventura1", 1, "Juego De Rol");
        insertGameTags("Aventura1", 2, "Mundo Abierto");
        insertGameTags("Aventura1", 3, "Atmosferico");
        insertGameTags("Aventura1", 4, "Tercera Persona");
        insertGameDevelopers("Aventura1", 1, "Nintendo Entertainment Planning & Development");

        insertGames("Aventura2", R.drawable.godofwaraventura, "God Of War", "Aventura", 829, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Aventura2", 5, "Un Jugador");
        insertGameTags("Aventura2", 6, "Gore");
        insertGameTags("Aventura2", 7, "Mundo Abierto");
        insertGameTags("Aventura2", 8, "Souls-Like");
        insertGameDevelopers("Aventura2", 2, "Santa Monica Studio, Jetpack Interactive");

        insertGames("Aventura3", R.drawable.hogwartsaventura, "Hogwarts Legacy", "Aventura", 999, "Mayormente Positivas", "Muy Positivas");
        insertGameTags("Aventura3", 9, "Magia");
        insertGameTags("Aventura3", 10, "Mundo Abierto");
        insertGameTags("Aventura3", 11, "Tercera Persona");
        insertGameTags("Aventura3", 12, "RPG");
        insertGameDevelopers("Aventura3", 3, "Avalanche Software");

        insertGames("Aventura4", R.drawable.tearsofthekingdomaventura, "The Legend Of Zelda: Tears Of The Kingdom", "Aventura", 1200, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Aventura4", 13, "Juego De Rol");
        insertGameTags("Aventura4", 14, "Mundo Abierto");
        insertGameTags("Aventura4", 15, "Atmosferico");
        insertGameTags("Aventura4", 16, "Tercera Persona");
        insertGameDevelopers("Aventura4", 4, "Nintendo Entertainment Planning & Development");

        //ACCION
        insertGames("Accion1", R.drawable.cyberpunkaccion, "Cyberpunk 2077", "Accion", 800, "Muy Positivas", "Muy Positivas");
        insertGameTags("Accion1", 17, "Desnudos");
        insertGameTags("Accion1", 18, "Primera Persona");
        insertGameTags("Accion1", 19, "Mundo Abierto");
        insertGameTags("Accion1", 20, "Violento");
        insertGameDevelopers("Accion1", 5, "CD PROJEKT RED");

        insertGames("Accion2", R.drawable.dmc5accion, "Devil May Cry 5", "Accion", 599, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Accion2", 21, "Hack And Slash");
        insertGameTags("Accion2", 22, "Anime");
        insertGameTags("Accion2", 23, "Violento");
        insertGameTags("Accion2", 24, "Buen Soundtrack");
        insertGameDevelopers("Accion2", 6, "CAPCOM CO., Ltd.");

        insertGames("Accion3", R.drawable.dl2accion, "Dying Light 2", "Accion", 1299, "Mayormente Positivas", "Mayormente Positivas");
        insertGameTags("Accion3", 25, "Zombies");
        insertGameTags("Accion3", 26, "Horror");
        insertGameTags("Accion3", 27, "Primera Persona");
        insertGameTags("Accion3", 28, "Post Apocaliptico");
        insertGameDevelopers("Accion3", 7, "Techland");

        insertGames("Accion4", R.drawable.detroitaccion, "Detroit: Become Human", "Accion", 799, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Accion4", 29, "Cinematico");
        insertGameTags("Accion4", 30, "Futurista");
        insertGameTags("Accion4", 31, "Narracion");
        insertGameTags("Accion4", 32, "Narracion Dinamica");
        insertGameDevelopers("Accion4", 8, "Quantic Dream");

        //TERROR
        insertGames("Terror1", R.drawable.ptterror, "PT: Silent Hill", "Terror", 0, "Sin Reseñas", "Sin Reseñas");
        insertGameTags("Terror1", 33, "Primera Persona");
        insertGameTags("Terror1", 34, "Horror");
        insertGameTags("Terror1", 35, "Violento");
        insertGameTags("Terror1", 36, "Terror Psicologico");
        insertGameDevelopers("Terror1", 9, "KONAMI");

        insertGames("Terror2", R.drawable.amnessiaterror, "Amnesia: The Dark Descent", "Terror" ,227.99, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Terror2", 37, "Horror");
        insertGameTags("Terror2", 38, "Sigilo");
        insertGameTags("Terror2", 39, "Walking Simulator");
        insertGameTags("Terror2", 40, "Exploracion");
        insertGameDevelopers("Terror2", 10, "Frictional Games");

        insertGames("Terror3", R.drawable.re7terror, "Resident Evil 7 Biohazard", "Terror", 399, "Extremadamente Positivas", "Extremadamente Positivas");
        insertGameTags("Terror3", 41, "Terror Psicologico");
        insertGameTags("Terror3", 42, "Zombies");
        insertGameTags("Terror3", 43, "Survival Horror");
        insertGameTags("Terror3", 44, "Primera Persona");
        insertGameDevelopers("Terror3", 11, "CAPCOM CO., Ltd.");

        insertGames("Terror4", R.drawable.kf2accion, "Killing Floor 2", "Terror", 200, "Mayormente Positivas", "Muy Positivas");
        insertGameTags("Terror4", 45, "Zombies");
        insertGameTags("Terror4", 46, "Primera Persona");
        insertGameTags("Terror4", 47, "Online Co-Op");
        insertGameTags("Terror4", 48, "Hack And Slash");
        insertGameDevelopers("Terror4", 12, "Tripwire Interactive");

        //ESTRATEGIA
        insertGames("Estrategia1", R.drawable.lolestrategia, "League Of Legendds", "Estrategia", 0, "Muy Positivas", "Muy Positivas");
        insertGameTags("Estrategia1", 49, "Competitivo");
        insertGameTags("Estrategia1", 50, "Juego De Rol");
        insertGameTags("Estrategia1", 51, "Magico");
        insertGameTags("Estrategia1", 52, "MOBA");
        insertGameDevelopers("Estrategia1", 13, "RIOT GAMES");

        insertGames("Estrategia2", R.drawable.tf2estrategia, "Team Fortress 2", "Estrategia", 0, "Extremadamente Negativas (Maldito Valve)", "Muy Positivas");
        insertGameTags("Estrategia2", 53, "Free To Play");
        insertGameTags("Estrategia2", 54, "Tactico");
        insertGameTags("Estrategia2", 55, "Class-Based");
        insertGameTags("Estrategia2", 56, "Online Co-Op");
        insertGameDevelopers("Estrategia2", 14, "Valve");

        insertGames("Estrategia3", R.drawable.ttestrategia, "Teamlight Tactics", "Estrategia", 0, "Muy Positivas", "Muy Positivas");
        insertGameTags("Estrategia3", 57, "Competitivo");
        insertGameTags("Estrategia3", 58, "Juego De Rol");
        insertGameTags("Estrategia3", 59, "MOBA");
        insertGameTags("Estrategia3", 60, "Camara Isometrica");
        insertGameDevelopers("Estrategia3", 15, "RIOT GAMES");

        insertGames("Estrategia4", R.drawable.valorantestrategia, "VALORANT", "Estrategia", 0, "Mayormente Positivas", "Muy Positivas");
        insertGameTags("Estrategia4", 61, "FPS");
        insertGameTags("Estrategia4", 62, "Online Co-Op");
        insertGameTags("Estrategia4", 63, "Tactical Shooter");
        insertGameTags("Estrategia4", 64, "Competitivo");
        insertGameDevelopers("Estrategia4", 16, "RIOT GAMES");
    }

    public void crearBarraLateral(View view)
    {
        if (isBarActive)
        {
            barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up));
            barraLateral_ContenedorPrincipal.setVisibility(View.GONE);
            isBarActive = false;
            return;
        }

        barraLateral_ContenedorPrincipal.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down));
        barraLateral_ContenedorPrincipal.setVisibility(View.VISIBLE);
        isBarActive = true;

    }

    public void insertGames(String idGame, int imgGame, String gameName, String genreGame, double price, String general_Reviews, String all_Reviews)
    {
        AdminOpenHelper admin = new AdminOpenHelper(this, "Games", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        ContentValues gameData = new ContentValues();
        gameData.put("idGame", idGame);
        gameData.put("imgGame", imgGame);
        gameData.put("gameName", gameName);
        gameData.put("genreGame", genreGame);
        gameData.put("price", price);
        gameData.put("general_reviews", general_Reviews);
        gameData.put("all_reviews", all_Reviews);
        dataBase.insert("Games", null, gameData);
        dataBase.close();
    }

    public void insertGameTags(String idGame, int idTag, String Tag)
    {
        AdminOpenHelper admin = new AdminOpenHelper(this, "gameTags", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        ContentValues gameTags_Data = new ContentValues();
        gameTags_Data.put("idGame", idGame);
        gameTags_Data.put("idTag", idTag);
        gameTags_Data.put("Tag", Tag);
        dataBase.insert("gameTags", null, gameTags_Data);
        dataBase.close();
    }

    public void insertGameDevelopers(String idGame, int idDeveloper, String Developer)
    {
        AdminOpenHelper admin = new AdminOpenHelper(this, "gameDevelopers", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        ContentValues gameDevelopers_Data = new ContentValues();
        gameDevelopers_Data.put("idGame", idGame);
        gameDevelopers_Data.put("idDeveloper", idDeveloper);
        gameDevelopers_Data.put("Developer", Developer);
        dataBase.insert("gameDevelopers", null, gameDevelopers_Data);
        dataBase.close();
    }

    public void insertSelectedGame(String idGame)
    {
        AdminOpenHelper admin = new AdminOpenHelper(this, "selectedGames", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String check_if_game_was_already_selected = "SELECT idGame, userEmail FROM selectedGames WHERE idGame="
                + "'" + idGame+"' AND userEmail="  + "'" +userEmail+"'";
        Cursor Query_check_if_game = dataBase.rawQuery(check_if_game_was_already_selected,null);

        if (Query_check_if_game.moveToFirst())
        {
            dataBase.delete("selectedGames","userEmail=" + "'" +userEmail+"' AND idGame=" + "'" +idGame+"'",null);
            System.out.println(Query_check_if_game.getString(0) + Query_check_if_game.getString(1));
            Toast.makeText(this, "Juego Deseleccionado", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues selectedGameData = new ContentValues();
        selectedGameData.put("userEmail", userEmail);
        selectedGameData.put("idGame", idGame);
        dataBase.insert("selectedGames", null, selectedGameData);
        dataBase.close();
        Toast.makeText(this, "Juego Seleccionado", Toast.LENGTH_SHORT).show();
    }

    public void onClickGameImage(View gameImage)
    {
        ConstraintLayout gameContainer = (ConstraintLayout) gameImage.getParent();

        for (View gameImages : getGameImages(gameContainer))
        {
            if (gameImages.getVisibility() == View.VISIBLE)
            {
                gameImages.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                insertSelectedGame(gameImages.getContentDescription().toString());
            }
        }

    }

    public void leftArrowFunc(View view)
    {
        ConstraintLayout gameContainer = (ConstraintLayout) view.getParent();

        for (View gameImages : getGameImages(gameContainer))
        {
            if (gameImages.getVisibility() == View.VISIBLE)
            {
                imageCounter = getGameImages(gameContainer).indexOf(gameImages);
            }
        }

        if (imageCounter == 0) { return; }
        setGameInfo(gameContainer, imageCounter - 1);
        setGameTags(gameContainer, imageCounter - 1);

        getGameImages(gameContainer).get(imageCounter).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_arrow_animation));
        getGameImages(gameContainer).get(imageCounter - 1).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_arrow_animation2));

        getGameImages(gameContainer).get(imageCounter - 1).setVisibility(View.VISIBLE);
        getGameImages(gameContainer).get(imageCounter).setVisibility(View.GONE);

        /*
        if (getGameImages(gameContainer).get(1).getContentDescription().toString().contains("Aventura"))
        {
            getGameImages(gameContainer).get(aventuraCounter).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_arrow_animation));
            if (aventuraCounter == 0) { aventuraCounter = 4; }
            getGameImages(gameContainer).get(aventuraCounter - 1).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_arrow_animation2));
            aventuraCounter--;

        }

        int index = getTableLayout(gameContainer).indexOf(gameTags);
            Cursor Query_tbl = Bd.rawQuery("SELECT Tag FROM gameTags WHERE idGame=" + getGameImages(gameContainer).get(index).getContentDescription().toString(), null);
            if (!Query_tbl.moveToFirst())
            {
                Bd.close();
                return;
            } else
            {

                Bd.close();
            }

         */
    }
    public void rightArrowFunc(View view)
    {
        ConstraintLayout gameContainer = (ConstraintLayout) view.getParent();

        for (View gameImages : getGameImages(gameContainer))
        {
            if (gameImages.getVisibility() == View.VISIBLE)
            {
                imageCounter = getGameImages(gameContainer).indexOf(gameImages);
            }
        }

        if (imageCounter == 3) { return; }
        setGameInfo(gameContainer, imageCounter + 1);
        setGameTags(gameContainer, imageCounter + 1);

        getGameImages(gameContainer).get(imageCounter).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_arrow_animation));
        getGameImages(gameContainer).get(imageCounter + 1).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_arrow_animation2));

        getGameImages(gameContainer).get(imageCounter + 1).setVisibility(View.VISIBLE);
        getGameImages(gameContainer).get(imageCounter).setVisibility(View.GONE);
    }

    public void setGameTags (View parent, int imageIndex)
    {
        AdminOpenHelper Ad = new AdminOpenHelper(this, "gameTags", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        String selectQuery_tags = "SELECT Tag FROM gameTags WHERE idGame="
                + "'" + getGameImages(parent).get(imageIndex).getContentDescription()+"'";

        Cursor Query_tbl_tags = Bd.rawQuery(selectQuery_tags, null);
        if (!Query_tbl_tags.moveToFirst())
        {
            System.out.println("No funciona xd");
            Bd.close();
            return;
        }

        int row_index = 0;
        int n_Elements = 0;

        for(int index = 0; index < getTableLayout(parent).size(); index++)
        {
            n_Elements = getTableLayout(parent).get(index).getChildCount() + n_Elements;
        }

        int index_of_element = 0;
        for(int index = 0; index < n_Elements; index++)
        {
            if (index_of_element >= getTableLayout(parent).get(row_index).getChildCount())
            {
                row_index++;
                index_of_element = 0;
            }

            TextView textViews = (TextView) ((TableRow) getTableLayout(parent).get(row_index)).getChildAt(index_of_element);
            textViews.setText(Query_tbl_tags.getString(0));
            Query_tbl_tags.moveToNext();
            index_of_element++;
        }
        Bd.close();
    }

    public void setGameInfo (View parent, int imageIndex)
    {
        AdminOpenHelper Ad = new AdminOpenHelper(this, "Games", null, 1);
        SQLiteDatabase Bd = Ad.getWritableDatabase();

        String selectQuery_game_info = "SELECT gameName, price FROM Games WHERE idGame="
                + "'"+getGameImages(parent).get(imageIndex).getContentDescription()+"'";;

        Cursor Query_tbl_game_info = Bd.rawQuery(selectQuery_game_info, null);
        if (!Query_tbl_game_info.moveToFirst())
        {
            System.out.println("No funciona xd");
            Bd.close();
            return;
        }

        for (TextView gametextViews : getGameTextViews(parent))
        {
            gametextViews.setText(Query_tbl_game_info.getString(getGameTextViews(parent).indexOf(gametextViews)));
        }
        Bd.close();
    }

    public List<View> getGameImages(View parent)
    {
        List<View> imageViews = new ArrayList<>();
        List<View> gameImages = new ArrayList<>();
        for(int index = 0; index < ((ViewGroup) parent).getChildCount(); index++)
        {
            View children = ((ViewGroup) parent).getChildAt(index);

            if (children.getClass() == imgBtnBarra.getClass())
            {
                imageViews.add(children);
            }
        }

        for (View images : imageViews)
        {
            String contentDescription = (String) images.getContentDescription();
            if (contentDescription != null)
            {
                gameImages.add(images);
            }
        }

        return gameImages;
    }

    public List<TextView> getGameTextViews (View parent)
    {
        List<TextView> textViews_list = new ArrayList<>();
        for(int index = 0; index < ((ViewGroup) parent).getChildCount(); index++)
        {
            View children = ((ViewGroup) parent).getChildAt(index);

            if (children.getClass() == textView_class.getClass())
            {
                textViews_list.add((TextView) children);
            }
        }
        return textViews_list;
    }


    public List<TableRow> getTableLayout(View parent)
    {
        TableLayout table_ = null;
        List<TableRow> tableRows_List = new ArrayList<>();

        for(int index = 0; index < ((ViewGroup) parent).getChildCount(); index++)
        {
            View children = ((ViewGroup) parent).getChildAt(index);

            if (children.getClass() == tableLayout_class.getClass())
            {
                table_ = (TableLayout) children;
            }
        }

        for(int index = 0; index < ((ViewGroup) Objects.requireNonNull(table_)).getChildCount(); index++)
        {
            TableRow tableRows = (TableRow) ((ViewGroup) table_).getChildAt(index);
            if (tableRows == null) { continue; }
            tableRows_List.add(tableRows);
        }

        return tableRows_List;
    }

    /*
    public TextView getTableRowsElements(List<TableRow> tableRowArrayList, int nRow)
    {
        List<TextView> rowElements = new ArrayList<>();
        for(int index = 0; index < ((TableRow) tableRowArrayList.get(nRow)).getChildCount(); index++)
        {
            TextView textViews = (TextView) ((TableRow) tableRowArrayList.get(nRow)).getChildAt(index);
            rowElements.add(textViews);
        }
        return (TextView) rowElements;
    }
     */

    public void sidebar_Tienda(View view)
    {
        Intent tienda_ = new Intent(this, Tienda.class);
        tienda_.putExtra("userEmail", userEmail);
        startActivity(tienda_);
    }

    public void sidebar_Library(View view)
    {
        Intent library_ = new Intent(this, Biblioteca.class);
        library_.putExtra("userEmail", userEmail);
        startActivity(library_);
    }

    public void sidebar_User(View view)
    {
        Intent user_ = new Intent(this, Usuario.class);
        user_.putExtra("userEmail", userEmail);
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