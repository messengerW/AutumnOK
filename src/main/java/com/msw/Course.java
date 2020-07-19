package com.msw;

/*
 * File: Course.java
 * Date: 2019/11/20-20:02
 * Author: msw.
 * PS 最近在学习 MapReduce, 实践一下
*/

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Course {

    public static void main(String[] args) throws Exception {

        String inputPath = "hdfs://centos:9000/input/course.txt";
        String outputPath = "hdfs://centos:9000/output/course";

        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Course.class);
        job.setJar("out/artifacts/Course/Course.jar");

        job.setMapperClass(Mapper_CS.class);
        job.setReducerClass(Reducer_CS.class);
        // 指定 Map 输入/输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        // 指定 Reduce 输入/输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 指定 MapReduce 程序的输入/输出路径
        Path input = new Path(inputPath);
        Path output = new Path(outputPath);

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(output)) {
            fs.delete(output, true);
        }
        FileInputFormat.setInputPaths(job, input);
        FileOutputFormat.setOutputPath(job, output);

        job.setNumReduceTasks(3);

        // 最后提交任务
        boolean waitForCompletion = job.waitForCompletion(true);
        System.exit(waitForCompletion ? 0 : 1);
    }

    /**
     * Mapper组件：
     * <p>
     * 输入的key:
     * 输入的value: computer,huangxiaoming,85
     * <p>
     * 输出的key: course
     * 输入的value: score
     */
    private static class Mapper_CS extends Mapper<LongWritable, Text, Text, Text> {

        Text keyOut = new Text();
        Text valueOut = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] splits = value.toString().split(",");
            String course = splits[0];
            String score = splits[2];

            keyOut.set(course);
            valueOut.set(score);

            context.write(keyOut, valueOut);
        }
    }

    /**
     * Reducer组件：
     * <p>
     * 输入的key:
     * 输入的values:
     * <p>
     * 输出的key:
     * 输入的value:
     */
    private static class Reducer_CS extends Reducer<Text, Text, Text, Text> {

        Text valueOut = new Text();

        List<Integer> scoreList = new ArrayList<Integer>();

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            scoreList.clear();
            for (Text t : values) {
                scoreList.add(Integer.valueOf(t.toString()));
            }

            // 求最高成绩和最低成绩
            Integer maxScore = Collections.max(scoreList);
            Integer minScore = Collections.min(scoreList);

            int sumScore = 0;
            for (int score : scoreList) {
                sumScore += score;
            }

            // 求平均成绩
            double avgScore = sumScore * 1D / scoreList.size();

            valueOut.set(maxScore + "\t" + minScore + "\t" + Math.round(avgScore));
            context.write(key, valueOut);
        }
    }
}