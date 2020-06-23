def add(x, y): return x + y


params = (1, 2)


print(add(*params))

print("---------------------------------")

params = {'name': 'Sir Robin', 'greeting': 'Well met'}


def hello_3(greeting='Hello', name='world'):
    print('%s,%s!' % (greeting, name))


hello_3(**params)

print("---------------------------------")


def with_stars(**kwds):
    print(kwds['name'], 'is', kwds['age'], 'years old')


def without_stars(kwds):
    print(kwds['name'], 'is', kwds['age'], 'years old')


args = {'name': 'Mr. Gumby', 'age': 42}
with_stars(**args)
without_stars(args)
