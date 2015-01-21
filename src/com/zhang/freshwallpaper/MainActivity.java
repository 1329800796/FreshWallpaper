package com.zhang.freshwallpaper;

import java.util.Random;
import com.nineoldandroids.view.ViewHelper;
import com.zhang.freshwallpaper.adapter.ImageAdapter;
import com.zhang.freshwallpaper.view.DragLayout;
import com.zhang.freshwallpaper.view.DragLayout.DragListener;
import android.app.Activity;
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
import android.widget.Toast;

public class MainActivity extends Activity {

	private DragLayout dl;
	private GridView gv_img;// gridview
	private ListView lv;// listview
	private TextView tv_noimg;
	private ImageView iv_icon, iv_bottom;

	private ImageAdapter adapter;// 自定义适配器

	public MainActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initDragLayout();
		initdata();
	}

	/**
	 * 初始化布局
	 */
	private void initDragLayout() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setDragListener(new DragListener() {

			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				lv.smoothScrollToPosition(new Random().nextInt(30));// 30数据随机
			}

			@Override
			public void onDrag(float percent) {
				// TODO Auto-generated method stub
				ViewHelper.setAlpha(iv_icon, 1 - percent);
			}

			@Override
			public void onClose() {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 初始化
	 */
	private void initdata() {
		// TODO Auto-generated method stub
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
		gv_img = (GridView) findViewById(R.id.gv_img);
		tv_noimg = (TextView) findViewById(R.id.iv_noimg);
		gv_img.setFastScrollEnabled(true);// 启用快速滚动滑块
		adapter = new ImageAdapter(this);
		gv_img.setAdapter(adapter);
		gv_img.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				// 跳转
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
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, arg2, 1).show();
			}
		});
		iv_icon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dl.open();// 开启侧滑
			}
		});
	}
	/**
	 * 获取图片
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	/**
	 * 动画效果
	 */
	private void shake() {
		iv_icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
	}

}
