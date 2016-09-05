package co.samsao.reporter.samsao.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.samsao.reporter.R;

public class GitHubDialogFragment extends DialogFragment {

    @BindView(R.id.dialog_button)
    Button button;
    @BindView(R.id.dialog_name)
    TextView dialogName;
    @BindView(R.id.dialog_language)
    TextView dialogLanguage;
    @BindView(R.id.dialog_branch)
    TextView dialogBranch;
    @BindView(R.id.dialog_forks)
    TextView dialogForks;

    public GitHubDialogFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, view);

        getDialog().setTitle(R.string.dialog_title);

        button.setOnClickListener(v -> getDialog().cancel());

        Bundle args = getArguments();
        dialogName.setText(args.getString("name"));
        dialogLanguage.setText(args.getString("language"));
        dialogBranch.setText(args.getString("branch"));
        dialogForks.setText(args.getString("forks"));

        return view;
    }
}
