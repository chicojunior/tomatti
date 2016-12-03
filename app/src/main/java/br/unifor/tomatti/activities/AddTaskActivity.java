package br.unifor.tomatti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.unifor.tomatti.R;
import br.unifor.tomatti.database.TaskDAO;
import br.unifor.tomatti.model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskTitle;
    private EditText taskDescription;
    private EditText taskPomodoros;
    private Button addNewTask;

    private TaskDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        dao = new TaskDAO(this);

        taskTitle = (EditText) findViewById(R.id.editTextTitulo);
        taskDescription = (EditText) findViewById(R.id.editTextDescription);
        taskPomodoros = (EditText) findViewById(R.id.editTextPomodoros);
        addNewTask = (Button) findViewById(R.id.add_task_button);

        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String title = taskTitle.getText().toString();
                    taskTitle.setText("");

                    String description = taskDescription.getText().toString();
                    taskDescription.setText("");

                    String pomodoros = taskPomodoros.getText().toString();
                    taskPomodoros.setText("");

                    //Integer pom = pomodoros

                    dao.createTask(title, description, pomodoros);

                    Toast.makeText(getApplicationContext(), "Nova tarefa adicionada!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    Log.d("Erro: ", String.valueOf(e));
                }

            }
        });
    }
}
