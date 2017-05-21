package pearsonGriger;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PearsonMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleArrayWritable> {
    private static final int MISSING = 9999;

    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleArrayWritable> output, Reporter reporter) throws IOException {
      String line = value.toString();
      String[] parts = line.split(",");
      double x, y;

      for (int i = 0; i < parts.length -1; i++)
        for (int j = i + 1; j < (parts.length - 1); j++){
          x = Double.parseDouble(parts[i]); y = Double.parseDouble(parts[j]);

          double[] values = {x,y,x*y,x*x,y*y};

          output.collect(new Text("C" + i + "C" + j), new DoubleArrayWritable(values));
        }
    }
}
