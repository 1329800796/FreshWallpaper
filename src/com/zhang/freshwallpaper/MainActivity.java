package com.zhang.freshwallpaper;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhang.freshwallpaper.adapter.ImageAdapter;
import com.zhang.freshwallpaper.util.Callback;
import com.zhang.freshwallpaper.util.Invoker;
import com.zhang.freshwallpaper.util.Util;
import com.zhang.freshwallpaper.view.DragLayout;
import com.zhang.freshwallpaper.view.DragLayout.DragListener;
/**
 * 主界面
 * @author zhangyg
 *
 */
public class MainActivity extends Activity {

	private DragLayout dl;// 自定义布局
	private GridView gv_img;//
	private ImageAdapter adapter;
	private ListView lv;
	private TextView tv_noimg;
	private ImageView iv_icon, iv_bottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Util.initImageLoader(this);// 初始化
		initDragLayout();
		initView();
	}

	/**
	 * c初始化布局
	 */
	private void initDragLayout() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				lv.smoothScrollToPosition(new Random().nextInt(30));// 随机30个
			}

			@Override
			public void onClose() {
				shake();
			}

			@Override
			public void onDrag(float percent) {
				ViewHelper.setAlpha(iv_icon, 1 - percent);
			}
		});
	}

	/**
	 * 初始化数据
	 */

	private void initView() {
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
		gv_img = (GridView) findViewById(R.id.gv_img);
		tv_noimg = (TextView) findViewById(R.id.iv_noimg);
		gv_img.setFastScrollEnabled(true);// 启动快速滑动
		adapter = new ImageAdapter(this);
		gv_img.setAdapter(adapter);
		gv_img.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 跳转
				Intent intent = new Intent(MainActivity.this,
						ImageActivity.class);
				intent.putExtra("path", adapter.getItem(position));
				startActivity(intent);				
			}
		});
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.item_text, new String[] { "NewBee", "ViCi Gaming",
						"Evil Geniuses", "Team DK", "Invictus Gaming", "LGD",
						"Natus Vincere", "Team Empire", "Alliance", "Cloud9",
						"Titan", "Mousesports", "Fnatic", "Team Liquid",
						"MVP Phoenix", "NewBee", "ViCi Gaming",
						"Evil Geniuses", "Team DK", "Invictus Gaming", "LGD",
						"Natus Vincere", "Team Empire", "Alliance", "Cloud9",
						"Titan", "Mousesports", "Fnatic", "Team Liquid",
						"MVP Phoenix" }));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Util.t(getApplicationContext(), "click " + position);
			}
		});
		iv_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dl.open();
				
			}
		});
	}

	/**
	 * 获取图片
	 */
	@Override
	protected void onResume() {
		super.onResume();
		loadImage();
	}

	private void loadImage() {
		new Invoker(new Callback() {
			@Override
			public boolean onRun() {
				adapter.addAll(Util.getGalleryPhotos(MainActivity.this));
				return adapter.isEmpty();
			}

			@Override
			public void onBefore() {
				// 转菊花
			}

			@Override
			public void onAfter(boolean b) {
				adapter.notifyDataSetChanged();
				if (b) {
					tv_noimg.setVisibility(View.VISIBLE);
				} else {
					tv_noimg.setVisibility(View.GONE);
					String s = "file://" + adapter.getItem(0);
					ImageLoader.getInstance().displayImage(s, iv_icon);
					ImageLoader.getInstance().displayImage(s, iv_bottom);
				}
				shake();
			}
		}).start();

	}

	/**
	 * 动画
	 */
	private void shake() {
		iv_icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
	}

}
