package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DaoUtil extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sistemafinanceiro.db";
    private static final int VERSION = 1;


    public DaoUtil(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    public SQLiteDatabase getConexaoDataBase() {
        return this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlDDL = "CREATE TABLE usuario( " +
                " id          INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " email       VARCHAR(255) NOT NULL, " +
                " nome        VARCHAR(50) NOT NULL, " +
                " senha   VARCHAR(255) NOT NULL ) ";
        db.execSQL(sqlDDL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario ");
        onCreate(db);
    }
}
