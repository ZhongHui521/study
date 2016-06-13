package com.huixue.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huixue.study.CycleView.CycleView;

/**
 * Created by Administrator on 2016/6/3.
 *
 * 首页Fragment实现类，加载Home的Fragment的布局
 */



public class FragmentHome extends Fragment {
    private CycleView cycleView;
     @Override
     public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){


         View view = inflater.inflate(R.layout.fragment_home,container,false);
         /**
          * 在首页时同时加载轮播图
          */
         cycleView = (CycleView) view.findViewById(R.id.home_cycle_view);
         cycleView.setImagesUrl(new String[]{
                 "http://img04.muzhiwan.com/2015/06/16/upload_557fd293326f5.jpg",
                 "http://img03.muzhiwan.com/2015/06/05/upload_557165f4850cf.png",
                 "http://img02.muzhiwan.com/2015/06/11/upload_557903dc0f165.jpg",
                 "http://img04.muzhiwan.com/2015/06/05/upload_5571659957d90.png",
                 "http://img03.muzhiwan.com/2015/06/16/upload_557fd2a8da7a3.jpg"});
//        int[] imagesRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c,
//                R.mipmap.d, R.mipmap.e};
//        kanner.setImagesRes(imagesRes);
         return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cycleView.removeCallbacksAndMessages();
    }
}
