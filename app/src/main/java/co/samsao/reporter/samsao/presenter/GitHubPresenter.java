package co.samsao.reporter.samsao.presenter;

import android.util.Log;

import javax.inject.Inject;

import co.samsao.reporter.clients.GitHubClient;

import co.samsao.reporter.models.GitHubModel;
import co.samsao.reporter.samsao.GitHubInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Retrofit;

public class GitHubPresenter implements GitHubInterface.Presenter {

    private Retrofit retrofit;
    private GitHubInterface.View view;
    private Realm realm;

    private static final String TAG = GitHubPresenter.class.getSimpleName();

    @Inject
    public GitHubPresenter(Retrofit retrofit, GitHubInterface.View view, Realm realm) {
        this.retrofit = retrofit;
        this.view = view;
        this.realm = realm;
    }

    public void fetchData(){
        retrofit.create(GitHubClient.class).getRepos("samsao")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(repos -> {
                            Log.e(TAG, "Successfully got data");

                            realm.beginTransaction();
                            if (realm != null){
                                realm.deleteAll();
                            }
                            realm.copyToRealmOrUpdate(repos);
                            realm.commitTransaction();

                            view.onComplete(repos);
                        },
                        throwable -> {
                            Log.e("Error", throwable.getMessage());
                            view.onError(throwable.getMessage());
                        });
    }

    @Override
    public void fetchDataDB() {
        Log.e(TAG, "Getting data from DB");
        view.onComplete(realm.where(GitHubModel.class).findAll());
    }

}
