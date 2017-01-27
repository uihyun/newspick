package com.uihyun.newyorktimes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.uihyun.newyorktimes.R;
import com.uihyun.newyorktimes.data.Photo;
import com.uihyun.newyorktimes.data.Story;
import com.uihyun.newyorktimes.data.StoryData;
import com.uihyun.newyorktimes.dialog.CustomProgressDialog;
import com.uihyun.newyorktimes.list.ListViewAdapter;
import com.uihyun.newyorktimes.logger.Logger;
import com.uihyun.newyorktimes.manager.StoryManager;
import com.uihyun.newyorktimes.model.StaggeredGridView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class StoryListActivity extends Activity {

    private ListViewAdapter listAdapter;
    private List<Story> storyList;
    private CustomProgressDialog progressDialog;
    private StaggeredGridView gridView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        Logger.info(getClass().getSimpleName(), "open activity");

        listAdapter = new ListViewAdapter();

        gridView = (StaggeredGridView) findViewById(R.id.gridview);
        gridView.setAdapter(listAdapter);

        int margin = getResources().getDimensionPixelSize(R.dimen.margin);
        gridView.setItemMargin(margin);
        gridView.setPadding(margin, 0, margin, 0);
        gridView.setOnItemClickListener(new StaggeredGridView.OnItemClickListener() {
            @Override
            public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), StoryActivity.class);
                intent.putExtra("story_link", storyList.get(position).getLink());
                startActivity(intent);
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_purple
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listAdapter.removeListViewItems();
                getStories();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setEnabled(false);

//        progressDialog = CustomProgressDialog.show(this, "", false, null);
        getStories();
//        progressDialog.dismiss();
    }

    private void getStories() {
        Logger.debug(getClass().getSimpleName(), "refresh story");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StoryManager.getInstance().readStories(new Response.Listener<StoryData>() {

                    @Override
                    public void onResponse(StoryData storyData) {
                        if (storyData.getStatus().equals("OK")) {
                            storyList = storyData.getStoryList();

                            if (storyList != null) {
                                for (int i = 0; i < storyList.size(); i++) {
                                    try {
                                        if (storyList.get(i).getPhotoList().size() > 0) {
                                            Photo photo = storyList.get(i).getPhotoList().get(2);
                                            URL imageUrl = new URL(photo.getImageUrl());
                                            listAdapter.addItem(imageUrl.toString(), photo.getHeight(), photo.getWidth(), storyList.get(i).getTitle());
                                        } else {
                                            listAdapter.addItem(null, 0, 0, storyList.get(i).getTitle());
                                        }
                                    } catch (MalformedURLException e) {
                                        Logger.error(getClass().getSimpleName(), e.getMessage());
                                    }
                                }
                            }
                            listAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.no_result), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, null);
            }
        });
    }
}
