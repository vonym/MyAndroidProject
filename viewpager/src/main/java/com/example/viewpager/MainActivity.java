package com.example.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/*使用ViewPager制作导航界面GuidActivity
* 启动界面：SplashActivity
* 主界面:HomeActivity
*
* 1.ViewPager改怎么使用
* 1.1布局文件中定义ViewPager
* 1.2声明实例
* １.３为ViewPager去指定一个PagerAdapter
* 1.3.1写一个类继PagerAdapter
* 1.3.2实现里面的方法（默认两个,其实有四个）
* 1.3.3加载适配器
* 2.小圆点怎么跟随ViewPager滑动实现
*
*
* Activity+ fragment+ViewPager+fragment*/
public class MainActivity extends Activity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ImageView lan_Iv;
    private Button btn;
    private int[] imgs = {R.drawable.guide_1, R.drawable.guide_2,
            R.drawable.guide_3, R.drawable.guide_4};
    private List<ImageView> imgList;
    private int pointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须放在setContentView方法之前
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        lan_Iv = (ImageView) findViewById(R.id.lan_Iv);
        btn = (Button) findViewById(R.id.btn);
        imgList = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imgList.add(imageView);

            //根据图片的数量去放置相应的数量的小灰点
            ImageView huiImg = new ImageView(this);
            huiImg.setImageResource(R.drawable.xiaohuidian_bg);
            //LayoutParams好比衣服
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                //左边距
                params.leftMargin = 20;
            }
            huiImg.setLayoutParams(params);
            linearLayout.addView(huiImg);
        }
        viewPager.setAdapter(new MyAdapter());

        /**我们的代码都是写在onCreate(),onCreate()在调用的时候，
         *界面底层的绘制工作还没有完成，所以在onCreate()中得不到距离pointWidth
         *
         * addOnGlobalLayoutListener:在视图树绘制完成后调用*/
        lan_Iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //得到两个小灰点之间的距离
                pointWidth = linearLayout.getChildAt(1).getLeft() - linearLayout.getChildAt(0).getLeft();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //在viewPager不断滑动时调用
            //positionOffset：滑动的百分比
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lan_Iv.getLayoutParams();
                params.leftMargin = (int) (pointWidth * positionOffset + pointWidth * position);
                lan_Iv.setLayoutParams(params);
                Log.i("offset", positionOffset + "");
            }

            @Override
            public void onPageSelected(int position) {
                if (position == (imgList.size() - 1)) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    class MyAdapter extends PagerAdapter {
        //返回ViewPager里面有几页
        @Override
        public int getCount() {
            return imgs.length;
        }

        //用于判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //相当于getView()
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //优化问题
            /*ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(imgs[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);*/
            ImageView imageView = imgList.get(position);
            imageView.setImageResource(imgs[position]);
            container.addView(imageView);
            return imageView;
        }

        //从View中移除当前的
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}