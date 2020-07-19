package com.msw.SortPro;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

/*
 * File: SortApp.java
 * Date: 2020-05-16 22:09
 * Author: msw
 * PS ...
*/
public class SortApp {
    public static class SortProMapper extends Mapper<LongWritable, Text, IntPair, IntWritable> {

        private final IntPair key = new IntPair();
        private final IntWritable value = new IntWritable();

        @Override
        protected void map(LongWritable inKey, Text inValue, Context context) throws IOException, InterruptedException {

            StringTokenizer itr = new StringTokenizer(inValue.toString());
            int left = 0;
            int right = 0;

            System.out.print("\nMapInputKey: " + inKey + "\tMapInputValue: " + inValue + "\t===>\t");

            if (itr.hasMoreTokens()) {
                left = Integer.parseInt(itr.nextToken());
                if (itr.hasMoreTokens()) {
                    right = Integer.parseInt(itr.nextToken());
                }
                key.set(left, right);
                value.set(right);
                context.write(key, value);
                System.out.println("MapOutputKey: " + "(" + left + "," + right + ")" + "\tMapOutputValue: " + value);

            }

        }
    }

    public static class GroupingComparator implements RawComparator<IntPair> {
        public int compare(byte[] bytes1, int s1, int l1, byte[] bytes2, int s2, int l2) {
            return WritableComparator.compareBytes(bytes1, s1, Integer.SIZE/8, bytes2, s2, Integer.SIZE/8);
        }

        public int compare(IntPair o1, IntPair o2) {
            int first1 = o1.getFirst();
            int first2 = o2.getFirst();
            return first1 - first2;
        }
    }

    public static class SortProReducer extends Reducer<IntPair, IntWritable, Text, IntWritable> {

        private static final Text Divider = new Text("=========");
        private final Text first = new Text();

        @Override
        protected void reduce(IntPair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {


            context.write(Divider, null);
            first.set(Integer.toString(key.getFirst()));
            for (IntWritable value : values) {

                System.out.print("\nReduceInputKey: " + "(" + key.getFirst() + "," + key.getSecond() + ")" + "\tReduceInputValue: ");
                System.out.print(value + "\t===>\t");
                System.out.println("ReduceOutputKey: " + first + "\tReduceOutputValue: " + value);

                context.write(first, value);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Path inputFile = new Path("hdfs://centos:9000/input/sortpro.txt");
        Path outputPath = new Path("hdfs://centos:9000/output/sortpro");

        try {
            Properties properties = System.getProperties();
            properties.setProperty("HADOOP_USER_NAME", "root");

            Configuration conf = new Configuration();

            FileSystem hdfs = inputFile.getFileSystem(conf);

            if (hdfs.exists(outputPath))
                hdfs.delete(outputPath,true);
            hdfs.close();

            Job job = Job.getInstance(conf,"SortPro");

            job.setJar("out/artifacts/SortPro/SortPro.jar");

            FileInputFormat.setInputPaths(job, inputFile);
            FileOutputFormat.setOutputPath(job, outputPath);

            job.setMapperClass(SortProMapper.class);
            job.setReducerClass(SortProReducer.class);

            job.setGroupingComparatorClass(GroupingComparator.class);

            job.setMapOutputKeyClass(IntPair.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
