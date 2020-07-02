package hadoop;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.bson.BSONObject;


import java.io.IOException;
import java.util.StringTokenizer;

public class MongoCount {
    public static class TokenizerMapper extends Mapper<Object, BSONObject, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, BSONObject value, Context context)
                throws IOException, InterruptedException {
//            System.out.println("key: " + key);
//            System.out.println("value: " + value);
            if (null != value.get("pid"))
                System.out.println(value.get("uid") + " " + value.get("pid") + " " + "1");
            StringTokenizer itr = new StringTokenizer(value.get("emerge_time").toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mongo.input.uri", "mongodb://localhost/p1.rs");
        conf.set("mongo.output.uri", "mongodb://localhost/p1.hadoop_rs");
        @SuppressWarnings("deprecation")
        Job job = new Job(conf, "word count");
        job.setJarByClass(MongoCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setInputFormatClass(MongoInputFormat.class);
        job.setOutputFormatClass(MongoOutputFormat.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
