package com.huixue.study;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.huixue.study.adapter.GridAdapter;
import com.huixue.study.adapter.ItemCourseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/3.
 *全部课程的Fragment
 */
public class FragmentCourse extends Fragment{
    private Context courseContext;
    private GridView gridContenxt;
    private BaseAdapter courseAdapter =null;
    private ArrayList<ItemCourseAdapter> iData = null;//存放数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_course,container,false);
        iData = new ArrayList<ItemCourseAdapter>();
        //给数据列表添加数据
        iData.add(new ItemCourseAdapter(R.drawable.grid_item_lrb,"还是四个"));
        iData.add(new ItemCourseAdapter(R.drawable.grid_item_lrb,"一直四个"));
        iData.add(new ItemCourseAdapter(R.drawable.grid_item_lrb,"三个咋的"));
        //适配器
        courseAdapter = new GridAdapter(this,);
        return view;
    }
}
