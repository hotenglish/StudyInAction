#!/bin/bash

export BOOK_HOME=/usr/local/package
export APP_JAR=$BOOK_HOME/BigDataAlgorithms-1.0.jar
USERS_INPUT=/chap04/input/users.txt
TRANSCATIONS_INPUT=/chap04/input/transcations.txt

prog=org.dataalgorithms.chap04.sparkwithlambda.SparkLeftOuterJoin
$SPARK_HOME/bin/spark-submit --class $prog \
    --master yarn \
    --deploy-mode cluster \
    --num-executors 2 \
    --driver-memory 2g \
    --executor-memory 1g \
    --executor-cores 2 \
    $APP_JAR $USERS_INPUT $TRANSCATIONS_INPUT
