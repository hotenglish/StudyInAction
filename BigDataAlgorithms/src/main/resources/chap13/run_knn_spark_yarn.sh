#
k=2
d=28
APP_JAR=BigDataAlgorithms-1.0.jar
R="/chap13/input/R.txt"
S="/chap13/input/S.txt"
OUTPUT="/chap13/spark/output2"
prog=org.dataalgorithms.chap13.sparkwithlambda.kNN

#
spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 2 \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 2 \
    $APP_JAR $k $d $R $S $OUTPUT