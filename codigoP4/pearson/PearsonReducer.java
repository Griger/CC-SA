package pearsonGriger;
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class PearsonReducer extends MapReduceBase implements Reducer<Text, DoubleArrayWritable, Text, DoubleWritable> {

    public void reduce(Text key, Iterator<DoubleArrayWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
			double xsum, x2sum, ysum, y2sum, xysum;
			double xmean, ymean, x2mean, y2mean, xymean, sigmaX, sigmaY, sigmaXY, rhoXY;
			int nRows = 0;

			xsum = x2sum = ysum = y2sum = xysum = 0.0;

			while (values.hasNext()) {
				nRows++;

				double[] array = values.next().toDoubleArray();

				xsum += array[0];
				ysum += array[1];
				xysum += array[2];
				x2sum += array[3];
				y2sum += array[4];
			}

			xmean = xsum/nRows; ymean = ysum/nRows; xymean = xysum/nRows; x2mean = x2sum/nRows; y2mean = y2sum/nRows;

			sigmaX = Math.sqrt(x2mean - xmean*xmean);
			sigmaY = Math.sqrt(y2mean - ymean*ymean);
			sigmaXY = xymean - xmean*ymean;

			rhoXY = sigmaXY / (sigmaX * sigmaY);
			output.collect(key, new DoubleWritable(rhoXY));
		}
}
