package Ninja.coder.Ghostemane.code.FileHelper;

import Ninja.coder.Ghostemane.code.BaseCompat;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.adapter.ProjectManagerAdapter;
import Ninja.coder.Ghostemane.code.model.ProjectModel;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import java.util.List;

public class FactoryModelProject implements ProjectManagerAdapter.OnProjectClick {
  private BaseCompat baseCompat;
  private RecyclerView rv;
  protected List<ProjectModel> itemModel = new ArrayList<>();
  protected ProjectManagerAdapter adaptor;

  public FactoryModelProject(BaseCompat baseCompat) {
    this.baseCompat = baseCompat;
    itemModel.add(new ProjectModel("html", R.drawable.htmlfile, true));
    itemModel.add(new ProjectModel("node js", R.drawable.js01, false));
    itemModel.add(new ProjectModel("cpp", R.drawable.cppfile, false));
    itemModel.add(new ProjectModel("java", R.drawable.javanull, false));
    bind();
  }

  protected void bind() {
    
    rv = new RecyclerView(baseCompat);
    ViewGroup.LayoutParams param =
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    rv.setPadding(8, 8, 8, 8);
    rv.setLayoutParams(param);

    adaptor = new ProjectManagerAdapter(itemModel, this);
    rv.setAdapter(adaptor);
    rv.setLayoutManager(new GridLayoutManager(baseCompat, 2));
    new MaterialAlertDialogBuilder(baseCompat)
        .setTitle("Project manager")
        .setNegativeButton(android.R.string.cancel, null)
        .setView(rv)
        .show();
  }


  @Override
  public void onClick(View view, int pos) {
    Toast.makeText(baseCompat, String.valueOf(pos), 2).show();
  }
}
