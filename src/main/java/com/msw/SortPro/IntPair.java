package com.msw.SortPro;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 * File: IntPair.java
 * Date: 2020-05-16 21:59
 * Author: msw
 * PS ...
*/
public class IntPair implements WritableComparable<IntPair> {

    private int first = 0;
    private int second = 0;

    public void set(int left, int right) {
        first = left;
        second = right;
    }

    public int getFirst() {return first;}
    public int getSecond() {return second;}

    public void readFields(DataInput input) throws IOException {
        first = input.readInt();
        second = input.readInt();
    }

    public void write(DataOutput output) throws IOException {
        output.writeInt(first);
        output.writeInt(second);
    }

    @Override
    public int hashCode() {
        return first + "".hashCode() + second + "".hashCode();
    }

    @Override
    public boolean equals(Object right) {
        if (right instanceof IntPair) {
            IntPair r = (IntPair) right;
            return r.first == first && r.second == second;
        } else {
            return false;
        }
    }

    public int compareTo(IntPair o) {
        if (first != o.first) {
            return first - o.first;
        } else if (second != o.second) {
            return second - o.second;
        } else {
            return 0;
        }
    }

}
