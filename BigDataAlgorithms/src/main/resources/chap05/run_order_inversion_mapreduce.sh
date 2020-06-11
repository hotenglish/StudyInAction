export BOOK_HOME=/home/data-algorithms-book
export APP_JAR=$BOOK_HOME/dist/data_algorithms_book.jar
INPUT=/chap05/input/order_inversion.txt
OUTPUT=/chap05/output
hadoop fs -rm -r $OUTPUT
DRIVER=org.dataalgorithms.chap05.mapreduce.RelativeFrequencyDriver
hadoop jar $APP_JAR $DRIVER $INPUT $OUTPUT