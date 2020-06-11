export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/mononid/input
OUTPUT=/chap28/output/2
DRIVER=org.dataalgorithms.chap28.mapreduce.MeanDriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT