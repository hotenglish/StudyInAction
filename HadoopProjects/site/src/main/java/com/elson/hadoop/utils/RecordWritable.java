package com.elson.hadoop.utils;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RecordWritable implements WritableComparable<RecordWritable> {

    private String product_no;
    private String lac_id;
    private int moment;
    private String start_time;
    private String user_id;
    private String county_id;
    private int staytime;
    private String city_id;

    @Override
    public int compareTo(RecordWritable o) {
        // 先按手机号排序 Asc
        int value = this.product_no.compareTo(o.product_no);
        if (value == 0)
            // 再按时间进行排序 Desc
            return o.start_time.compareTo(this.start_time);
        return value;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(product_no);
        out.writeUTF(lac_id);
        out.writeInt(moment);
        out.writeUTF(start_time);
        out.writeUTF(user_id);
        out.writeUTF(county_id);
        out.writeInt(staytime);
        out.writeUTF(city_id);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        product_no = in.readUTF();
        lac_id = in.readUTF();
        moment = in.readInt();
        start_time = in.readUTF();
        user_id = in.readUTF();
        county_id = in.readUTF();
        staytime = in.readInt();
        city_id = in.readUTF();
    }

    @Override
    public String toString() {
        return this.product_no + " " + this.lac_id + " " + this.moment + " "
                + this.start_time + " " + user_id + " " + county_id + " " + staytime + " " + city_id;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getLac_id() {
        return lac_id;
    }

    public void setLac_id(String lac_id) {
        this.lac_id = lac_id;
    }

    public int getMoment() {
        return moment;
    }

    public void setMoment(int moment) {
        this.moment = moment;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }

    public int getStaytime() {
        return staytime;
    }

    public void setStaytime(int staytime) {
        this.staytime = staytime;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
