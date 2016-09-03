package co.samsao.reporter.samsao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.samsao.reporter.R;

public class GitHubActivity extends AppCompatActivity {

      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          if (savedInstanceState == null){
              getSupportFragmentManager().beginTransaction()
                      .add(R.id.activity_main, new MainFragment())
                      .commit();
          }
  }
}
