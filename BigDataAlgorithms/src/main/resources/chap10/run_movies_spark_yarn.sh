APP_JAR=BigDataAlgorithms-1.0.jar
prog=org.dataalgorithms.chap10.spark.MovieRecommendationsWithJoin
INPUT=/chap10/input/movies.txt

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 3 \
    --driver-memory 2g \
    --executor-memory 2g \
    --executor-cores 3 \
    $APP_JAR $INPUT