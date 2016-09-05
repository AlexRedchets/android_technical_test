package co.samsao.reporter.samsao.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.samsao.reporter.App;
import co.samsao.reporter.R;
import co.samsao.reporter.models.GitHubModel;
import co.samsao.reporter.samsao.GitHubAdapter;
import co.samsao.reporter.samsao.GitHubInterface;
import co.samsao.reporter.samsao.presenter.GitHubPresenter;

public class GitHubFragment extends Fragment implements GitHubInterface.View, GitHubAdapter.ClickListener {

    @Inject
    GitHubPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    GitHubAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new GitHubAdapter(getContext(), this) ;
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.fetchData();
    }

    private void resolveDependencies() {
        ((App)getActivity().getApplicationContext()).getGitHubComponent(this).inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((App)getActivity().getApplicationContext()).releaseGitHubComponent();
    }

    @Override
    public void onComplete(List<GitHubModel> data) {
        adapter.updateAdapter(data);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onClick(String name) {
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }
}
