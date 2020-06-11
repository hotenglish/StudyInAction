APP_JAR=BigDataAlgorithms-1.0.jar
prog=org.dataalgorithms.chap17.sparkwithlambda.Kmer
INPUT=/chap17/input/sample_kmer.txt
OUTPUT=/chap17/output
K=3
N=5

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 3 \
    --driver-memory 2g \
    --executor-memory 2g \
    --executor-cores 3 \
    $APP_JAR $INPUT $K $N