package co.samsao.reporter.samsao;

import java.util.List;

import co.samsao.reporter.models.GitHubModel;

public interface GitHubInterface {

    interface View{

        void onComplete(List<GitHubModel> userData);

        void onError(String message);
    }

    interface Presenter{
        void fetchData();
    }

}
