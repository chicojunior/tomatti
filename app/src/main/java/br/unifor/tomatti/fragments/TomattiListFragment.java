package br.unifor.tomatti.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.unifor.tomatti.R;
import br.unifor.tomatti.activities.MainActivity;
import br.unifor.tomatti.adapter.TaskListAdapter;
import br.unifor.tomatti.database.TaskDAO;
import br.unifor.tomatti.model.Task;
import br.unifor.tomatti.service.ClockService;


public class TomattiListFragment extends Fragment {

    private RecyclerView mTaskList;
    private TaskDAO dao;
    private ClockService clockService;
    private boolean isServiceBound;
    private boolean isCounterStarted;
    //private Button mainButton;
    private TextView mainContador;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        TaskListAdapter adapter = new TaskListAdapter(getContext(), getTaskList());

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        final Button mainButton = (Button) view.findViewById(R.id.task_item_btnStart);

        /*mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCounterStarted) {
                    if (isServiceBound) {
                        clockService.stopClock();
                        isCounterStarted = false;
                        mainButton.setText("Iniciar");
                    }
                } else {
                    if (isServiceBound) {
                        clockService.startClock(5);
                        isCounterStarted = true;
                        mainButton.setText("Parar");
                    }
                }
            }
        });*/

        handler = new Handler();

        mTaskList = (RecyclerView) view.findViewById(R.id.fragment_tasklist_list);
        mTaskList.setAdapter(adapter);
        mTaskList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }


    private List<Task> getTaskList() {
        dao = new TaskDAO(getActivity().getApplicationContext());
        List<Task> tasks = dao.listTasks();
        return tasks;

    }


}
