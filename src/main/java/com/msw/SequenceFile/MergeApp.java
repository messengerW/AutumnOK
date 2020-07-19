package com.msw.SequenceFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;
import java.util.Properties;

/*
 * File: MergeApp.java
 * Date: 2020-05-18 20:58
 * Author: msw
 * PS ...
*/
public class MergeApp {

    static class SequenceFileMapper extends Mapper<NullWritable, BytesWritable, Text, BytesWritable> {

        private Text filenameKey;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            InputSplit split = context.getInputSplit();
            Path path = ((FileSplit) split).getPath();
            filenameKey = new Text(path.toString());
        }

        @Override
        protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
            context.write(filenameKey, value);
        }

        public static void main(String[] args) throws Exception {

            Path inputFiles[] = {new Path("hdfs://centos:9000/input/merge/f1.txt"),
                    new Path("hdfs://centos:9000/input/merge/f2.txt"),
                    new Path("hdfs://centos:9000/input/merge/f3.txt"),
                    new Path("hdfs://centos:9000/input/merge/f4.txt"),
                    new Path("hdfs://centos:9000/input/merge/f5.txt")
            };
            Path outputPath = new Path("hdfs://centos:9000/output/MergeFileApp");

            try {
                Properties properties = System.getProperties();
                properties.setProperty("HADOOP_USER_NAME", "root");

                Configuration conf = new Configuration();

                final FileSystem hdfs = outputPath.getFileSystem(conf);
                if (hdfs.exists(outputPath)) {
                    hdfs.delete(outputPath, true);
                }

                Job job = Job.getInstance(conf, "MergeApp");

                job.setJar("out/artifacts/merge/merge.jar");

                job.setInputFormatClass(WholeFileInputFormat.class);
                job.setOutputFormatClass(SequenceFileOutputFormat.class);

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(BytesWritable.class);

                job.setMapperClass(SequenceFileMapper.class);

                FileInputFormat.setInputPaths(job, inputFiles);
                FileOutputFormat.setOutputPath(job, outputPath);

                System.exit(job.waitForCompletion(true) ? 0 : 1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
