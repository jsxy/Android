package com.example.xiyan.testapplication.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.supconit.azpt.ui.search.widget.SearchView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiyan on 16-5-10.
 */
public class SearchViewActivity extends Activity implements SearchView.SearchViewListener {
    private SearchView searchView;
    private ArrayAdapter<String> autoCompleteAdapter;
    private List autoCompleteData = new ArrayList();
    private List<String> hsData = new ArrayList();
    private List<String> resultData;
    private ListView lvResults;
    private ArrayAdapter resultAdapter;

    public SearchViewActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(1);
        this.setContentView(com.supconit.azpt.ui.R.layout.activity_search_view);
        this.initViews();
        this.initData();
    }

    private void initViews() {
        this.searchView = (SearchView)this.findViewById(com.supconit.azpt.ui.R.id.searchView);
        this.searchView.setSearchViewListener(this);
        this.autoCompleteAdapter = new ArrayAdapter(this, 17367043, this.autoCompleteData);
        this.searchView.setAutoCompleteAdapter(this.autoCompleteAdapter);
        this.lvResults = (ListView)this.findViewById(com.supconit.azpt.ui.R.id.lv_search_results);
        this.lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(SearchViewActivity.this, position + "", 0).show();
            }
        });
    }

    private void initData() {
        this.getAutoCompleteData((List)null);
        this.getResultData((String)null);
    }

    private void getAutoCompleteData(List<String> list) {
        this.autoCompleteData.clear();
        if(list != null) {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                String str = (String)var2.next();
                this.autoCompleteData.add(str);
            }
        }

        this.autoCompleteAdapter.notifyDataSetChanged();
    }

    private void getResultData(String text) {
        if(this.resultData == null) {
            this.resultData = new ArrayList();
        } else {
            this.resultData.clear();

            for(int i = 0; i < 10; ++i) {
                this.resultData.add("www" + i);
            }
        }

        if(this.resultAdapter == null) {
            this.resultAdapter = new ArrayAdapter(this, 17367043, this.resultData);
        } else {
            this.resultAdapter.notifyDataSetChanged();
        }

    }

    public void onRefreshAutoComplete(List list) {
        this.getAutoCompleteData(list);
    }

    public void onSearch(String text) {
        this.getResultData(text);
        this.lvResults.setVisibility(0);
        if(this.lvResults.getAdapter() == null) {
            this.lvResults.setAdapter(this.resultAdapter);
        } else {
            this.resultAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "完成搜索",Toast.LENGTH_SHORT).show();
    }

    public void onClearResult() {
        this.resultData.clear();
        this.resultAdapter.notifyDataSetChanged();
    }
}
