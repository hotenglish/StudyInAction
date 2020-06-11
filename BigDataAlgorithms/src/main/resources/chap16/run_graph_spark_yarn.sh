APP_JAR=BigDataAlgorithms-1.0.jar
prog=org.dataalgorithms.chap16.spark.CountTrianglesWithLambda
INPUT=/chap16/input/sample_graph.txt
OUTPUT=/chap16/output

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 3 \
    --driver-memory 2g \
    --executor-memory 2g \
    --executor-cores 3 \
    $APP_JAR $INPUT $OUTPUT