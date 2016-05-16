package com.example.xiyan.testapplication.Image;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.image.util.ImageLoader;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.core.InjectCore;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshBase;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshListView;

import java.util.LinkedList;

@AZActivity(R.layout.activity_list_image)
public class ListImageActivity  extends Activity {

    private ListView listView;
    private ImageLoader imageLoader;
    public static String[] imageNetUrl = new String[]{"http://s11.sinaimg.cn/thumb180/e215ca26gx6Dvd4KaMy5a", "http://img4.baixing.net/33dca1d8cf43cf24b06aff2ecdbd62a8.jpg_180x180", "http://img2.imgtn.bdimg.com/it/u=3179881374,3105935508&fm=15&gp=0.jpg", "http://img6.baixing.net/e563baea0119fde1e0d8b8ec43e0467a.jpg_180x180", "http://fdfs.xmcdn.com/group7/M0B/4B/CA/wKgDWlWvCDzj1nXuAADoSBScAu0998_web_large.jpg", "http://img6.baixing.net/202160d4e03d20620d03fc6f964432dc.jpg_180x180", "http://img2.imgtn.bdimg.com/it/u=3797855178,3480629334&fm=11&gp=0.jpg", "http://ww2.sinaimg.cn/thumb180/94b23293tw1elooyszmnqj2050050t8q.jpg", "http://awb.img.xmtbang.com/img/uploadnew/201510/23/e84eaaee6719491cb9ab159ae9630c29.jpg", "http://img2.imgtn.bdimg.com/it/u=738878696,3319213926&fm=15&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2619159266,858976268&fm=21&gp=0.jpg", "http://img3.imgtn.bdimg.com/it/u=2517272913,886013688&fm=21&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2510939683,4234033765&fm=21&gp=0.jpg", "http://img008.hc360.cn/g8/M09/E5/40/wKhQtlQGrbeEIsNyAAAAAL4jbHI166.jpg..180x180.jpg", "http://p3.music.126.net/prtL9DkmxPubM7wheASF-w==/7962663210377677.jpg?param=180y180", "http://img4.imgtn.bdimg.com/it/u=470319400,4181718460&fm=15&gp=0.jpg", "http://portrait2.sinaimg.cn/3270074041/blog/180", "http://ww2.sinaimg.cn/thumb180/9380d3ebjw1f0oods9r7aj205k05k0st.jpg", "http://img0.imgtn.bdimg.com/it/u=2263238649,3508981465&fm=21&gp=0.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg"};


    private PullToRefreshListView pullToRefreshListView;
    private LinkedList<String> listData = new LinkedList<>();
    private ImageAdapter adapter;

    private String newData = "http://s11.sinaimg.cn/thumb180/e215ca26gx6Dvd4KaMy5a";
    private String newData1 = "http://img4.baixing.net/33dca1d8cf43cf24b06aff2ecdbd62a8.jpg_180x180";

    class LoadImage extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return newData;
        }

        @Override
        protected void onPostExecute(String s) {
//            listData.addFirst(s);更新数据并刷新
            listData.addFirst(newData1);
            adapter.notifyDataSetChanged();
            pullToRefreshListView.onPullDownRefreshComplete();
            super.onPostExecute(s);
        }
    }

    class LoadImageLast extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return newData;
        }

        @Override
        protected void onPostExecute(String s) {
            listData.add(s);
            adapter.notifyDataSetChanged();
//            listView.setAdapter(adapter);
            pullToRefreshListView.onPullUpRefreshComplete();
            super.onPostExecute(s);
        }
    }

    @AZAfter
    protected void init() {

        imageLoader = ImageLoader.getInstance(this, 3, ImageLoader.Type.FIFO);
        imageLoader.setPicnoId(R.mipmap.az_broken_image);

        for (int i=0;i<imageNetUrl.length;i++){
            listData.add(imageNetUrl[i]);
        }

        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.refreshListView);
        pullToRefreshListView.setPullLoadEnabled(true);//是否向上刷新
        listView = pullToRefreshListView.getRefreshableView();
        adapter = new ImageAdapter();
        listView.setAdapter(adapter);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                new LoadImage().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//                Toast.makeText(ListImageActivity.this, "come in", Toast.LENGTH_LONG).show();
                new LoadImageLast().execute();
            }
        });

    }

    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData.size();//listView的显示长度
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = ListImageActivity.this.getLayoutInflater().inflate(R.layout.gridimage, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.imageItem);
            img.setImageResource(R.mipmap.picture_no); //刷新列表时带出的图
            imageLoader.loadNetImage(listData.get(position), img);
            return convertView;
        }
    }

}
