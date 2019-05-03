package root.iv.cocomoapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.cocomo.ProjectParam;

public class ProjectParamAdapter extends RecyclerView.Adapter<ProjectParamAdapter.ViewHolder> {
    private List<ProjectParam> financeList;
    private LayoutInflater inflater;

    public ProjectParamAdapter(LayoutInflater inflater) {
        financeList = new LinkedList<>();
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_finance, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return financeList.size();
    }

    public void append(ProjectParam finance) {
        int count = financeList.size();
        financeList.add(finance);
        notifyItemInserted(count);
    }

    public void append(List<ProjectParam> finance) {
        for (ProjectParam f : finance)
            append(f);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewAttrName;
        private TextView viewValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewAttrName = itemView.findViewById(R.id.viewAttrName);
            viewValue = itemView.findViewById(R.id.viewValue);
        }

        public void bind(int pos) {
            ProjectParam finance = financeList.get(pos);
            viewAttrName.setText(finance.getName());
            viewValue.setText(String.format(Locale.ENGLISH, "%5.2f", finance.getValue()));
        }
    }
}
