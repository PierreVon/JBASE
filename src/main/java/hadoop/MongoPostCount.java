package hadoop;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.bson.BSONObject;

import java.io.IOException;
import java.util.StringTokenizer;

public class MongoPostCount {
    public static class TokenizerMapper extends Mapper<Object, BSONObject, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, BSONObject value, Context context)
                throws IOException, InterruptedException {
            System.out.println("key: " + key);
            System.out.println("value: " + value);
            context.write(new Text(value.get("uid").toString()), one);
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
        conf.set("mongo.input.uri", "mongodb://localhost/p1.post");
        conf.set("mongo.output.uri", "mongodb://localhost/p1.hadoop_post");
        @SuppressWarnings("deprecation")
        Job job = new Job(conf, "word count");
        job.setJarByClass(MongoPostCount.class);
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
