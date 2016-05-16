package com.example.xiyan.testapplication.Image;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xiyan.testapplication.R;
import com.supconit.azpt.image.util.ImageLoader;
import com.supconit.azpt.ioc.annotation.AZActivity;
import com.supconit.azpt.ioc.annotation.AZAfter;
import com.supconit.azpt.ioc.annotation.AZView;

@AZActivity(R.layout.activity_grid_image)
public class GridImageActivity extends Activity {

    @AZView
    private GridView gridView;

    private ImageLoader imageLoader;

    public static String[] imageNetUrl = new String[]{"http://preview.quanjing.com/fod_liv001/fod-11038956.jpg", "http://preview.quanjing.com/fod_liv001/fod-11071593.jpg","http://preview.quanjing.com/fod_liv001/fod-11069979.jpg","http://preview.quanjing.com/west001/asf01440.jpg","http://preview.quanjing.com/west002/asf02591.jpg","http://preview.quanjing.com/west002/asf02616.jpg","http://preview.quanjing.com/west002/asf02578.jpg","http://preview.quanjing.com/west002/asf02605.jpg"};

    @AZAfter
    protected void init() {

        imageLoader = ImageLoader.getInstance(this,3, ImageLoader.Type.FIFO);
//        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter());
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
                convertView = GridImageActivity.this.getLayoutInflater().inflate(R.layout.gridimage, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.imageItem);
            img.setImageResource(R.mipmap.picture_no);//刷新列表时带出的图
            imageLoader.loadNetImage(imageNetUrl[position], img);

            return convertView;
        }
    }


}
