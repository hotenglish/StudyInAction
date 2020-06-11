APP_JAR=BigDataAlgorithms-1.0.jar
prog=org.dataalgorithms.chap14.sparkwithlambda.NaiveBayesClassifierBuilder
INPUT=/chap14/naivebayes/training_data.txt

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 12 \
    --driver-memory 3g \
    --executor-memory 7g \
    --executor-cores 12 \
    $APP_JAR $INPUT