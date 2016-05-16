package com.example.xiyan.testapplication.fragmant;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.image.util.ImageLoader;
import com.supconit.azpt.ioc.core.InjectCore;
import com.supconit.azpt.ui.fragment.AZTabFragment;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshBase;
import com.supconit.azpt.ui.pullrefresh.widget.PullToRefreshListView;

import java.util.LinkedList;


/**
 * Created by xiyan on 2016/4/19.
 */
public class Fragment2 extends AZTabFragment {

    private ListView listView;
    private ImageLoader imageLoader;
    private PullToRefreshListView pullToRefreshListView;
    private LinkedList<String> listData = new LinkedList<>();
    private ImageAdapter adapter;

    public static String[] imageNetUrl = new String[]{"http://s11.sinaimg.cn/thumb180/e215ca26gx6Dvd4KaMy5a", "http://img4.baixing.net/33dca1d8cf43cf24b06aff2ecdbd62a8.jpg_180x180", "http://img2.imgtn.bdimg.com/it/u=3179881374,3105935508&fm=15&gp=0.jpg", "http://img6.baixing.net/e563baea0119fde1e0d8b8ec43e0467a.jpg_180x180", "http://fdfs.xmcdn.com/group7/M0B/4B/CA/wKgDWlWvCDzj1nXuAADoSBScAu0998_web_large.jpg", "http://img6.baixing.net/202160d4e03d20620d03fc6f964432dc.jpg_180x180", "http://img2.imgtn.bdimg.com/it/u=3797855178,3480629334&fm=11&gp=0.jpg", "http://ww2.sinaimg.cn/thumb180/94b23293tw1elooyszmnqj2050050t8q.jpg", "http://awb.img.xmtbang.com/img/uploadnew/201510/23/e84eaaee6719491cb9ab159ae9630c29.jpg", "http://img2.imgtn.bdimg.com/it/u=738878696,3319213926&fm=15&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2619159266,858976268&fm=21&gp=0.jpg", "http://img3.imgtn.bdimg.com/it/u=2517272913,886013688&fm=21&gp=0.jpg", "http://img4.imgtn.bdimg.com/it/u=2510939683,4234033765&fm=21&gp=0.jpg", "http://img008.hc360.cn/g8/M09/E5/40/wKhQtlQGrbeEIsNyAAAAAL4jbHI166.jpg..180x180.jpg", "http://p3.music.126.net/prtL9DkmxPubM7wheASF-w==/7962663210377677.jpg?param=180y180", "http://img4.imgtn.bdimg.com/it/u=470319400,4181718460&fm=15&gp=0.jpg", "http://portrait2.sinaimg.cn/3270074041/blog/180", "http://ww2.sinaimg.cn/thumb180/9380d3ebjw1f0oods9r7aj205k05k0st.jpg", "http://img0.imgtn.bdimg.com/it/u=2263238649,3508981465&fm=21&gp=0.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg", "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg"};
    private  String newData = "http://s11.sinaimg.cn/thumb180/e215ca26gx6Dvd4KaMy5a";
    private String newData1 = "http://img4.baixing.net/33dca1d8cf43cf24b06aff2ecdbd62a8.jpg_180x180";



    class LoadImage extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            return newData;
        }

        @Override
        protected void onPostExecute(String s) {
            listData.addFirst(s);
            listData.addFirst(newData1);
            adapter.notifyDataSetChanged();
            pullToRefreshListView.onPullDownRefreshComplete();
            super.onPostExecute(s);
        }
    }
    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageNetUrl.length;
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gridimage, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.imageItem);
            img.setImageResource(R.mipmap.picture_no); //默认图片
            imageLoader.loadNetImage(listData.get(position), img);

            return convertView;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = createRootView(inflater, R.layout.refresh_list);
        InjectCore.inject(this, root);

        pullToRefreshListView = (PullToRefreshListView) root.findViewById(R.id.refreshListView);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new LoadImage().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }
        });

        for (int i=0;i<imageNetUrl.length;i++){
            listData.add(imageNetUrl[i]);
        }

        imageLoader = ImageLoader.getInstance(getActivity(), 3, ImageLoader.Type.FIFO);
        imageLoader.setPicnoId(R.mipmap.az_broken_image);
        adapter = new ImageAdapter();
        listView.setAdapter(adapter);

//        setTitleVisiable(View.GONE);
        setTitle("最新图片");
//        TextView tv = (TextView) root.findViewById(R.id.home_tv);
//        tv.setText("Fragment Time");


        return root;
    }

    @Override
    public int initCheckedResId() {
        return R.mipmap.recents_checked;
    }

    @Override
    public int initUncheckedResId() {
        return R.mipmap.recents_unchecked;
    }

    @Override
    public String initTabLable() {
        return "时间";
    }

    @Override
    public int initCheckedTextColor() {
        return Color.rgb(0, 191, 142);
    }

    @Override
    public int initUnCheckedTextColor() {
        return Color.GRAY;
    }
}
