package hadoop;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
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
import java.util.List;
import java.util.StringTokenizer;

public class MongoTopic {
    public static class TokenizerMapper extends Mapper<Object, BSONObject, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, BSONObject value, Context context)
                throws IOException, InterruptedException {
//            System.out.println("key: " + key);
//            System.out.println("value: " + value);
//            System.out.println(value.get("name") + " " + value.get("postList") + " " + "1");
            for (Object post : (List<Object>) value.get("postList")) {
                if (post instanceof String)
                    System.out.println(value.get("name") + " " + post + " " + "1");
                else {
                    BasicDBObject b = (BasicDBObject) post;
                    JSONObject jsonObject = JSONObject.parseObject(b.toJson());
                    System.out.println(value.get("name") + " " + jsonObject.get("pid") + " " + "1");
                }
            }
/*            StringTokenizer itr = new StringTokenizer(value.get("emerge_time").toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }*/
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
        conf.set("mongo.input.uri", "mongodb://localhost/p1.topic");
        conf.set("mongo.output.uri", "mongodb://localhost/p1.hadoop_rs");
        @SuppressWarnings("deprecation")
        Job job = new Job(conf, "word count");
        job.setJarByClass(MongoTopic.class);
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
