APP_JAR=BigDataAlgorithms-1.0.jar
ttesthome=/usr/local/package
prog=org.dataalgorithms.chap23.sparkwithlambda.AllVersusAllCorrelation
reference=r2
biomarkers=/biomarkers/biomarkers.txt
LIB=$ttesthome/lib
EXTRA_JARS=$LIB/commons-math-2.1.jar,$LIB/commons-math3-3.4.1.jar

spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 3 \
    --driver-memory 2g \
    --executor-memory 2g \
    --executor-cores 3 \
    --jars $EXTRA_JARS \
    $APP_JAR $reference $biomarkers