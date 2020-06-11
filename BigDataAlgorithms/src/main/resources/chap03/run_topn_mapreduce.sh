APP_JAR=BigDataAlgorithms-1.0.jar

#
# phase 1: AggregateByKeyDriver
#
INPUT=/chap03/top10/input
OUTPUT=/chap03/output/1
Driver=org.dataalgorithms.chap03.mapreduce.AggregateByKeyDriver
hadoop jar $APP_JAR $Driver $INPUT $OUTPUT

#
# Phase 2: Run TopN
#
INPUT=/chap03/output/1
OUTPUT=/chap03/output/final
Driver=org.dataalgorithms.chap03.mapreduce.TopNDriver
N=3
hadoop jar $APP_JAR $Driver $N $INPUT $OUTPUT
