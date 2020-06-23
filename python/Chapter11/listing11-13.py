import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


for line in open(filename):
    process(line)
