export APP_JAR=BigDataAlgorithms-1.0.jar

N=2
INPUT=/chap07/input/input.txt
OUTPUT=/chap07/output/2
DRIVER=org.dataalgorithms.chap07.mapreduce.MBADriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT $N


