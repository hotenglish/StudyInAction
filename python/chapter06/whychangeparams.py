storage = {}
storage['first'] = {}
storage['middle'] = {}
storage['last'] = {}

me = 'Magnus Lie Hetland'
storage['first']['Magnus'] = [me]
storage['middle']['Lie'] = [me]
storage['last']['Hetland'] = [me]

print(storage['middle']['Lie'])

my_sister = 'Anne Lie HetLand'
storage['first'].setdefault('Anne', []).append(my_sister)
storage['middle'].setdefault('Lie', []).append(my_sister)
storage['last'].setdefault('Hetland', []).append(my_sister)
print(storage['first']['Anne'])
print(storage['middle']['Lie'])


def init(data):
    data['first'] = {}
    data['middle'] = {}
    data['last'] = {}


def lookup(data, label, name):
    return data[label].get(name)


print(lookup(storage, 'middle', 'Lie'))


def store(data, full_name):
    names = full_name.split()
    if len(names) == 2:
        names.insert(1, '')
    labels = 'first', 'middle', 'last'
    for label, name in zip(labels, names):
        people = lookup(data, label, name)
        if people:
            people.append(full_name)
        else:
            data[label][name] = [full_name]


MyNames = {}
init(MyNames)
store(MyNames, 'Magnus Lie Hetland')
lookup(MyNames, 'middle', 'Lie')


def inc(x): return x + 1


foo = 10
foo = inc(foo)
print(foo)


def inc(x): x[0]=x[0] + 1

foo = [10]
inc(foo)
print(foo)
