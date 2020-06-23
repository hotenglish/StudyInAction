import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


with open(filename) as f:
    while True:
        line = f.readline()
        if not line: break
        process(line)