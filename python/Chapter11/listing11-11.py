
import fileinput
import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


for line in fileinput.input(filename):
    process(line)