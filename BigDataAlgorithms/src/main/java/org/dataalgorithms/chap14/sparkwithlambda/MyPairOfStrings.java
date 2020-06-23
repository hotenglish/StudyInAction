package org.dataalgorithms.chap14.sparkwithlambda;

import edu.umd.cloud9.io.WritableComparatorUtils;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public class MyPairOfStrings implements Serializable, WritableComparable<MyPairOfStrings> {

    private static final long serialVersionUID = 1165184588599315841L;

    private String leftElement;
    private String rightElement;

    static {
        WritableComparator.define(MyPairOfStrings.class, new MyPairOfStrings.Comparator());
    }

    public MyPairOfStrings() {
    }

    public MyPairOfStrings(String left, String right) {
        this.set(left, right);
    }

    public void readFields(DataInput in) throws IOException {
        this.leftElement = in.readUTF();
        this.rightElement = in.readUTF();
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.leftElement);
        out.writeUTF(this.rightElement);
    }

    public String getLeftElement() {
        return this.leftElement;
    }

    public String getRightElement() {
        return this.rightElement;
    }

    public String getKey() {
        return this.leftElement;
    }

    public String getValue() {
        return this.rightElement;
    }

    public void set(String left, String right) {
        this.leftElement = left;
        this.rightElement = right;
    }

    public boolean equals(Object obj) {
        MyPairOfStrings pair = (MyPairOfStrings)obj;
        return this.leftElement.equals(pair.getLeftElement()) && this.rightElement.equals(pair.getRightElement());
    }

    public int compareTo(MyPairOfStrings pair) {
        String pl = pair.getLeftElement();
        String pr = pair.getRightElement();
        return this.leftElement.equals(pl) ? this.rightElement.compareTo(pr) : this.leftElement.compareTo(pl);
    }

    public int hashCode() {
        return this.leftElement.hashCode() + this.rightElement.hashCode();
    }

    public String toString() {
        return "(" + this.leftElement + ", " + this.rightElement + ")";
    }

    public MyPairOfStrings clone() {
        return new MyPairOfStrings(this.leftElement, this.rightElement);
    }

    public static class Comparator extends WritableComparator {
        public Comparator() {
            super(MyPairOfStrings.class);
        }

        public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
            String thisLeftValue = WritableComparatorUtils.readUTF(b1, s1);
            String thatLeftValue = WritableComparatorUtils.readUTF(b2, s2);
            if (thisLeftValue.equals(thatLeftValue)) {
                int s1offset = readUnsignedShort(b1, s1);
                int s2offset = readUnsignedShort(b2, s2);
                String thisRightValue = WritableComparatorUtils.readUTF(b1, s1 + 2 + s1offset);
                String thatRightValue = WritableComparatorUtils.readUTF(b2, s2 + 2 + s2offset);
                return thisRightValue.compareTo(thatRightValue);
            } else {
                return thisLeftValue.compareTo(thatLeftValue);
            }
        }
    }

}
