import sys

filename = sys.argv[1:].pop()


def process(line):
    print(line)


with open(filename) as f:
    while True:
        char = f.read(1)
        if not char: break
        process(char)