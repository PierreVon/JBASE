package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import utils.FileDelete;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;

public class TemperatureMaxSort {
    public static class Combokey implements WritableComparable<Combokey> {
        private int year;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        private int temp;

        /*
         * 对key进行比较实现
         * */
        @Override
        public int compareTo(Combokey o) {
            System.out.println("Combokey.compareTo()" + o.toString());
            int y0 = o.getYear();
            int t0 = o.getTemp();
            //年份相同(s升序)
            if (year == y0) {
                //气温降序
                return -(temp - t0);
            } else {
                return (year - y0);
            }
        }

        /*
         * 串行化过程
         * */
        @Override
        public void write(DataOutput out) throws IOException {
            //年份
            out.writeInt(year);
            //气温
            out.writeInt(temp);
        }

        //反串行化的过程
        @Override
        public void readFields(DataInput in) throws IOException {
            year = in.readInt();
            temp = in.readInt();
        }

        public String toString() {
            return year + ":" + temp;
        }
    }

    public static class YearPartitioner extends Partitioner<Combokey,NullWritable> {
        @Override
        public int getPartition(Combokey key, NullWritable nullWritable, int numPartitions) {
            System.out.println("YearPartitioner.getPartition"+key);
            int year = key.getYear();
            return  year%numPartitions;
        }
    }

    public static class CombokeyComparator extends WritableComparator {
        protected CombokeyComparator(){
            super(Combokey.class,true);
        }
        public int compare(WritableComparable a,WritableComparable b){
            System.out.println("CombokeyComparator"+a+","+b);
            Combokey k1 = (Combokey)a;
            Combokey k2 = (Combokey)b;
            return k1.compareTo(k2);
        }
    }

    public static class YearGroupComparator extends WritableComparator{
        protected YearGroupComparator(){
            super(Combokey.class,true);
        }
        public int compare(WritableComparable a,WritableComparable b){
            System.out.println("YearGroupComparator"+a+","+b);
            Combokey key1 = (Combokey)a;
            Combokey key2 = (Combokey)b;
            return  key1.getYear()-key2.getYear();
        }
    }

    public static class MaxTempMapper extends Mapper<LongWritable, Text,Combokey,NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            System.out.println("MaxTempMapper.map");
            String[] arr = value.toString().split(" ");
            Combokey keyout = new Combokey();
            keyout.setYear(Integer.parseInt(arr[0]));
            keyout.setTemp(Integer.parseInt(arr[1]));
            context.write(keyout,NullWritable.get());
        }
    }

    public static class MaxTempReducer extends Reducer<Combokey,NullWritable,IntWritable,IntWritable> {
        @Override
        protected void reduce(Combokey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            int year = key.getYear();
            int temp = key.getTemp();
            System.out.println("MaxTempReducer.reduce"+year+","+temp);
            context.write(new IntWritable(year),new IntWritable(temp));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("MaxTempApp");
        FileInputFormat.addInputPath(job,new Path("data/temperature.txt"));
        FileDelete.deleteDir(new File("data/sort-max-temperature"));
        FileOutputFormat.setOutputPath(job,new Path("data/sort-max-temperature"));
        job.setJarByClass(TemperatureMaxSort.class);
        //设置Map类
        job.setMapperClass(MaxTempMapper.class);
        //设置Reduce类
        job.setReducerClass(MaxTempReducer.class);
        //设置Map输出类型
        job.setMapOutputKeyClass(Combokey.class);
        job.setMapOutputValueClass(NullWritable.class);
        //设置reduce输出类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        //设置分区类
        job.setPartitionerClass(YearPartitioner.class);
        //设置分组对比器
        job.setGroupingComparatorClass(YearGroupComparator.class);
        //设置排序对比器
        job.setSortComparatorClass(CombokeyComparator.class);
        job.setNumReduceTasks(3);
        job.waitForCompletion(true);

    }
}
