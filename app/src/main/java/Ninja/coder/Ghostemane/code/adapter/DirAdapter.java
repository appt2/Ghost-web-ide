package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.layoutmanager.ColorList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class DirAdapter extends RecyclerView.Adapter<DirAdapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public DirAdapter(ArrayList<HashMap<String, Object>> _arr) {
        _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View _v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.copyinsterfile, parent, false);
        RecyclerView.LayoutParams _lp =
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        _v.setLayoutParams(_lp);
        return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
        View _view = _holder.itemView;

        final LinearLayout linear2 = _view.findViewById(R.id.linear2);
        final TextView textview2 = _view.findViewById(R.id.textview2);
        final ImageView imageview1 = _view.findViewById(R.id.imageview1);

        RecyclerView.LayoutParams _lp =
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        _view.setLayoutParams(_lp);
        textview2.setText(_data.get((int) _position).get("mo").toString());
        if (_position == (_data.size() - 1)) {
            imageview1.setVisibility(View.GONE);
        }
        ColorList.setColorAsRandom(imageview1, textview2);
        if (_position == 0) {
            textview2.setVisibility(View.GONE);
            imageview1.setVisibility(View.GONE);
        }
        if (_position == 1) {
            textview2.setVisibility(View.GONE);
            imageview1.setVisibility(View.VISIBLE);
        }
        if (_position == 2) {
            textview2.setText("Src");
        }
        if (_position == 3) {
        }
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }
}
