package com.fubang.wanghong.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.entities.HistoryEnity;
import com.fubang.wanghong.R;
import com.fubang.wanghong.ui.RoomActivity_;
import com.fubang.wanghong.ui.TestActivity_;
import com.zhuyunjian.library.ListBaseAdapter;

import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p>
 * 项目名称：newfubangzhibo
 * 类描述：历史浏览记录列表适配器
 * 创建人：zhuyunjian
 * 创建时间：2016-09-28 12:50
 * 修改人：zhuyunjian
 * 修改时间：2016-09-28 12:50
 * 修改备注：
 */
public class HistoryAdapter extends ListBaseAdapter<HistoryEnity> {
    public HistoryAdapter(List<HistoryEnity> list, Context context) {
        super(list, context);
    }

    @Override
    public View getItemView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_history_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(AppConstant.HEAD_URL + list.get(position).getRoompic()))
                .setAutoPlayAnimations(true)
                .build();
//        Log.d("123", AppConstant.HEAD_URL + list.get(position).getRoompic());
        holder.headImage.setController(controller);
        holder.idTv.setText(list.get(position).getRoomid());
        holder.nameTv.setText(list.get(position).getRoomname());
        final String[] s = list.get(position).getGateway().split(";");
        //点击进入房间
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(RoomActivity_.intent(context)
                        .extra("roomIp",s[0]).extra("roomId",list.get(position).getRoomid()).get());
            }
        });
        return convertView;
    }

    static class ViewHolder {
        LinearLayout layout;
        TextView idTv, nameTv;
        SimpleDraweeView headImage;

        public ViewHolder(View itemView) {
            layout = (LinearLayout) itemView.findViewById(R.id.history_layout);
            idTv = (TextView) itemView.findViewById(R.id.history_room_id);
            nameTv = (TextView) itemView.findViewById(R.id.history_room_name);
            headImage = (SimpleDraweeView) itemView.findViewById(R.id.history_list_headicon);
        }
    }
}

