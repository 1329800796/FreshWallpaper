package com.zhang.freshwallpaper;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhang.freshwallpaper.util.Callback;
import com.zhang.freshwallpaper.util.Invoker;
import com.zhang.freshwallpaper.util.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
/**
 * 图片展示界面
 * @author zhangyg
 *
 */
public class ImageActivity extends Activity {

	private RelativeLayout rl;
	private ImageView iv;
	private TextView tv;
	private EditText et;
	private SeekBar sb1;
	private SeekBar sb2;
	private String path;
	private ProgressDialog pd;

	public ImageActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		initView();
		iv.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						iv.getLayoutParams().height = iv.getWidth();
						iv.getViewTreeObserver().removeGlobalOnLayoutListener(
								this);
					}
				});
		
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		path = getIntent().getStringExtra("path");
		rl = (RelativeLayout) findViewById(R.id.rl);
		iv = (ImageView) findViewById(R.id.iv);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
		sb1 = (SeekBar) findViewById(R.id.sb1);
		sb2 = (SeekBar) findViewById(R.id.sb2);
		ImageLoader.getInstance().displayImage("file://" + path, iv);
		
		sb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				int padding = 200-arg1;
				iv.setPadding(padding, padding, padding, padding);
				iv.invalidate();//刷新view
			}
		});
		
		sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tv.setTextSize(30+arg1);
			}
		});
		
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				tv.setText(arg0.toString());
			}
		});
	}
	
	private Bitmap bmp;
	
	public void onSave(View v){
		new Invoker(new Callback() {
			
			@Override
			public boolean onRun() {
				// TODO Auto-generated method stub
				return Util.saveImageToGallery(getApplicationContext(), bmp, path.endsWith(".png"));
			}
			
			@Override
			public void onBefore() {
				// TODO Auto-generated method stub
				pd = new ProgressDialog(ImageActivity.this);
				pd.setCancelable(false);//keydown dismiss
				pd.show();
				bmp = Util.convertViewToBitmap(rl);
				
			}
			
			@Override
			public void onAfter(boolean b) {
				// TODO Auto-generated method stub
				if (b) {
					Util.t(getApplicationContext(), "保存成功");
					pd.dismiss();
					finish();
				} else {
					Util.t(getApplicationContext(), "保存失败");
					pd.dismiss();
				}
			}
		}).start();
	}
}
