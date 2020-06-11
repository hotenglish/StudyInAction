export BOOK_HOME=/usr/local/package
export APP_JAR=$BOOK_HOME/BigDataAlgorithms-1.0.jar
NEIGHBOR_WINDOW=2
USERS_INPUT=/chap05/input/order_inversion.txt
TRANSCATIONS_INPUT=/chap05/output/spark/oiresult.txt

prog=org.dataalgorithms.chap05.spark.RelativeFrequency
$SPARK_HOME/bin/spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 2 \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 2 \
    $APP_JAR $NEIGHBOR_WINDOW $USERS_INPUT $TRANSCATIONS_INPUT
