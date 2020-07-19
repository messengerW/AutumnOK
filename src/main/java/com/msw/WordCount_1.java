package com.msw;

/*
 * File: WordCount_1.java
 * Date: 2019/11/22 16:25
 * Author: msw.
 * PS 再试一下 wordcount
*/

import org.apache.hadoop.conf.Configuration;
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
import java.util.StringTokenizer;

public class WordCount_1 {

    /*
    * Map Input: <LongWritable,Text>
    *     key - LongWritable,值得一提的是 LongWritable 表示该行在文件中的 偏移量,
    *     不同于行号,如果要表示行号应使用 IntWritable;
    *     value - Text,表示的是该行的文本内容.
    * Map Output: <Text,IntWritable>
    *     key - Text,这里表示的是 Word
    *     value - IntWritable,单词的计数
    * */
    public static class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            //  System.out.println(line);
            //  StringTokenizer split line  by 'space'
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                //  Map Output
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable i : values) {
                sum += i.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf = new Configuration();

        try {

            //新建一个 Job 工作
            Job job = Job.getInstance(conf, "WordCount_1");

            //设置运行类
            job.setJarByClass(WordCount_1.class);
            job.setJar("out/artifacts/WordCount_1/WordCount_1.jar");

            //设置要执行的 Mapper 类
            job.setMapperClass(WordMapper.class);
            //设置要执行的 Reducer 类
            job.setReducerClass(WordReducer.class);

            //设置 Mapper 输出 key 的类型
            job.setMapOutputKeyClass(Text.class);
            //设置 Mapper 输出 value 的类型
            job.setMapOutputValueClass(IntWritable.class);

            //设置 MapReduce 程序的输入目录
            FileInputFormat.setInputPaths(job, new Path("hdfs://centos:9000/input/word01.txt"));
            //设置 MapReduce 程序执行完毕后结果的输出目录
            FileOutputFormat.setOutputPath(job, new Path("hdfs://centos:9000/output/wordcount01"));

            //设置 reduce 任务的个数，默认个数为一个（一般 reduce 的个数越多效率越高）
            //这里设置的个数会体现在输出路径下 /output/part-r-0000x 的个数
            job.setNumReduceTasks(2);

            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
