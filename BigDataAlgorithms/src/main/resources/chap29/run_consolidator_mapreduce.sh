export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/chap29/input
OUTPUT=/chap29/output/2
DRIVER=org.dataalgorithms.chap29.combinesmallfilesbybuckets.WordCountDriverWithConsolidator
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT