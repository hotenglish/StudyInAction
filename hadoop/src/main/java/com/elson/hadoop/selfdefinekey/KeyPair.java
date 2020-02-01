package com.elson.hadoop.selfdefinekey;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyPair implements WritableComparable<KeyPair> {

    private int year;
    private int hot;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    @Override
    public int compareTo(KeyPair o) {
        int res = Integer.compare(this.year, o.year);
        if (res != 0) {
            return res;
        }
        return Integer.compare(this.hot, o.hot);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.year);
        dataOutput.writeInt(this.hot);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readInt();
        this.hot = dataInput.readInt();
    }

    @Override
    public String toString() {
        return year + "\t" + hot;
    }

    @Override
    public int hashCode() {
        return new Integer(year + hot).hashCode();
    }
}
