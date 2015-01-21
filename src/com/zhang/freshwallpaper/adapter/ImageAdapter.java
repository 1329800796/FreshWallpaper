package com.zhang.freshwallpaper.adapter;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhang.freshwallpaper.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

	private LayoutInflater inflater;//布局加载器
	
	private ArrayList<String> paths = new ArrayList<String>();//数据源sd卡
	
	public ImageAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return paths.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return paths.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

//	public void addAll(ArrayList<String> paths) {
//        this.paths.clear();
//        this.paths.addAll(paths);
//    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_image, null);
			holder = new ViewHolder();
			holder.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage("file://"+paths.get(position), holder.iv_item );
		return holder.iv_item;
	}

	static class ViewHolder {
        ImageView iv_item;
    }

}
