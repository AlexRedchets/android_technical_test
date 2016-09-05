package co.samsao.reporter.samsao.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import co.samsao.reporter.clients.GitHubClient;

import co.samsao.reporter.models.GitHubModel;
import co.samsao.reporter.samsao.GitHubInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GitHubPresenter implements GitHubInterface.Presenter {

    private Retrofit retrofit;
    private GitHubInterface.View view;
    private List<GitHubModel> listRepos;

    private static final String TAG = GitHubPresenter.class.getSimpleName();

    @Inject
    public GitHubPresenter(Retrofit retrofit, GitHubInterface.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    public void fetchData(){
        retrofit.create(GitHubClient.class).getRepos("samsao")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(repos -> {
                            Log.e(TAG, "Successfully got data");
                            listRepos = repos;
                            view.onComplete(repos);
                        },
                        throwable -> {
                            Log.e("Error", throwable.getMessage());
                            view.onError(throwable.getMessage());
                        });
    }

    @Override
    public void onItemClick(String name) {
        GitHubModel model;
    }

}
