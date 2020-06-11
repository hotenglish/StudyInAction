export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/chap17/input/sample_kmer.txt
OUTPUT=/chap17/output/2
DRIVER=org.dataalgorithms.chap17.mapreduce.KmerCountDriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT


