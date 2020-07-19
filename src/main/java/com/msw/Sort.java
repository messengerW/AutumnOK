package com.msw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Properties;

/*
 * File: Sort.java
 * Date: 2020-05-16 19:45
 * Author: msw
 * PS 利用 MapReduce 实现排序,利用的是 MapReduce 的默认排序功能.
 * 也就是 Reduce 任务首先会根据输入的 key 对所有的 <key, value-list> 进行顺序排序
*/
public class Sort {
    public static class SortMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private static IntWritable data = new IntWritable();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            /**
             * @Function: MapOutputKey-每一行待排序的数字, MapOutputValue-任意
             * @param key 字节偏移量
             * @param value 每一行的待排序数字
             * @param context
             * @Return: void
            */

            String line = value.toString();
            data.set(Integer.parseInt(line));
            context.write(data, one);

            System.out.print("\nMapInputKey: " + key + "\tMapInputValue: " + value);
            System.out.print("\t===>\t");
            System.out.println("MapOutputKey: " + data + "\tMapOutputValue: " + one);

        }
    }

    public static class SortReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

        private static IntWritable data = new IntWritable(1);

        public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            /**
             * @Function: 因为已经默认排序过，所以只需要将 ReduceInputKey 位置上的数字放到 ReduceOutputValue 输出即可，
             *            ReduceOutputKey 位置给出顺序号
             * @param key   排序后的数字
             * @param values    1
             * @param context
             * @Return: void
            */

            System.out.print("\nReduceInputKey: " + key + "\tReduceInputValue: ");

            for (IntWritable val : values) {
                context.write(data, key);

                System.out.print(val + "\t===>\t");
                System.out.println("ReduceOutputKey: " + data + "\tReduceOutputValue: " + key);

                data = new IntWritable(data.get()+1);
            }


        }
    }

    public static void main(String[] args) throws Exception, IOException {

//        Path srcFile = new Path("");
        Path inputFile = new Path("hdfs://centos:9000/input/sort.txt");
        Path outputPath = new Path("hdfs://centos:9000/output/sort");

        try {
            Properties properties = System.getProperties();
            properties.setProperty("HADOOP_USER_NAME", "root");

            Configuration conf = new Configuration();

            try {
                FileSystem hdfs = outputPath.getFileSystem(conf);

//                if ( !hdfs.exists(inputFile) )
//                    hdfs.copyFromLocalFile(srcFile, inputFile);

                if ( hdfs.exists(outputPath) )
                    hdfs.delete(outputPath, true);

                hdfs.close();
            } catch (IOException ie) {
                ie.fillInStackTrace();
            }

            Job job = Job.getInstance(conf, "SortApp");
            job.setJarByClass(Sort.class);
            job.setJar("out/artifacts/Sort/sort.jar");

            job.setMapperClass(SortMapper.class);
            job.setReducerClass(SortReducer.class);

            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job, inputFile);
            FileOutputFormat.setOutputPath(job, outputPath);

            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
