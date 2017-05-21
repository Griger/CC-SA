package pearsonGriger;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PearsonMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, ArrayWritable> {
    private static final int MISSING = 9999;

    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
      String line = value.toString();
      String[] parts = line.split(",");
      Double x, y, x2, y2, xy;

      for (int i = 0; i < (parts.lenght -1); i++)
        for (int j = i + 1; j < (parts.lenght - 1); j++){
          x = Double.parseDouble(parts[i]); y = Double.parseDouble(parts[j]);
          x2 = String.valueOf(x*x); y2 = String.valueOf(y*y); xy = String.valueOf(x*y);

          String[] array = {parts[i],parts[j],xy,x2,y2};

          ArrayWritable values = new ArrayWritable(array);

          output.collect(new Text("C" + i + "C" + j), values);
        }
}
