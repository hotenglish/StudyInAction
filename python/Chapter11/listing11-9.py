import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


with open(filename) as f:
    for char in f.read():
        process(char)