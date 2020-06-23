package org.dataalgorithms.chap14.sparkwithlambda;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

@Public
@Stable
public class MyDoubleWritable implements  Serializable, WritableComparable<MyDoubleWritable> {

    private static final long serialVersionUID = -8398178743639693917L;

    private double value = 0.0D;

    public MyDoubleWritable() {
    }

    public MyDoubleWritable(double value) {
        this.set(value);
    }

    public void readFields(DataInput in) throws IOException {
        this.value = in.readDouble();
    }

    public void write(DataOutput out) throws IOException {
        out.writeDouble(this.value);
    }

    public void set(double value) {
        this.value = value;
    }

    public double get() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MyDoubleWritable)) {
            return false;
        } else {
            MyDoubleWritable other = (MyDoubleWritable)o;
            return this.value == other.value;
        }
    }

    public int hashCode() {
        return (int)Double.doubleToLongBits(this.value);
    }

    public int compareTo(MyDoubleWritable o) {
        return this.value < o.value ? -1 : (this.value == o.value ? 0 : 1);
    }

    public String toString() {
        return Double.toString(this.value);
    }

    static {
        WritableComparator.define(MyDoubleWritable.class, new MyDoubleWritable.Comparator());
    }

    public static class Comparator extends WritableComparator {
        public Comparator() {
            super(MyDoubleWritable.class);
        }

        public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
            double thisValue = readDouble(b1, s1);
            double thatValue = readDouble(b2, s2);
            return thisValue < thatValue ? -1 : (thisValue == thatValue ? 0 : 1);
        }
    }

}
