APP_JAR=BigDataAlgorithms-1.0.jar
prog=org.dataalgorithms.chap08.sparkwithlambda.FindCommonFriends
INPUT=/chap08/input/file3.txt

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 3 \
    --driver-memory 2g \
    --executor-memory 2g \
    --executor-cores 3 \
    $APP_JAR $INPUT