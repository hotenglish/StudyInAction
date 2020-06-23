nested = [[1, 2], [3, 4], [5]]


def flatten(nested):
    for sublist in nested:
        for element in sublist:
            yield element


for num in flatten(nested):
    print(num)

print("------------------------")

print(list(flatten(nested)))

print("------------------------")

nested2 = [[[1], 2], 3, 4, [5, [6, 7]], 8]


def flatten2(nested):
    try:
        for sublist in nested:
            for element in flatten2(sublist):
                yield element
    except TypeError:
        yield nested


print(list(flatten2(nested2)))

print("------------------------")

nested3 = ['foo', ['bar', ['baz']]]


def flatten3(nested):
    try:
        try:
            nested + ''
        except TypeError:
            pass
        else:
            raise TypeError
        for sublist in nested:
            for element in flatten3(sublist):
                yield element
    except TypeError:
        yield nested


print(list(flatten3(nested3)))

print("------------------------")

nested4 = ['foo', ['bar', ['baz']]]


def flatten4(nested):
    result = []
    try:
        try:
            nested + ''
        except TypeError:
            pass
        else:
            raise TypeError
        for sublist in nested:
            for element in flatten4(sublist):
                result.append(element)
    except TypeError:
        result.append(nested)
    return result


print(list(flatten4(nested4)))


def repeater(value):
    while True:
        new = (yield value)
        if new is not None: value = new


r = repeater(42)
r.__next__()
print(r.send("Hello world!"))
