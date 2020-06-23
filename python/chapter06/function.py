import math

x = 1
y = math.sqrt
print(hasattr(y, '__call__'))


def hello(name):
    return 'Hello,' + name + '!'


print(hello('world'))
print(hello('Gumby'))


def fibs(num):
    result = [0, 1]
    for i in range(num - 2):
        result.append(result[-2] + result[-1])
    return result


print(fibs(10))
print(fibs(15))


def square(x):
    'Calculates the square of the number x.'
    return x * x


print(square.__doc__)
help(square)


def test():
    print('This is printed')
    return
    print('This is not')


x = test()

print(x)


def try_to_change(n):
    n = 'Mr. Gumby'


name = 'Mrs. Entity'
try_to_change(name)
print(name)


def change(n):
    n[0] = 'Mr. Gumbly'

print("------------------------------------------")
names = ['Mrs. Entity', 'Mrs. Thing']
change(names)
print(names)

names = ['Mrs. Entity', 'Mrs. Thing']
n = names[:]
print(n is names)
print(n == names)
n[0] = 'Mr. Gumbly'
print(n)
print(names)
