package co.samsao.reporter.samsao;

import java.util.List;

import co.samsao.reporter.models.GitHubModel;

public interface GitHubInterface {

    interface View{

        void onComplete(List<GitHubModel> data);

        void onError(String message);
    }

    interface Presenter{
        void fetchData();
    }

}
