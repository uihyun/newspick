package com.uihyun.newyorktimes.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.uihyun.newyorktimes.AppController;
import com.uihyun.newyorktimes.R;

import java.util.ArrayList;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ListViewItem item = listViewItemList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (item.getType()) {
                case 0:
                    convertView = inflater.inflate(R.layout.listview_item_landscape, parent, false);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.listview_item_portrait, parent, false);
                    break;
                default:
                    convertView = inflater.inflate(R.layout.listview_item_landscape, parent, false);
                    break;

            }
        }

        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.list_image);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.list_name);

        imageView.setImageUrl(item.getImageUrl(), AppController.getImageLoader());
        imageView.setImageBitmap(item.getImage());
        titleTextView.setText(item.getTitle());

        return convertView;
    }


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(String imageUrl, String title) {
        ListViewItem item = new ListViewItem();

        item.setType(0);
        item.setImageUrl(imageUrl);
        item.setTitle(title);

        listViewItemList.add(item);
    }

    public void removeListViewItems() {
        listViewItemList.clear();
    }

    public void removeListViewItem(int index) {
        listViewItemList.remove(index);
    }
}
