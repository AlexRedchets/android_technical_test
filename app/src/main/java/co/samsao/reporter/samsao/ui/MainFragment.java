package co.samsao.reporter.samsao.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import co.samsao.reporter.App;
import co.samsao.reporter.R;
import co.samsao.reporter.models.GitHubModel;
import co.samsao.reporter.samsao.GitHubInterface;
import co.samsao.reporter.samsao.presenter.GitHubPresenter;

public class MainFragment extends Fragment implements GitHubInterface.View {

    @Inject
    GitHubPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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
    public void onComplete(List<GitHubModel> userData) {

    }

    @Override
    public void onError(String message) {

    }
}
