package com.huixue.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/3.
 *
 * 首页Fragment实现类，加载Home的Fragment的布局
 */



public class FragmentHome extends Fragment {
     @Override
     public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_home,container,false);
         return  view;
    }

}
