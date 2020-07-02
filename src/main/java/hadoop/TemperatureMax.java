package hadoop;

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
import utils.FileDelete;

import java.io.File;
import java.io.IOException;

public class TemperatureMax {
    public static class MaxTempMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split(" ");
            context.write(new IntWritable(Integer.parseInt(arr[0])), new IntWritable(Integer.parseInt(arr[1])));
        }
    }

    public static class MaxTempReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
        @Override
        protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max = Integer.MIN_VALUE;
            for (IntWritable iw : values) {
                max = Math.max(max, iw.get());
            }
            context.write(key, new IntWritable(max));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("MaxTempApp");
        FileInputFormat.addInputPath(job, new Path("data/temperature.txt"));
        FileDelete.deleteDir(new File("data/temperature-max-result"));
        FileOutputFormat.setOutputPath(job, new Path("data/temperature-max-result"));
        job.setNumReduceTasks(3);
        job.setJarByClass(TemperatureMax.class);
        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }
}
