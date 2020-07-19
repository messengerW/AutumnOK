package com.msw;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
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
 * File: WordCount_new.java
 * Date: 2020-05-15 18:28
 * Author: msw
 * PS 又开始看MapReduce了，继续w从WordCount开始.加了System调试更方便理解，打印出的内容需要在 主机名:8042/logs/userlogs/下查看
*/
public class WordCount_new {

    public static class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            /**
             * @Function: StringTokenizer 是 java 一个用于分隔字符串的方法：
             * `StringTokenizer(String str, String delim):返回一个用来解析str的StringTokenizer对象，
             * 以delim为分隔符分隔str，如果未指定分隔符，则采用默认分隔符(' '、'\n'、'\t'、'\r')`
             * @param key: MapInputKey, 在这里是字节偏移量
             * @param value: MapInputValue, 每一行的内容
             * @param context: 跟踪 Task 的状态，记录上下文信息，充当 Map()、Reduce()之间的桥梁
             * @Return: void
             */
            String line = value.toString();     // 将行内容转成字符串
            StringTokenizer itr = new StringTokenizer(line);

            System.out.println("\nMapInputKey: " + key + "\t\t\tMapInputValue: " + value);

            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
                System.out.println("MapOutputKey: " + word + "\t\t\tMapOutputValue: " + one);
            }
        }

    }

    public static class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            /**
             * @Function: 在这个例子中，Reducer 方法的作用就是统计每个单词出现的频数
             * @param key: ReduceInputKey 这里是单词word
             * @param values: ReduceInputValue 频数列表，就是key所对应的这个单词在每一行出现的次数
             * @param context
             * @Return: void
             */

            int sum = 0;

            System.out.print("\nReduceInputKey: " + key + "\t\t\tReduceInputValue: ");


            // 计算这个单词出现的总次数
            for (IntWritable val : values) {
                System.out.print(val + ",");
                sum += val.get();
            }

            result.set(sum);
            context.write(key, result);
            System.out.println("\nReduceOutputKey: " + key + "\t\t\tReduceOutputValue: " + result);

        }
    }

    public static void main(String[] args) throws Exception {

        // 从本机选择文件上传至 HDFS
        Path srcFile = new Path("C:/Windows/WindowsUpdate.log");
        // 输入文件路径(一定要是路径 + 文件)
        Path inputFile = new Path("hdfs://centos:9000/input/test.txt");
        // 结果输出路径
        Path outputPath = new Path("hdfs://centos:9000/output/word_new");

        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");

        try {
            Configuration conf = new Configuration();

            FileSystem hdfs = outputPath.getFileSystem(conf);

            // 判断 inputFile 文件是否存在,若不存在需要上传测试文件至 HDFS
            if (!hdfs.exists(inputFile)) {
                hdfs.copyFromLocalFile(srcFile, inputFile);     // 从本地上传文件至 HDFS
            }

            // 判断 output 目录是否已经存在,如果存在要先删除
            if (hdfs.exists(outputPath)) {
                hdfs.delete(outputPath, true);
            }

            hdfs.close();

            Job job = Job.getInstance(conf, "App_WordCount");
            job.setJarByClass(WordCount_new.class);
            job.setJar("out/artifacts/WordCount_new/WordCount_new.jar");

            // 设置 Mapper 类相关
            job.setMapperClass(WordMapper.class);
            // job.setMapOutputKeyClass(Text.class);        // 如果Map输出格式与Reduce一致则无需再单独设置
            // job.setMapOutputValueClass(IntWritable.class);

            // 在 Mapper 与 Reducer 之间可以设置一个可选的 Combiner，前提是不能影响 Reducer 最终输出
            // 添加了 Combiner 之后，查看 Reducer 任务的输入会发现有所不同
            // job.setCombinerClass(WordReducer.class);

            // 设置 Reducer 类相关
            job.setReducerClass(WordReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            // 设置输入/输出格式
            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            // 设置输入/输出路径
            FileInputFormat.addInputPath(job, inputFile);
            FileOutputFormat.setOutputPath(job, outputPath);

            // 设置 reduce 任务的个数，默认个数为一个（一般 reduce 的个数越多效率越高）
            // 这里设置的个数会体现在输出路径下 /output/part-r-0000x 的个数
            // job.setNumReduceTasks(2);

            // 提交 Job
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
