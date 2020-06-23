import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


with open(filename) as f:
    for line in f.readlines():
        process(line)