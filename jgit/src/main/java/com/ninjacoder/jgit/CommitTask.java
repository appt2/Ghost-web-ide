package com.ninjacoder.jgit;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class CommitTask extends GitTask {

  private static final String TAG = CommitTask.class.getSimpleName();

  CommitTask(Context context, File repo, String[] values) {
    super(context, repo, values);
    id = 4;
  }

  @Override
  protected Boolean doInBackground(String... strings) {
    try {
      Git git = GitWrapper.getGit(repo);
      if (git != null) {
        git.commit().setMessage(strings[0]).call();
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
      return false;
    }

    return true;
  }
}
