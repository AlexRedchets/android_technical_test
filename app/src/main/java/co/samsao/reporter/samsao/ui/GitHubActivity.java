package co.samsao.reporter.samsao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import co.samsao.reporter.App;
import co.samsao.reporter.R;
import co.samsao.reporter.models.GitHubModel;
import co.samsao.reporter.samsao.GitHubInterface;
import co.samsao.reporter.samsao.presenter.GitHubPresenter;

public class GitHubActivity extends AppCompatActivity implements GitHubInterface.View {

    @Inject
    GitHubPresenter presenter;

      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      resolveDependencies();

          presenter.fetchData();
  }

    private void resolveDependencies() {
        ((App)getApplicationContext()).getGitHubComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((App)getApplicationContext()).releaseGitHubComponent();
    }

    @Override
    public void onComplete(List<GitHubModel> userData) {

    }

    @Override
    public void onError(String message) {

    }
}
