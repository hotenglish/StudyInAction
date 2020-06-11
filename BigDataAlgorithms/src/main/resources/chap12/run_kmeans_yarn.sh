export BOOK_HOME=/usr/local/package
export APP_JAR=BigDataAlgorithms-1.0.jar
K=3
inputfile=/chap12/input/kmeans_input_file.txt
prog=org.dataalgorithms.chap12.spark.JavaKMeans
iterations=10

park-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 2 \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 2 \
    $APP_JAR $inputfile $K $iterations