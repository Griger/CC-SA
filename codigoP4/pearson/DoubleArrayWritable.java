package pearsonGriger;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Writable;

public class DoubleArrayWritable extends ArrayWritable {
  public DoubleArrayWritable() {
    super(DoubleWritable.class);
  }

  public DoubleArrayWritable(double[] values) {
    super(DoubleWritable.class);
    DoubleWritable[] numbers = new DoubleWritable[values.length];
    for (int i = 0; i < values.length; i++) {
        numbers[i] = new DoubleWritable(values[i]);
    }
    set(numbers);
  }

  public double[] toDoubleArray() {
    Writable[] w = this.get();
    double[] a = new double[w.length];

    for (int i = 0; i < a.length; i++)
      a[i] = Double.parseDouble(w[i].toString());

    return a;
  }
}
