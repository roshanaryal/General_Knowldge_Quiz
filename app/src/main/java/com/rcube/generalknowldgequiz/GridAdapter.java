package com.rcube.generalknowldgequiz;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<GridModal> mGridModalList;

    public GridAdapter(List<GridModal> gridModalList) {
        mGridModalList = gridModalList;
    }

    @Override
    public int getCount() {
        return mGridModalList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        }

        TextView textView=convertView.findViewById(R.id.grid_text);
        textView.setText(mGridModalList.get(position).getCategoryName());
        textView.setOnClickListener(v -> {
            Intent intent=new Intent(parent.getContext(),QuestionActivity.class);
            intent.putExtra("category",mGridModalList.get(position).getCategoryName());
            parent.getContext().startActivity(intent);
        });
        return convertView;
    }
}
