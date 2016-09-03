package co.samsao.reporter;

import android.app.Application;

import co.samsao.reporter.samsao.build.GitHubComponent;
import co.samsao.reporter.samsao.GitHubInterface;
import co.samsao.reporter.samsao.build.GitHubModule;
import co.samsao.reporter.dependencies.AppModule;
import co.samsao.reporter.dependencies.DaggerNetComponent;
import co.samsao.reporter.dependencies.NetComponent;
import co.samsao.reporter.dependencies.NetModule;

public class App extends Application {

    private NetComponent netComponent;
    private GitHubComponent gitHubComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com/users/"))
                .build();
    }

    public GitHubComponent getGitHubComponent(GitHubInterface.View view){
        gitHubComponent = netComponent.plus(new GitHubModule(view));
        return gitHubComponent;
    }

    public void releaseGitHubComponent(){
        gitHubComponent = null;
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
