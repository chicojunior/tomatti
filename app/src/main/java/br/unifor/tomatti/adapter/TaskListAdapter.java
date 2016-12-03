package br.unifor.tomatti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.unifor.tomatti.R;
import br.unifor.tomatti.activities.MainActivity;
import br.unifor.tomatti.model.Task;

/**
 * Created by chico on 01/12/16.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder>{

    private Context context;
    private List<Task> mTasks;
    private LayoutInflater mInflater;

    public TaskListAdapter(Context context, List<Task> tasks) {
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.mTasks = tasks;

    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.task_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {

        Task task = mTasks.get(position);

        holder.name.setText(task.getName());
        holder.description.setText(task.getDescription());
        holder.pomodoros.setText(task.getPomodoros());

    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView description;
        TextView pomodoros;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.task_item_name);
            description = (TextView) itemView.findViewById(R.id.task_item_description);
            pomodoros = (TextView) itemView.findViewById(R.id.task_item_pomodoros);

        }

    }

    /*private MainActivity.OnButtonClickedListener onButtonClickedListener = null;

    private View.OnClickListener startClockClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (onButtonClickedListener == null) {
                //onButtonClickedListener = ((SampleActivity) getActivity()).onButtonClickedListener ();
            }
            if (onButtonClickedListener != null) {
                onButtonClickedListener.onButtonClicked();
            }
        }
    };*/

}
