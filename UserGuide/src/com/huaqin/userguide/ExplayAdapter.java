package com.huaqin.userguide;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExplayAdapter extends ArrayAdapter<Explayinfo> {

	private final ArrayList<Explayinfo> listarray;
	private final Context mContext;
	private final LayoutInflater mInflater;

	public ExplayAdapter(Context context, ArrayList<Explayinfo> apps) {
		super(context, 0, apps);
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.listarray = apps;

	}

	@Override
	public int getCount() {
		return this.listarray.size();
	}

	@Override
	public Explayinfo getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Explayinfo info = listarray.get(position);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.grid_view_strle, null);
			//convertView.setFocusable(false);
		}
		/*
		if (position % 2 == 0) {
			convertView
					.setBackgroundResource(R.drawable.grid_view_item_bg_y);
		} else {
			convertView
					.setBackgroundResource(R.drawable.grid_view_item_bg_w);
		}
		*/
		TextView title = (TextView) convertView.findViewById(R.id.every_title);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.explay_item_preview);
		TextView progress = (TextView) convertView
				.findViewById(R.id.every_progress);

		if (info != null) {
			title.setText(info.getInfo_name());
			image.setImageResource(info.getInfo_image());

			progress.setText(info.getInfo_read_number() + "/"
					+ info.getInfo_number());
		}

		return convertView;

	}

}
