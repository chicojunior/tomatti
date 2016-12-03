package br.unifor.tomatti.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.unifor.tomatti.model.Task;

import static android.content.ContentValues.TAG;

/**
 * Created by chico on 03/12/16.
 */

public class TaskDAO {

    private static final String TABLE_NAME = "tasks";

    private SQLiteDatabase db;
    private TomattiHelper dbHelper;

    public TaskDAO(Context context) {
        dbHelper = new TomattiHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void createTask(String title, String description, String pomodoros) {
        ContentValues values = new ContentValues();
        values.put("name", title);
        values.put("description", description);
        values.put("pomodoros", pomodoros);

        db.insert(TABLE_NAME, null, values);
    }

    public void deleteTask(int taskId) {
        db.delete(TABLE_NAME, "_id = ?" + taskId, null);
    }

    /*public void updateTask(int taskId) {
        //db.update("tasks", "_id = ?" + taskId, new Integer(taskId));
        db.update("tasks", );
        int update = db.update(TABLE_NAME, "_id = ?", taskId);
    }*/


    public List listTasks() {
        List taskList = new ArrayList();

        String[] tableColumns = new String[] {"name", "description", "pomodoros"};
        Cursor cursor = db.query(TABLE_NAME, tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        try {

            while (!cursor.isAfterLast()) {
                Task task = new Task();

                task.setName(cursor.getString(0));
                task.setDescription(cursor.getString(1));
                task.setPomodoros(cursor.getString(2));

                taskList.add(task);

                cursor.moveToNext();
            }

        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return taskList;
    }


}
