package br.unifor.tomatti.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.unifor.tomatti.R;
import br.unifor.tomatti.activities.AddTaskActivity;

/**
 * Created by chico on 02/12/16.
 */

public class TomattiButtonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        Button addTask = (Button) view.findViewById(R.id.add_task_button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /*public void addTask(View view) {
        Intent intent = new Intent(getContext(), AddTaskActivity.class);
        startActivity(intent);
    }*/
}
