import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


with open(filename) as f:
    char = f.read(1)
    while char:
        process(char)
        char = f.read(1)