```bash
javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* -d java_classes Pearson* DoubleArrayWritable.java

/usr/java/jdk1.7.0_51/bin/jar -cvf pearson.jar -C java_classes / .

hadoop jar pearson.jar pearsonGriger.Pearson /tmp/BDCC/datasets/ECBDL14/ECBDL14_10tst.data ./stat/output/
```

Pasos para limpiar el directorio de salidas cada vez que queramos ejecutar un nuevo algoritmo

```bash
hdfs dfs -rm stat/output/* && hdfs dfs -rmdir stat/output
```
