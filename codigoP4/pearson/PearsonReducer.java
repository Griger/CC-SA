package pearsonGriger;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class PearsonReducer extends MapReduceBase implements Reducer<Text, ArrayWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterator<ArrayWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
			double xsum, x2sum, ysum, y2sum, xysum;
			double xmean, ymean, x2mean, y2mean, xymean, sigmaX, sigmaY, sigmaXY, rhoXY;
			int nRows = 0;

			xsum = x2sum = ysum = y2sum = xysum = 0.0;

			while (values.hasNext()) {
				nRows++;
				String[] array = values.next().toStrings();
				double[] valueArray = Arrays.stream(array).mapToDouble(Double::parseDouble).toArray();

				xsum += valueArray[0];
				ysum += valueArray[1];
				xysum += valueArray[2];
				x2sum += valueArray[3];
				y2sum += valueArray[4];
			}

			xmean = xsum/nRows; ymean = ysum/nRows; xymean = xysum/nRows; x2mean = x2sum/nRows; y2mean = y2sum/nRows;

			sigmaX = Math.sqrt(x2mean - xmean^2);
			sigmaY = Math.sqrt(y2mean - ymean^2);
			sigmaXY = xymean - xmean*ymean;

			rhoXY = sigmaXY / (sigmaX * sigmaY);
			output.collect(key, new DoubleWritable(rhoXY));
		}
}
