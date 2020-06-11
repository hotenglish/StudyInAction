export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/chap29/input
OUTPUT=/chap29/output/3
DRIVER=org.dataalgorithms.chap30.combinesmallfilesbybuckets.WordCountDriverWithoutConsolidator
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT