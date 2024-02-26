package Ninja.coder.Ghostemane.code;

import android.util.Log;
import java.io.File;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitView {
  public File file;

  public GitView(File file) {
    this.file = file;
  }

  public boolean isGit() throws Exception {
    var repo = new FileRepositoryBuilder();
    Repository mainrepo = repo.setGitDir(file).readEnvironment().findGitDir().build();
    if (mainrepo.getDirectory().isDirectory()) {
      Log.e("Files ", mainrepo.getDirectory().getAbsolutePath());
      return true;
    } else {
      return false;
    }
  }
}
