package com.huixue.study.CycleView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.huixue.study.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

public class CycleView extends FrameLayout {
    private int count;
    private ImageLoader mImageLoader;
    private List<ImageView> imageViews;
    private Context context;
    private ViewPager banner_vp;//轮播图的viewpager
    private boolean isAutoPlay;
    private int currentItem;
    private int delayTime;
    private LinearLayout layoutLinearDot;//指示点的线布局
    private List<ImageView> iv_dots; //存放圆点
    private Handler handler = new Handler();
    private DisplayImageOptions options;

    public CycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        initImageLoader(context);
        initData();
    }

    public CycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CycleView(Context context) {
        this(context, null);
    }

    private void initData() {
        imageViews = new ArrayList<ImageView>();
        iv_dots = new ArrayList<ImageView>();
        delayTime = 2000;
    }

    public void setImagesUrl(String[] imagesUrl) {
        initLayout();
        initImgFromNet(imagesUrl);
        showTime();
    }

    public void setImagesRes(int[] imagesRes) {
        initLayout();
        initImgFromRes(imagesRes);
        showTime();
    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        imageViews.clear();
        //加载bannaer所在的布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_bananer_view, this, true);
        //获取bannaer的viewpager
        banner_vp = (ViewPager) view.findViewById(R.id.banaer_view_pager);
        //指示圆点线性布局
        layoutLinearDot = (LinearLayout) view.findViewById(R.id.layout_linear_dot);
        layoutLinearDot.removeAllViews();
    }

    /**
     * 初始化图片，通过设置本地图片
     * @param imagesRes
     */
    private void initImgFromRes(int[] imagesRes) {
        count = imagesRes.length;
        //动态创建存放指示圆点的布局
        for (int i = 0; i < count; i++) {
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            iv_dot.setImageResource(R.drawable.dot_blur);
            //调用addview增加iv_dot到一个线性布局
            layoutLinearDot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        //默认第一个获得焦点
        iv_dots.get(0).setImageResource(R.drawable.dot_focus);

        for (int i = 0; i <= count + 1; i++) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.FIT_XY);
//            iv.setBackgroundResource(R.drawable.loading);
            if (i == 0) {
                iv.setImageResource(imagesRes[count - 1]);
            } else if (i == count + 1) {
                iv.setImageResource(imagesRes[0]);
            } else {
                iv.setImageResource(imagesRes[i - 1]);
            }
            imageViews.add(iv);
        }
    }

    /**
     * 通过网络获取图片
     * @param imagesUrl
     */
    private void initImgFromNet(String[] imagesUrl) {
        count = imagesUrl.length;
        for (int i = 0; i < count; i++) {
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            iv_dot.setImageResource(R.drawable.dot_blur);
            layoutLinearDot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        iv_dots.get(0).setImageResource(R.drawable.dot_focus);

        for (int i = 0; i <= count + 1; i++) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.FIT_XY);
//            iv.setBackgroundResource(R.mipmap.loading);
            if (i == 0) {
                mImageLoader.displayImage(imagesUrl[count - 1], iv, options);
            } else if (i == count + 1) {
                mImageLoader.displayImage(imagesUrl[0], iv, options);
            } else {
                mImageLoader.displayImage(imagesUrl[i - 1], iv, options);
            }
            imageViews.add(iv);
        }
    }

    private void showTime() {
        banner_vp.setAdapter(new KannerPagerAdapter());
        banner_vp.setFocusable(true);
        banner_vp.setCurrentItem(1);
        currentItem = 1;
        banner_vp.addOnPageChangeListener(new MyOnPageChangeListener());
        startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;
        handler.postDelayed(task, 1000);//yuan 2000
    }

    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        mImageLoader = ImageLoader.getInstance();
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    banner_vp.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    banner_vp.setCurrentItem(currentItem);
                    handler.postDelayed(task, 3000);
                }
            } else {
                handler.postDelayed(task, 5000);
            }
        }
    };

    class KannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

    }

    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (banner_vp.getCurrentItem() == 0) {
                        banner_vp.setCurrentItem(count, false);
                    } else if (banner_vp.getCurrentItem() == count + 1) {
                        banner_vp.setCurrentItem(1, false);
                    }
                    currentItem = banner_vp.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < iv_dots.size(); i++) {
                if (i == arg0 - 1) {
                    iv_dots.get(i).setImageResource(R.drawable.dot_focus);
                } else {
                    iv_dots.get(i).setImageResource(R.drawable.dot_blur);
                }
            }
        }

    }

    public void removeCallbacksAndMessages() {
        handler.removeCallbacksAndMessages(null);
        context = null;
    }

}