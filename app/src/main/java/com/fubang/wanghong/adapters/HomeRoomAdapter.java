package com.fubang.wanghong.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.entities.RoomListEntity;
import com.fubang.wanghong.R;
import com.fubang.wanghong.ui.RoomActivity_;
import com.fubang.wanghong.ui.RoomNewActivity_;
import com.zhuyunjian.library.ListBaseAdapter;

import java.util.List;

/**
 * 首页房间列表的适配器
 * Created by dell on 2016/4/7.
 */
public class HomeRoomAdapter extends ListBaseAdapter<RoomListEntity> {
    private List<RoomListEntity> list;

    public HomeRoomAdapter(List<RoomListEntity> list, Context context) {
        super(list, context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getItemView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_room, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
//        if (position == 0){
////            holder.simpleDraweeView.setVisibility(View.GONE);
////            holder.roomLayout.setVisibility(View.GONE);
//        }else {
        if (!list.get(position).getRoompic().equals("")) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(AppConstant.HEAD_URL + list.get(position).getRoompic()))
                    .setAutoPlayAnimations(true)
                    .build();
//            Log.d("123", AppConstant.HEAD_URL + list.get(position).getRoompic());
            holder.simpleDraweeView.setController(controller);
            holder.homeImage.setVisibility(View.GONE);
            holder.simpleDraweeView.setVisibility(View.VISIBLE);
        }else {
            holder.simpleDraweeView.setVisibility(View.GONE);
            holder.homeImage.setVisibility(View.VISIBLE);
        }
//            holder.simpleDraweeView.setImageURI(Uri.parse(AppConstant.HEAD_URL+list.get(position).getRoompic()));
            holder.roomNumber.setText(list.get(position).getRscount() + "/" + list.get(position).getRoomrs());
            holder.roomText.setText(list.get(position).getRoomname());
            holder.roomLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("123",position+"-----------------");
                    context.startActivity(RoomActivity_.intent(context)
                            .extra("roomIp",list.get(position).getGateway()).extra("roomId",list.get(position).getRoomid()).extra("roomPwd",list.get(position).getRoompwd()).get());
                }
            });
//        }
        return convertView;
    }

    static class ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView roomNumber,roomText;
        LinearLayout roomLayout;
        ImageView homeImage;

        public ViewHolder(View itemView) {
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.home_room_pic);
            roomNumber = (TextView)itemView.findViewById(R.id.home_room_number);
            roomText = (TextView) itemView.findViewById(R.id.home_room_name);
            roomLayout = (LinearLayout) itemView.findViewById(R.id.home_room_layout);
            homeImage = (ImageView) itemView.findViewById(R.id.home_room_photo);
        }
    }
}
