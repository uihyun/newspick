package com.uihyun.newyorktimes.list;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.uihyun.newyorktimes.AppController;
import com.uihyun.newyorktimes.R;
import com.uihyun.newyorktimes.logger.Logger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class ListViewAdapter extends BaseAdapter {
    private final int TYPE_LANDSCAPE = 0;
    private final int TYPE_PORTRAIT = 1;
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ListViewItem item = listViewItemList.get(position);

        String title = item.getTitle();
        Logger.debug(getClass().getSimpleName(), "get list item view = " + title);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (item.getType()) {
            case TYPE_LANDSCAPE:
                convertView = inflater.inflate(R.layout.listview_item_landscape, parent, false);
                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.text_layout);

                Random random = new Random();
                int color = Color.argb(255, random.nextInt(200), random.nextInt(200), random.nextInt(200));
                linearLayout.setBackgroundColor(color);
                break;
            case TYPE_PORTRAIT:
                convertView = inflater.inflate(R.layout.listview_item_portrait, parent, false);
                break;
            default:
                convertView = inflater.inflate(R.layout.listview_item_landscape, parent, false);
                break;
        }

        NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.list_image);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.list_name);

        if (item.getImageUrl() != null)
            imageView.setImageUrl(item.getImageUrl(), AppController.getImageLoader());
        else
            imageView.setVisibility(View.GONE);

        Paint paint = titleTextView.getPaint();
        StringBuilder sb = new StringBuilder();
        int line = 0;
        while (true) {
            int index = paint.breakText(title, true, 285, null);
            sb.append(title.substring(0, index));
            title = title.substring(index);
            if (index <= 0) {
                title = item.getTitle();
                break;
            }
            if (line > 2) {
                title = sb.toString().substring(0, sb.toString().length() - 3) + "...";
                break;
            }

            line++;
        }
        titleTextView.setText(title);

//        float widthTextView = titleTextView.getResources().getDisplayMetrics().widthPixels;
//        for (int i=0; i<title.length(); i++) {
//            titleTextView.setText(title.substring(0, i+i));
//            if (titleTextView.getPaint().measureText(title.substring(0, i+i), 0, title.substring(0, i+i).length()) > widthTextView) {
//                titleTextView.setText(title.substring(0, i+i) + "...");
//                break;
//            }
//        }

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

    public void addItem(String imageUrl, int height, int width, String title) {
        Logger.debug(getClass().getSimpleName(), "add list item = " + title);

        ListViewItem item = new ListViewItem();

        if (height <= width) {
            item.setType(TYPE_LANDSCAPE);
        } else {
            item.setType(TYPE_PORTRAIT);
        }

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
