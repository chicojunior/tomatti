package br.unifor.tomatti.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.unifor.tomatti.model.ModelInterface;

/**
 * Created by chico on 03/12/16.
 */

public abstract class GenericDAO<T extends ModelInterface> {

    private static final String TABLE_NAME = "tasks";
    protected SQLiteDatabase mDB;

    public GenericDAO(Context context) {

        TomattiHelper tomattiHelper = new TomattiHelper(context);
        mDB = tomattiHelper.getWritableDatabase();
    }

    public void insert(T obj) {
        mDB.insert(TABLE_NAME, null, createContentValues(obj));
    }

    public void update(T obj){
        mDB.update(TABLE_NAME,createContentValues(obj),"_id = ?", new String[]{obj.getId().toString()});
    }

    public void delete(T obj){
        mDB.delete(TABLE_NAME,"_id = ?", new String[]{obj.getId().toString()});
    }

    public abstract ContentValues createContentValues(T obj);


}

