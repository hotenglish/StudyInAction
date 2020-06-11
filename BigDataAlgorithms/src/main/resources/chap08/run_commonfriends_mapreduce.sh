export APP_JAR=BigDataAlgorithms-1.0.jar
INPUT=/chap08/input
OUTPUT=/chap08/output
DRIVER=org.dataalgorithms.chap08.mapreduce.CommonFriendsDriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT


