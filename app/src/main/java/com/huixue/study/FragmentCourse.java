package com.huixue.study;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
    private GridView gridViewIt,gridViewKill;
    private BaseAdapter courseAdapterIt =null;
    private BaseAdapter courseAdapterKill =null;
    private ArrayList<ItemCourse> iData = null;//存放IT互联网数据
    private ArrayList<ItemCourse> kData = null;//存放职场技能数据
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_course,container,false);
        courseContext =getActivity();
        //获取gridView视图
        /**
         * gridView—it
         * */
        gridViewIt = (GridView)view.findViewById(R.id.grid_course_it);
        iData = new ArrayList<ItemCourse>();
        //给IT互联网数据列表添加数据
        iData.add(new ItemCourse(R.drawable.border_gray,"编程语言"));
        iData.add(new ItemCourse(R.drawable.border_gray,"移动开发"));
        iData.add(new ItemCourse(R.drawable.border_gray,"测试开发"));
        iData.add(new ItemCourse(R.drawable.border_gray,"软件测试"));
        iData.add(new ItemCourse(R.drawable.border_gray,"Web开发"));
        iData.add(new ItemCourse(R.drawable.border_gray,"设计制作"));
        iData.add(new ItemCourse(R.drawable.border_gray,"网络营销"));
        iData.add(new ItemCourse(R.drawable.border_gray,"数据库"));
        iData.add(new ItemCourse(R.drawable.border_gray,"产品策划"));
        iData.add(new ItemCourse(R.drawable.border_gray,"产品运营"));
        iData.add(new ItemCourse(R.drawable.border_gray,"操作系统"));
        iData.add(new ItemCourse(R.drawable.border_gray,"网络技术"));
        iData.add(new ItemCourse(R.drawable.border_gray,"硬件开发"));
        iData.add(new ItemCourse(R.drawable.border_gray,"其它"));
        /**
         * gridView—kills
         * */
        gridViewKill =(GridView)view.findViewById(R.id.grid_course_kill);

        kData = new ArrayList<ItemCourse>();
        //给职场技能数据列表添加数据
        kData.add(new ItemCourse(R.drawable.border_gray,"办公技能"));
        kData.add(new ItemCourse(R.drawable.border_gray,"求职应聘"));
        kData.add(new ItemCourse(R.drawable.border_gray,"职业考试"));
        kData.add(new ItemCourse(R.drawable.border_gray,"营销推广"));
        kData.add(new ItemCourse(R.drawable.border_gray,"财税投资"));
        kData.add(new ItemCourse(R.drawable.border_gray,"人力资源"));
        kData.add(new ItemCourse(R.drawable.border_gray,"商务礼仪"));
        kData.add(new ItemCourse(R.drawable.border_gray,"谈判沟通"));
        kData.add(new ItemCourse(R.drawable.border_gray,"职业规划"));
        kData.add(new ItemCourse(R.drawable.border_gray,"其他"));
        //IT互联网适配器
        courseAdapterIt = new MyAdapter<ItemCourse>(iData,R.layout.item_course_content)
        {
            @Override
            public void bindView(ViewHolder holder, ItemCourse obj) {
                holder.setImageResource(R.id.item_img,obj.getItemId());
                holder.setText(R.id.item_text,obj.getItemName());
            }
        };
        //职场技能适配器
        courseAdapterKill = new MyAdapter<ItemCourse>(kData,R.layout.item_course_content)
        {
            @Override
            public void bindView(ViewHolder holder, ItemCourse obj) {
                holder.setImageResource(R.id.item_img,obj.getItemId());
                holder.setText(R.id.item_text,obj.getItemName());
            }
        };
        /**
         * 互联网设置适配和监听事件
         */
        gridViewIt.setAdapter(courseAdapterIt);
        gridViewIt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                //吐司输出
                Toast.makeText(courseContext,"你点击了互联网项的第"+(postion+1)+"项",Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 互联网设置适配和监听事件
         */
        gridViewKill.setAdapter(courseAdapterKill);
        gridViewKill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                //吐司输出
                Toast.makeText(courseContext,"你点击了职场技能的第"+(postion+1)+"项",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
