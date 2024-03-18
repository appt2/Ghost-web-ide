package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import coyamo.visualxml.lib.parser.AndroidXmlParser;
import coyamo.visualxml.lib.parser.ReadOnlyParser;
import coyamo.visualxml.lib.proxy.ProxyResources;
import coyamo.visualxml.lib.ui.OutlineView;
import coyamo.visualxml.lib.utils.MessageArray;
import coyamo.visualxml.lib.utils.Utils;
import coyamo.visualxml.ui.adapter.ErrorMessageAdapter;
import coyamo.visualxml.ui.menu.CheckBoxActionProvider;
import coyamo.visualxml.ui.treeview.ViewBean;
import coyamo.visualxml.ui.treeview.ViewNodeBinder;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class XmlViewActivity extends AppCompatActivity {
    protected AppBarLayout appbarLayout;
    private OutlineView outlineView;
    private boolean isEditMode = true;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlview);
        outlineView = findViewById(R.id.outline_view);
        toolbar = findViewById(R.id.toolbarz);
        RecyclerView rv = findViewById(R.id.err_list);
        rv.setAdapter(new ErrorMessageAdapter());
        rv.setLayoutManager(new LinearLayoutManager(this));
        //  ProxyResources.getInstance().getViewIdMap().clear();
        //MessageArray.getInstanse().clear();
        setSupportActionBar(toolbar);

        var pro = ProxyResources.getInstance();
        if (pro != null) {
            pro.getViewIdMap().clear();
        }
        var msg = MessageArray.getInstanse();
        if (msg != null) {
            msg.clear();
        }

        final List<TreeNode> nodes = new ArrayList<>();

        final Stack<TreeNode> treeNodeStack = new Stack<>();
        try {

            AndroidXmlParser.with(outlineView)
                    .setOnParseListener(
                            new AndroidXmlParser.OnParseListener() {
                                @Override
                                public void onAddChildView(View v, ReadOnlyParser parser) {
                                    ViewBean bean = new ViewBean(v, parser);
                                    bean.setViewGroup(v instanceof ViewGroup);

                                    TreeNode<ViewBean> child = new TreeNode<>(bean);
                                    // 如果没有父Group
                                    // 理论上这种情况应该不存在的？
                                    // 这里暂不考虑
                                    if (treeNodeStack.size() == 0) {
                                        // 添加到根
                                        nodes.add(child);
                                    } else {
                                        // 添加到父亲
                                        TreeNode node = treeNodeStack.peek();
                                        node.addChild(child);
                                    }
                                }

                                @Override
                                public void onJoin(ViewGroup viewGroup, ReadOnlyParser parser) {
                                    ViewBean bean = new ViewBean(viewGroup, parser);
                                    bean.setViewGroup(true);

                                    // 生成group
                                    TreeNode<ViewBean> child = new TreeNode<>(bean);
                                    if (treeNodeStack.size() == 0) {
                                        treeNodeStack.push(child);
                                    } else {
                                        TreeNode node = treeNodeStack.peek();
                                        node.addChild(child);
                                        treeNodeStack.push(child);
                                    }
                                }

                                @Override
                                public void onRevert(ViewGroup viewGroup, ReadOnlyParser parser) {
                                    TreeNode node = treeNodeStack.pop();
                                    if (treeNodeStack.size() == 0) {

                                        node.expand();
                                        nodes.add(node);
                                    }
                                }

                                @Override
                                public void onFinish() {
                                }

                                @Override
                                public void onStart() {
                                }
                            })
                    .parse(getIntent().getStringExtra("xml"));

            loadViewTree(nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }


        outlineView.setHoldOutline(false);
        outlineView.setOutlineClickListener(
                new OutlineView.OnOutlineClickListener() {
                    @Override
                    public void onDown(final View v, int displayType) {
                        if (!isEditMode) {
                        }
                    }

                    @Override
                    public void onCancel(View v, int displayType) {
                    }

                    @Override
                    public void onClick(View v, int displayType) {
                        if (isEditMode) {
                            ViewBean bean = findBeanByView(nodes, v);
                            StringBuilder sb = new StringBuilder();
                            if (bean != null) {

                                for (ViewBean.ViewInfo info : bean.getInfoList()) {
                                    sb.append(info.getAttributeName())
                                            .append("=")
                                            .append(info.getAttributeValue())
                                            .append("\n");
                                }
                            }
                            new MaterialAlertDialogBuilder(XmlViewActivity.this).setMessage(sb).show();
                        }
                    }
                });
    }

    private void loadViewTree(List<TreeNode> nodes) {
        RecyclerView rv = findViewById(R.id.view_tree);
        rv.setLayoutManager(new LinearLayoutManager(this));

        TreeViewAdapter adapter =
                new TreeViewAdapter(nodes, Collections.singletonList(new ViewNodeBinder()));
        adapter.setPadding((int) Utils.dp2px(this, 16));
        adapter.setOnTreeNodeListener(
                new TreeViewAdapter.OnTreeNodeListener() {
                    @Override
                    public boolean onLongClick(TreeNode node, RecyclerView.ViewHolder holder) {
                        ViewBean viewBean = (ViewBean) node.getContent();

                        return true;
                    }

                    @Override
                    public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                        ViewBean type = (ViewBean) node.getContent();
                        if (type.isViewGroup()) {
                            onToggle(!node.isExpand(), holder);
                        }
                        return false;
                    }

                    @Override
                    public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                        ViewNodeBinder.ViewHolder dirViewHolder = (ViewNodeBinder.ViewHolder) holder;
                        final ImageView ivArrow = dirViewHolder.arrow;
                        int rotateDegree = isExpand ? 90 : -90;
                        ivArrow.animate().rotationBy(rotateDegree).start();
                    }
                });
        rv.setAdapter(adapter);
    }

    private ViewBean findBeanByView(List<TreeNode> nodes, View v) {
        for (TreeNode node : nodes) {
            ViewBean bean = (ViewBean) node.getContent();
            if (bean.getView() == v) {
                return bean;
            } else {
                bean = findBeanByView(node.getChildList(), v);
                if (bean != null) return bean;
            }
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                break;
            case 1:

                break;
            case 2:
                outlineView.setDisplayType(OutlineView.DISPLAY_VIEW);
                break;
            case 3:
                outlineView.setDisplayType(OutlineView.DISPLAY_DESIGN);
                break;
            case 4:
                outlineView.setDisplayType(OutlineView.DISPLAY_BLUEPRINT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuview, menu);
        CheckBoxActionProvider p =
                (CheckBoxActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.toggle_edit));
        p.setOnCheckedChangeListener(
                new CheckBoxActionProvider.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(View buttonView, boolean isChecked) {
                        isEditMode = !isChecked;
                        if (isEditMode) {
                            outlineView.removeSelect();
                        }
                        outlineView.setHoldOutline(isChecked);
                    }
                });
        return true;
    }
}
