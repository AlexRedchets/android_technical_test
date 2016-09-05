package co.samsao.reporter.samsao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import co.samsao.reporter.R;

public class GitHubActivity extends AppCompatActivity {

    private static final String TAG = GitHubActivity.class.getSimpleName();

      protected void onCreate(Bundle savedInstanceState) {
          Log.e(TAG, "onCreate started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          if (savedInstanceState == null){
              getSupportFragmentManager().beginTransaction()
                      .add(R.id.activity_main, new GitHubFragment())
                      .commit();
          }
  }
}
