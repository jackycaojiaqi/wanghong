package com.fubang.wanghong.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fubang.wanghong.R;
import com.fubang.wanghong.adapters.RoomChatAdapter;
import com.fubang.wanghong.entities.GiftEntity;
import com.fubang.wanghong.utils.GiftUtil;
import com.xlg.android.protocol.BigGiftRecord;
import com.xlg.android.protocol.RoomChatMsg;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_person)
public class PersonFragment extends BaseFragment {

    @ViewById(R.id.person_list)
    ListView listView;
    private List<RoomChatMsg> data = new ArrayList<>();

    private RoomChatAdapter adapter;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        adapter = new RoomChatAdapter(data,getContext());
        listView.setAdapter(adapter);
    }
    //接收服务器发送的消息更新列表
    @Subscriber(tag="PersonMsg")
    public void getRoomChatMsg(RoomChatMsg msg){
//        Log.d("123",msg.getContent());
//        if(msg.getMsgtype() == 0) {
//            if (msg.getIsprivate() == 1) {
//                if (msg.getToid() == 0 || msg.getToid() == Integer.parseInt(StartUtil.getUserId(getContext()))) {
//                    //("<b><FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:17px; COLOR:#FF0000\">/mr599</FONT></b>")) {
//                    //<b><FONT style="FONT-FAMILY:宋体;FONT-SIZE:17px; COLOR:#FF0000">/mr599</FONT></b>
                    data.add(msg);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(listView.getCount() - 1);
//                }
//            }
//        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
