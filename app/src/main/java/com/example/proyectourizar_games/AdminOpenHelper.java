package com.example.proyectourizar_games;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes3.dex */
public class AdminOpenHelper extends SQLiteOpenHelper {
    public AdminOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE if EXISTS Usuarios");
        db.execSQL("DROP TABLE if EXISTS Games");
        db.execSQL("DROP TABLE if EXISTS selectedGames");
        db.execSQL("DROP TABLE if EXISTS gameTags");
        db.execSQL("CREATE TABLE Usuarios(id INTEGER PRIMARY KEY, user TEXT, pass TEXT)");
        db.execSQL("CREATE TABLE Games(idGame TEXT PRIMARY KEY, imgGame INTEGER, gameName TEXT, genreGame TEXT, price DOUBLE)");
        db.execSQL
                (
                "CREATE TABLE " +
                "selectedGames(idUsuario INTEGER NOT NULL, " +
                "idGame TEXT NOT NULL, " + "FOREIGN KEY(idUsuario) REFERENCES Usuarios(id), " +
                "FOREIGN KEY(idGame) REFERENCES Games(id)," + "CONSTRAINT PK_user_selectedGames PRIMARY KEY (idGame, idUsuario))"
                );
        db.execSQL
                (
                        "CREATE TABLE " +
                                "gameTags(idGame TEXT NOT NULL, " +
                                "idTag INTEGER NOT NULL, Tag TEXT, " +
                                "FOREIGN KEY(idGame) REFERENCES Games(idGame), " +
                                "CONSTRAINT PK_tags_and_games PRIMARY KEY(idGame, idTag))"
                );
        db.execSQL
                ("CREATE TABLE " +
                "gameDevelopers(idGame INTEGER NOT NULL, " +
                "idDeveloper INTEGER NOT NULL, Developer TEXT, " +
                "FOREIGN KEY(idGame) REFERENCES Games(idGame), " +
                "CONSTRAINT PK_game_developers PRIMARY KEY(idGame, idDeveloper))"
                );
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS Usuarios");
        db.execSQL("DROP TABLE if EXISTS Games");
        db.execSQL("DROP TABLE if EXISTS selectedGames");
        db.execSQL("DROP TABLE if EXISTS gameTags");
        db.execSQL("CREATE TABLE Games(idGame TEXT PRIMARY KEY, imgGame INTEGER, gameName TEXT, genreGame TEXT, price DOUBLE)");
        db.execSQL
                (
                        "CREATE TABLE " +
                                "selectedGames(idUsuario INTEGER NOT NULL, " +
                                "idGame TEXT NOT NULL, " + "FOREIGN KEY(idUsuario) REFERENCES Usuarios(id), " +
                                "FOREIGN KEY(idGame) REFERENCES Games(id)," + "CONSTRAINT PK_user_selectedGames PRIMARY KEY (idGame, idUsuario))"
                );
        db.execSQL
                (
                        "CREATE TABLE " +
                                "gameTags(idGame TEXT NOT NULL, " +
                                "idTag INTEGER NOT NULL, Tag TEXT, " +
                                "FOREIGN KEY(idGame) REFERENCES Games(idGame), " +
                                "CONSTRAINT PK_tags_and_games PRIMARY KEY(idGame, idTag))"
                );
        db.execSQL
                ("CREATE TABLE " +
                        "gameDevelopers(idGame TEXT NOT NULL, " +
                        "idDeveloper INTEGER NOT NULL, Developer TEXT, " +
                        "FOREIGN KEY(idGame) REFERENCES Games(idGame), " +
                        "CONSTRAINT PK_game_developers PRIMARY KEY(idGame, idDeveloper))"
                );

    }
}
