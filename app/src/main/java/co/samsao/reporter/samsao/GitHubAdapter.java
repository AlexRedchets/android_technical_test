package co.samsao.reporter.samsao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.samsao.reporter.R;
import co.samsao.reporter.models.GitHubModel;

public class GitHubAdapter extends RecyclerView.Adapter<GitHubAdapter.ViewHolder>{

    private List<GitHubModel> gitHubModelList;
    private Context context;
    private String TAG = GitHubAdapter.class.getSimpleName();
    private ClickListener clickListener;

    public GitHubAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void updateAdapter(List<GitHubModel> lists){
        gitHubModelList = lists;
        notifyDataSetChanged();
        Log.i(TAG, "Adapter is updated");
    }

    @Override
    public int getItemCount() {
        return gitHubModelList != null ? gitHubModelList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.custom_repo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GitHubModel currentData = gitHubModelList.get(position);

        holder.repoName.setText(currentData.getName());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.repo_text)
        TextView repoName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(gitHubModelList.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {

        void onClick(GitHubModel model);
    }

}