package co.samsao.reporter.clients;

import java.util.List;

import co.samsao.reporter.models.GitHubModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GitHubClient {

    @GET("{username}/repos")
    Observable<List<GitHubModel>> getRepos(
            @Path("username") String username
    );
}
