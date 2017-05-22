package meanAllGriger;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MeanAllReducer extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
			Double nRows = 0.0;
			Double sum = 0.0;

			while (values.hasNext()) {
				nRows++;
				sum += values.next().get();
			}

			Text newKey = new Text("Mean" + key.toString());

			output.collect(newKey, new DoubleWritable(sum / nRows));
    }
}