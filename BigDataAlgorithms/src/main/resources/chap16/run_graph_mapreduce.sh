export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/chap16/input/sample_graph.txt
OUTPUT=/chap16/output/2
DRIVER=org.dataalgorithms.chap16.mapreduce.TriangleCounterDriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT


