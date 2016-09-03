package co.samsao.reporter.samsao.presenter;

import android.util.Log;

import javax.inject.Inject;

import co.samsao.reporter.clients.GitHubClient;

import co.samsao.reporter.samsao.GitHubInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GitHubPresenter implements GitHubInterface.Presenter {

    private Retrofit retrofit;
    private GitHubInterface.View view;

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
                            view.onComplete(repos);
                        },
                        throwable -> {
                            Log.e("Error", throwable.getMessage());
                            view.onError(throwable.getMessage());
                        });
    }

}
