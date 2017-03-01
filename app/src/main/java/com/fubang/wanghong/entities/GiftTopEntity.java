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
 * <p/>
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-06-24 16:36
 * 修改人：dell
 * 修改时间：2016-06-24 16:36
 * 修改备注：
 */
public class GiftTopEntity {

    /**
     * anchorName : 乖乖惹人爱
     * ncount : 11664572
     */
    @SerializedName("nbuddyid")
    private String nbuddyid;
    @SerializedName("ncount")
    private String ncount;
    @SerializedName("calias")
    private String calias;
    @SerializedName("cphoto")
    private String cphoto;

    public String getNbuddyid() {
        return nbuddyid;
    }

    public void setNbuddyid(String nbuddyid) {
        this.nbuddyid = nbuddyid;
    }


    public String getCalias() {
        return calias;
    }

    public void setCalias(String calias) {
        this.calias = calias;
    }

    public String getCphoto() {
        return cphoto;
    }

    public void setCphoto(String cphoto) {
        this.cphoto = cphoto;
    }


    public String getNcount() {
        return ncount;
    }

    public void setNcount(String ncount) {
        this.ncount = ncount;
    }
}
