package com.fubang.wanghong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.fubang.wanghong.entities.FaceEntity;
import com.fubang.wanghong.utils.FaceUtil;
import com.fubang.wanghong.utils.GlobalOnItemClickManager;
import com.fubang.wanghong.R;

import java.util.List;

/**
 * Created by dss886 on 15/9/22.
 */
public class XiHaHouFragment extends Fragment {

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.emotion_gird, container, false);
        BaseAdapter adapter = new XiHaHouEmojiAdapter(mContext);
        GridView grid = (GridView) view.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(GlobalOnItemClickManager.getInstance().getOnItemClickListener(1));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private class XiHaHouEmojiAdapter extends BaseAdapter {
        private List<FaceEntity> list = FaceUtil.getFaces();
        private Context mContext;

        public XiHaHouEmojiAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return 90;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.emotion_gird_item, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.image.setImageDrawable(mContext.getResources().getDrawable(list.get(position).getFaceId()));
//            Picasso.with(mContext).load(path).into(holder.image);
            return convertView;
        }

        private class ViewHolder {
            public ImageView image;
        }
    }
}
