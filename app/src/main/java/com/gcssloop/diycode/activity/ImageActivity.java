package com.gcssloop.diycode.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gcssloop.diycode.DiskImageCache;
import com.gcssloop.diycode.R;
import com.gcssloop.diycode.base.app.BaseImageActivity;
import com.gcssloop.diycode.base.app.ViewHolder;
import com.gcssloop.diycode_sdk.log.Logger;

/**
 * Created by zhangyuanyuan on 2017/12/13.
 */

public class ImageActivity extends BaseImageActivity {

    DiskImageCache mCache;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        setTitle("查看图片");
        mCache = new DiskImageCache(this);
        if (mCurrentMode == MODE_ERROR) {
            return;
        }
        ViewPager viewPager = holder.get(R.id.view_pager);
        final LayoutInflater inflater = getLayoutInflater();


        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView photoView = (PhotoView) inflater.inflate(R.layout.item_image, container, false);
                photoView.enable();
                String url = images.get(position);
                if (mCache.hasCache(url)) {
                    String file = mCache.getDiskPath(url);
                    Glide.with(ImageActivity.this).load(file).into(photoView);
                } else {
                    Glide.with(ImageActivity.this).load(images.get(position)).into(photoView);
                }
                container.addView(photoView);
                Logger.e("添加Item");
                return photoView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                PhotoView photoView = (PhotoView) container.findViewById(R.id.photo_view);
                container.removeView(photoView);
            }
        });
        viewPager.setCurrentItem(current_image_position);
    }

    public void loadImage(String url, PhotoView photoView) {
        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(photoView);
    }
}
