package com.huixue.study;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huixue.study.CycleView.CycleView;
import com.huixue.study.adapter.MyFragmentPageAdapter;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity{
    //viewpager 控件
    private ViewPager main_viewPager;
    //radioGroup 控件
    private RadioGroup main_bottom_radioGroup;
    //4个RadioButton控件
    private RadioButton radio_home,radio_course,radio_study,radio_account;
    //Fragment动态数组
    private ArrayList<Fragment> main_FragmentsList;
    //轮播图自定义组件
    private CycleView cycleView;
    private ImageView topLogoImg;//顶部Logo
    private ImageView topSearchImg;//顶部搜索
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        /*设置不显示标题栏*/
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();

//界面初始函数,功能是获取各控件ID
        InitView();
        //viewPager初始化函数
    }//onCreate over
    public  void InitView(){
        main_bottom_radioGroup = (RadioGroup)findViewById(R.id.main_bottom_radio_group);
        radio_home = (RadioButton)findViewById(R.id.main_home_radioBtn);
        radio_course = (RadioButton)findViewById(R.id.main_allcourse_radioBtn);
        radio_study = (RadioButton)findViewById(R.id.main_mystudy_radioBtn);
        radio_account=(RadioButton)findViewById(R.id.main_account_radioBtn);

        topLogoImg = (ImageView) findViewById(R.id.top_logo_img);
        topSearchImg = (ImageView) findViewById(R.id.top_search_img);
        //radiogroup监听,匿名内部类
        main_bottom_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                              @Override
                              public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                                  //判断选中是否是学习中心，控制显示和隐藏
                                  if (checkId == R.id.main_mystudy_radioBtn) {
                                      topLogoImg.setVisibility(View.INVISIBLE);
                                      topSearchImg.setVisibility(View.INVISIBLE);
                                  } else if (checkId == R.id.main_account_radioBtn) {
                                      topSearchImg.setVisibility(View.INVISIBLE);
                                  } else {
                                      topLogoImg.setVisibility(View.VISIBLE);
                                      topSearchImg.setVisibility(View.VISIBLE);
                                  }

                                //监听那个radioButton的被选中，根据radio的ID改变页面
                                  switch (checkId){
                                      case R.id.main_home_radioBtn:
                                          /*
                                          * 使用setCurrentitem第二个参数控制切换动画（暂时不明白）
                                          * true：打开
                                          * false:关闭
                                          * */
                                        main_viewPager.setCurrentItem(0,false);
                                          break;
                                      case  R.id.main_allcourse_radioBtn:
                                            main_viewPager.setCurrentItem(1,false);
                                          break;
                                      case R.id.main_mystudy_radioBtn:
                                            main_viewPager.setCurrentItem(2,false);
                                          break;
                                      case R.id.main_account_radioBtn:
                                            main_viewPager.setCurrentItem(3,false);
                                            break;

                                  }


                              }
                          }
        );
        /*Viewpager*/
        main_viewPager = (ViewPager)findViewById(R.id.main_view_pager);
        //bottom下的四个fragmnet
        FragmentHome  fragmentHome =new FragmentHome();
        FragmentCourse fragmentCourse = new FragmentCourse();
        FragmentStudy  fragmentStudy  =new FragmentStudy();
        FragmentAccount fragmentAccount = new FragmentAccount();
        main_FragmentsList = new ArrayList<Fragment>();
        main_FragmentsList.add(fragmentHome);
        main_FragmentsList.add(fragmentCourse);
        main_FragmentsList.add(fragmentStudy);
        main_FragmentsList.add(fragmentAccount);
        //设置适配器,将添加完所有fragment的list传递给适配器
        main_viewPager.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(),main_FragmentsList));
       //设置显示第一个Fragment
        main_viewPager.setCurrentItem(0);
        //设置页面监听,setOnPageChangeListener过时，使用了addOnPageChangeListener代替
        main_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){


                       @Override
                       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                       }
                        //修改这个,根据position获取当前页面，选择那个radiobutton被选中
                       @Override
                       public void onPageSelected(int position) {
                           //判断滑动到的是否是学习中心或者账号，控制显示和隐藏
                           if (position == 2) {
                               topLogoImg.setVisibility(View.INVISIBLE);
                               topSearchImg.setVisibility(View.INVISIBLE);
                           } else if (position == 3) {
                               topLogoImg.setVisibility(View.VISIBLE);
                               topSearchImg.setVisibility(View.INVISIBLE);
                           } else {
                               topLogoImg.setVisibility(View.VISIBLE);
                               topSearchImg.setVisibility(View.VISIBLE);
                           }
                               switch (position){
                                   case 0:
                                       main_bottom_radioGroup.check(R.id.main_home_radioBtn);
                                       break;
                                   case 1:
                                       main_bottom_radioGroup.check(R.id.main_allcourse_radioBtn);

                                       break;
                                   case 2:
                                       main_bottom_radioGroup.check(R.id.main_mystudy_radioBtn);
                                       break;
                                   case 3:
                                       main_bottom_radioGroup.check(R.id.main_account_radioBtn);
                                       break;

                               }
//                           //如果当前选择的不是首页，销毁轮播图
//                           if (position!=0){
//                               cycleView.removeCallbacksAndMessages();
//                           }
                       }

                       @Override
                       public void onPageScrollStateChanged(int state) {

                       }
                   }
        );

    }//initView over

}
