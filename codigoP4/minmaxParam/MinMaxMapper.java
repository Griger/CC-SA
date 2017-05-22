package minmaxParamGriger;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.JobConf;

public class MinMaxMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {
    private static final int MISSING = 9999;
    public static int col;

    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
    	String line = value.toString();
    	String[] parts = line.split(",");
    	output.collect(new Text(String.valueOf(col)), new DoubleWritable(Double.parseDouble(parts[col])));
    }

    public void configure(JobConf conf){
      col = Integer.parseInt(conf.get("col"));
    }
}
