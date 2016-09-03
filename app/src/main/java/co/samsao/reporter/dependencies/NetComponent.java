package co.samsao.reporter.dependencies;

import javax.inject.Singleton;

import co.samsao.reporter.samsao.build.GitHubComponent;
import co.samsao.reporter.samsao.build.GitHubModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    GitHubComponent plus (GitHubModule module);
}
