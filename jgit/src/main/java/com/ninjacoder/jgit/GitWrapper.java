package com.ninjacoder.jgit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.ninjacoder.jgit.databinding.LayoutGitpullBinding;
import com.ninjacoder.jgit.databinding.LayoutPushBinding;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Helper class to handle git functions */
public class GitWrapper {

  /** Log TAG */
  private static final String TAG = GitWrapper.class.getSimpleName();

  /**
   * git init
   *
   * @param context context to make toast
   * @param repo repo to init
   */
  public static void init(Context context, File repo) {
    try {
      Git git = Git.init().setDirectory(repo).call();
      Toast.makeText(context, "Git init from " + git.getRepository().getDirectory(), 2).show();

    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
    }
  }

  /**
   * git add -A
   *
   * @param repo repo to stage files
   */
  public static void add(File repo, Context c) {
    new Thread(
            () -> {
              try {
                Git git = getGit(repo);
                if (git != null) {
                  git.add().addFilepattern(".").call();
                }
              } catch (GitAPIException e) {
                runOnUi(
                    () -> {
                      Toast.makeText(c, e.getLocalizedMessage(), 2).show();
                    });
              }
              runOnUi(
                  () -> {
                    Toast.makeText(c, "All file addt!", 2).show();
                  });
            })
        .start();
  }

  static void runOnUi(Runnable f){
    new Handler(Looper.getMainLooper()).post(f);
  }

  /**
   * git commit -m 'message'
   *
   * @param context context to make toast
   * @param repo repo to commit to
   * @param message git commit message
   */
  public static void commit(Context context, File repo, String message) {
    new CommitTask(
            context,
            repo,
            new String[] {
              "Committing changes", "Committed successfully.", "Unable to commit files."
            })
        .execute(message);
  }

  private static String changeTextToNone(String text) {
    if (StringUtils.isEmptyOrNull(text)) {
      return "None\n";
    }

    return text;
  }

  /**
   * git status
   *
   * @param repo repo to view status of
   * @param t text views to set status to
   */
  public static void status(File repo, TextView... t) {
    try {
      Git git = getGit(repo);
      if (git != null) {
        Status status = git.status().call();

        Set<String> conflicting = status.getConflicting();
        StringBuilder conflictingOut = new StringBuilder();
        for (String conflict : conflicting) {
          conflictingOut.append(conflict).append("\n");
        }
        t[0].setText(changeTextToNone(conflictingOut.toString()));

        Set<String> added = status.getAdded();
        StringBuilder addedOut = new StringBuilder();
        for (String add : added) {
          addedOut.append(add).append("\n");
        }
        t[1].setText(changeTextToNone(addedOut.toString()));

        Set<String> changed = status.getChanged();
        StringBuilder changedOut = new StringBuilder();
        for (String change : changed) {
          changedOut.append(change).append("\n");
        }
        t[2].setText(changeTextToNone(changedOut.toString()));

        Set<String> missing = status.getMissing();
        StringBuilder missingOut = new StringBuilder();
        for (String miss : missing) {
          missingOut.append(miss).append("\n");
        }
        t[3].setText(changeTextToNone(missingOut.toString()));

        Set<String> modified = status.getModified();
        StringBuilder modifiedOut = new StringBuilder();
        for (String mod : modified) {
          modifiedOut.append(mod).append("\n");
        }
        t[4].setText(changeTextToNone(modifiedOut.toString()));

        Set<String> removed = status.getRemoved();
        StringBuilder removedOut = new StringBuilder();
        for (String remove : removed) {
          removedOut.append(remove).append("\n");
        }
        t[5].setText(changeTextToNone(removedOut.toString()));

        Set<String> uncommitted = status.getUncommittedChanges();
        StringBuilder uncommittedOut = new StringBuilder();
        for (String uncom : uncommitted) {
          uncommittedOut.append(uncom).append("\n");
        }
        t[6].setText(changeTextToNone(uncommittedOut.toString()));

        Set<String> untracked = status.getUntracked();
        StringBuilder untrackedOut = new StringBuilder();
        for (String untrack : untracked) {
          untrackedOut.append(untrack).append("\n");
        }
        t[7].setText(changeTextToNone(untrackedOut.toString()));

        Set<String> untrackedFolders = status.getUntrackedFolders();
        StringBuilder untrackedFoldersOut = new StringBuilder();
        for (String untrackedf : untrackedFolders) {
          untrackedFoldersOut.append(untrackedf).append("\n");
        }
        t[8].setText(changeTextToNone(untrackedFoldersOut.toString()));
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
    }
  }

  /**
   * git log
   *
   * @param repo to get commits from
   * @return list of commits
   */
  public static List<RevCommit> getCommits(File repo) {
    Iterable<RevCommit> log = null;
    List<RevCommit> revCommits = new ArrayList<>();
    try {
      Git git = getGit(repo);
      if (git != null) {
        log = git.log().call();
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());

      return null;
    }

    if (log != null) {
      for (RevCommit commit : log) {
        revCommits.add(commit);
      }
    }

    return revCommits;
  }

  /**
   * git branch
   *
   * @param repo repo to get branches from
   * @return list of branches
   */
  public static List<Ref> getBranches(File repo) {
    List<Ref> branches = null;
    try {
      Git git = getGit(repo);
      if (git != null) {
        branches = git.branchList().call();
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
      return null;
    }

    return branches;
  }

  /**
   * git branch branchName
   *
   * @param context context to make toast
   * @param repo to create branch
   * @param branchName name of branch
   * @param checked switch to branch if it exists
   */
  public static void createBranch(Context context, File repo, String branchName, boolean checked) {
    if (checked) {
      new CheckoutTask(
              context,
              repo,
              new String[] {
                "Creating new branch", "Checked out successfully.", "Unable to checkout."
              })
          .execute(String.valueOf(true), branchName);
    } else {
      try {
        Git git = getGit(repo);
        if (git != null) {
          git.branchCreate().setName(branchName).call();
        }
      } catch (GitAPIException e) {
        Log.e(TAG, e.toString());
      }
    }
  }

  /**
   * git branch -d branches
   *
   * @param repo to delete branches from
   * @param branches to delete
   */
  public static void deleteBranch(File repo, String... branches) {
    try {
      Git git = getGit(repo);
      if (git != null) {
        git.branchDelete().setBranchNames(branches).call();
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
    }
  }

  /**
   * git checkout branch
   *
   * @param context context to make toast
   * @param repo to checkout to branch
   * @param branch to checkout to
   */
  public static void checkout(Context context, View view, File repo, String branch) {
    new CheckoutTask(
            context,
            repo,
            new String[] {"Checking out", "Checked out successfully.", "Unable to checkout."})
        .execute(String.valueOf(false), branch);
  }

  public static String getCurrentBranch(File repo) {
    String branch = "";
    try {
      Git git = getGit(repo);
      if (git != null) {
        branch = git.getRepository().getFullBranch();
      }
    } catch (IOException e) {
      Log.e(TAG, e.toString());
      return null;
    }

    return branch;
  }

  /**
   * git clone remoteUrl
   *
   * @param context context to make toast
   * @param repo to clone
   * @param adapter to refresh
   * @param remoteUrl to clone from
   */
  public static void push(Context context, File repo) {
    LayoutPushBinding bin = LayoutPushBinding.inflate(LayoutInflater.from(context));
    bin.remotesSpinner.setAdapter(
        new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, getRemotes(repo)));
    new MaterialAlertDialogBuilder(context)
        .setTitle("Push")
        .setMessage("")
        .setView(bin.getRoot())
        .setNegativeButton(
            "Push",
            (c, f) -> {
              new PushTask(
                      context,
                      repo,
                      new String[] {
                        "Pushing changes",
                        "Successfully pushed commits to remote.",
                        "There was a problem while pushing commits."
                      },
                      new boolean[] {
                        bin.dryRun.isChecked(),
                        bin.force.isChecked(),
                        bin.thin.isChecked(),
                        bin.tags.isChecked()
                      })
                  .execute(
                      (String) bin.remotesSpinner.getSelectedItem(),
                      bin.pushUsername.getText().toString(),
                      bin.pushPassword.getText().toString());
            })
        .show();
  }

  public static void pull(Context context, File repo) {
    LayoutGitpullBinding bin = LayoutGitpullBinding.inflate(LayoutInflater.from(context));
    var rm = bin.remote.getEditText().getText().toString();
    var user = bin.userName.getEditText().getText().toString();
    var pass = bin.token.getEditText().getText().toString();
    new MaterialAlertDialogBuilder(context)
        .setTitle("Git Pull")
        .setMessage("")
        .setView(bin.getRoot())
        .setPositiveButton(
            "pull",
            (c, cc) -> {
              new PullTask(
                      context,
                      repo,
                      new String[] {
                        "Pulling changes",
                        "Successfully pulled commits from remote.",
                        "There was a problem while pulling commits."
                      })
                  .execute(rm, user, pass);
            })
        .show();
  }

  public static void fetch(Context context, File repo) {
    LayoutGitpullBinding bin = LayoutGitpullBinding.inflate(LayoutInflater.from(context));
    var rm = bin.remote.getEditText().getText().toString();
    var user = bin.userName.getEditText().getText().toString();
    var pass = bin.token.getEditText().getText().toString();

    new MaterialAlertDialogBuilder(context)
        .setTitle("Git Pull")
        .setMessage("")
        .setPositiveButton(
            "fetch",
            (c, cc) -> {
              new FetchTask(
                      context,
                      repo,
                      new String[] {
                        "Fetching remote " + rm,
                        "Successfully fetched from " + rm + ".",
                        "There was a problem while fetching from " + rm + "."
                      })
                  .execute(rm, user, pass);
            })
        .setView(bin.getRoot())
        .show();
  }

  static Git getGit(File repo) {
    try {
      return Git.open(repo);
    } catch (IOException e) {
      Log.e(TAG, e.toString());
    }

    return null;
  }

  private static StoredConfig getConfig(File repo) {
    Git git = getGit(repo);
    if (git != null) {
      return git.getRepository().getConfig();
    }

    return null;
  }

  public static String getRemoteUrl(File repo, String remote) {
    String url = "";
    StoredConfig config = getConfig(repo);
    if (config != null) {
      url = config.getString("remote", remote, "url");
    }

    return url;
  }

  public static ArrayList<String> getRemotes(File repo) {
    ArrayList<String> remotes = null;
    StoredConfig config = getConfig(repo);
    if (config != null) {
      remotes = new ArrayList<>(config.getSubsections("remote"));
    }

    return remotes;
  }

  public static void addRemote(File repo, String remote, String url) {
    StoredConfig config = getConfig(repo);
    if (config != null) {
      config.setString("remote", remote, "url", url);
      try {
        config.save();
      } catch (IOException e) {
        Log.e(TAG, e.toString());
      }
    }
  }

  public static void removeRemote(File repo, String remote) {
    StoredConfig config = getConfig(repo);
    if (config != null) {
      config.unsetSection("remote", remote);
      try {
        config.save();
      } catch (IOException e) {
        Log.e(TAG, e.toString());
      }
    }
  }

  public static boolean canCommit(View view, File repo) {
    try {
      Git git = getGit(repo);
      if (git != null) {
        return git.getRepository().getRepositoryState().canCommit()
            && git.status().call().hasUncommittedChanges();
      }
    } catch (GitAPIException e) {
      Log.e(TAG, e.toString());
      Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG).show();
    }

    return false;
  }

  public static boolean canCheckout(View view, File repo) {
    Git git = getGit(repo);
    return git != null && git.getRepository().getRepositoryState().canCheckout();
  }

  public static SpannableString diff(View view, File repo, ObjectId hash1, ObjectId hash2) {
    SpannableString string = null;
    Git git = getGit(repo);
    try {
      if (git != null) {
        OutputStream out = new ByteArrayOutputStream();
        DiffFormatter formatter = new DiffFormatter(out);
        formatter.setRepository(git.getRepository());
        formatter.format(hash1, hash2);
        string = new SpannableString(out.toString());
      }
    } catch (IOException e) {
      Log.e(TAG, e.toString());
      Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG).show();
    }

    return string;
  }

  void msg(Context c, String ec) {
    Toast.makeText(c, ec, 2).show();
  }
}
