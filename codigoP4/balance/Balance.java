package balanceGriger;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
public class Balance {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: Balance <input path> <output path>");
			System.exit(-1);
		}
		JobConf conf = new JobConf(Balance.class);
		conf.setJobName("Class balance");
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setMapperClass(BalanceMapper.class);
		conf.setReducerClass(BalanceReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(DoubleWritable.class);
		JobClient.runJob(conf);
	}
}
