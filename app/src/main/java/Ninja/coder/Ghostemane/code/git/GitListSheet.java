package Ninja.coder.Ghostemane.code.git;

import Ninja.coder.Ghostemane.code.databinding.MakefolderBinding;
import Ninja.coder.Ghostemane.code.model.ListSheet;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitListSheet {
  protected ListSheet listItem;
  protected File fileDir;
  protected Context ctx;
  protected GitUtils utils;
  private MaterialAlertDialogBuilder dialog;
  private MakefolderBinding bin;

  public GitListSheet(File fileDir, Context ctx) {
    this.fileDir = fileDir;
    this.ctx = ctx;
    try {
      utils = new GitUtils(fileDir);
    } catch (IOException err) {
      err.printStackTrace();
    }
    setVaribaleGit();
  }

  void setVaribaleGit() {
    listItem = new ListSheet();
    listItem.setSheetDialog(ctx);
    listItem.addItem("Git init", 0, utils.isGitinit(fileDir) || utils.isRep(fileDir));
    listItem.addItem("Git CreateBranch");
    listItem.addItem("Git MergeBranch");
    listItem.addItem("Git CommitChanges");
    listItem.addItem("Git Pull");
    listItem.addItem("Git Push");
    listItem.addItem("Git AddAll");
    listItem.addItem("Git Status");

    listItem.setOnItemClickLabe(
        new ListSheet.OnItemClick() {

          @Override
          public void OnItemClick(int pos) {
            switch (pos) {
              case 0:
                {
                  try {
                    utils.init(fileDir);
                  } catch (Exception err) {
                    err.printStackTrace();
                  }
                  break;
                }
              case 1:
                {
                  MaterialDialogHelper(
                      new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                          try {
                            utils.createBranch(bin.editor.getText().toString());
                          } catch (Exception err) {

                          }
                        }
                      });

                  break;
                }
              case 2:
                {
                  MaterialDialogHelper(
                      (c, d) -> {
                        try {
                          utils.mergeBranch(bin.editor.getText().toString());
                        } catch (Exception err) {

                        }
                      });

                  break;
                }
              case 3:
                {
                  MaterialDialogHelper(
                      (c, d) -> {
                        try {
                          utils.commitChanges(bin.editor.getText().toString());
                        } catch (Exception err) {

                        }
                      });
                  break;
                }

              case 4:
                {
                  MaterialDialogHelper(
                      (c, d) -> {
                        try {
                          utils.pull(bin.editor.getText().toString());
                        } catch (Exception err) {

                        }
                      });

                  break;
                }
              case 5:
                {
                  MaterialDialogHelper(
                      (c, d) -> {
                        try {
                          utils.push(bin.editor.getText().toString());
                        } catch (Exception err) {

                        }
                      });

                  break;
                }
              case 6:
                {
                  try {
                    utils.addAllFiles();
                  } catch (Exception err) {

                  }
                  break;
                }
              case 7:
                {
                  try {
                    MaterialDialogHelper(null);
                    bin.editor.setText(utils.getUncommittedChangesAsString());
                  } catch (Exception err) {

                  }

                  break;
                }
            }
          }
        });
  }

  void MaterialDialogHelper(DialogInterface.OnClickListener di) {
    //    makefolder
    bin = MakefolderBinding.inflate(LayoutInflater.from(ctx));
    dialog = new MaterialAlertDialogBuilder(ctx);
    dialog.setView(bin.getRoot());
    dialog.setTitle("Helper");
    dialog.setNegativeButton(android.R.string.ok, di);
    if (dialog != null) {
      dialog.show();
    }
  }
}
