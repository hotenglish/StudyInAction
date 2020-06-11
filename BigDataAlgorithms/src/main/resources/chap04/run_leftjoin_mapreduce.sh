#!/bin/bash

export BOOK_HOME=/home/data-algorithms-book
export APP_JAR=$BOOK_HOME/dist/data_algorithms_book.jar

#
# phase 1: leftJoin
#
USERS_INPUT=/chap04/input/users.txt
TRANSCATIONS_INPUT=/chap04/input/transcations.txt
OUTPUT=/chap04/output
hadoop fs -rm -r $OUTPUT
LeftJoinDriver=org.dataalgorithms.chap04.mapreduce.LeftJoinDriver
hadoop jar $APP_JAR  $LeftJoinDriver $USERS_INPUT $TRANSCATIONS_INPUT $OUTPUT

#
# Phase 2: Run LocationCount
#
Leftfinal_INPUT=/chap04/output
Leftfinal_OUTPUT=/chap04/output/final
hadoop fs -rm -r $TopN_OUTPUT
LocationCountDriver=org.dataalgorithms.chap04.mapreduce.LocationCountDriver
hadoop jar $APP_JAR $LocationCountDriver $Leftfinal_INPUT $Leftfinal_OUTPUT