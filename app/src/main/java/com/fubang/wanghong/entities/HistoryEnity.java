package com.fubang.wanghong.entities;

import com.google.gson.annotations.SerializedName;

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
 * 类描述：
 * 创建人：zhuyunjian
 * 创建时间：2016-09-28 10:15
 * 修改人：zhuyunjian
 * 修改时间：2016-09-28 10:15
 * 修改备注：
 */
public class HistoryEnity {

    /**
     * nvcbid : 100003
     * cname : 100003房间
     * croompic : 20160725200339_423.jpg
     * cgateaddr : 121.40.138.189:44056;121.40.138.189:44056;121.40.138.189:44056
     */
    @SerializedName("roomid")
    private String roomid;
    @SerializedName("roomname")
    private String roomname;
    @SerializedName("roompic")
    private String roompic;
    @SerializedName("gateway")
    private String gateway;
    @SerializedName("roompwd")
    private String roompwd;
    @SerializedName("rscount")
    private String rscount;
    @SerializedName("roomrs")
    private String roomrs;

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoompic() {
        return roompic;
    }

    public void setRoompic(String roompic) {
        this.roompic = roompic;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getRoompwd() {
        return roompwd;
    }

    public void setRoompwd(String roompwd) {
        this.roompwd = roompwd;
    }

    public String getRscount() {
        return rscount;
    }

    public void setRscount(String rscount) {
        this.rscount = rscount;
    }

    public String getRoomrs() {
        return roomrs;
    }

    public void setRoomrs(String roomrs) {
        this.roomrs = roomrs;
    }
}
