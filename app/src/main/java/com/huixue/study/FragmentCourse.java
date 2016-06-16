package com.huixue.study;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.huixue.study.adapter.ItemCourse;
import com.huixue.study.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/3.
 *全部课程的Fragment
 */
public class FragmentCourse extends Fragment{
    private int previousSelectedPosition = -1; //初始化当前被选择的Item为-1
    private Context courseContext;
    private GridView gridView;
    private BaseAdapter courseAdapter =null;
    private ArrayList<ItemCourse> iData = null;//存放数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_course,container,false);
        //获取gridView视图
        /**
         * gridView
         * */
        gridView = (GridView)view.findViewById(R.id.grid_view_course);
        courseContext =getActivity();
        iData = new ArrayList<ItemCourse>();
        //给数据列表添加数据
        iData.add(new ItemCourse(R.drawable.border_gray,"一四个"));
        iData.add(new ItemCourse(R.drawable.border_gray,"一直四个"));
        iData.add(new ItemCourse(R.drawable.border_gray,"一个咋的"));
        iData.add(new ItemCourse(R.drawable.border_gray,"二是四个"));
        iData.add(new ItemCourse(R.drawable.border_gray,"二直四个"));
        iData.add(new ItemCourse(R.drawable.border_gray,"三个咋的"));
        iData.add(new ItemCourse(R.drawable.border_gray,"大苏打四个"));
        iData.add(new ItemCourse(R.drawable.border_gray,"东城大道"));
        iData.add(new ItemCourse(R.drawable.border_gray,"哒哒哒的"));
        //适配器
        courseAdapter = new MyAdapter<ItemCourse>(iData,R.layout.item_course_content)
        {
            @Override
            public void bindView(ViewHolder holder, ItemCourse obj) {
                holder.setImageResource(R.id.item_img,obj.getItemId());
                holder.setText(R.id.item_text,obj.getItemName());
            }
        };
        gridView.setAdapter(courseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {


                //吐司输出
                Toast.makeText(courseContext,"你点击了"+(postion+1)+"项",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
