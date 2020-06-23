def print_params(*params):
    print(params)


def print_params_2(title, *params):
    print(title)
    print(params)


print_params('Testing')
print_params(1, 2, 3)
print_params_2('Params:', 1, 2, 3)
print_params_2('Nothing:')


# print_params_2('Hmn...', something=42)
# cause exception


def print_params_3(**params):
    print(params)


print_params_3(x=1, y=2, z=3)


def print_params_4(x, y, z=3, *pospar, **keypar):
    print(x, y, z)
    print(pospar)
    print(keypar)


print_params_4(1, 2, 3, 4, 5, 6, 7, foo=1, bar=2)
print_params_4(1, 2)

print('---------------------------------')


def lookup(data, label, name):
    return data[label].get(name)


def init(data):
    data['first'] = {}
    data['middle'] = {}
    data['last'] = {}


def store(data, *full_names):
    for full_name in full_names:
        names = full_name.split()
        if len(names) == 2: names.insert(1, '')
        labels = 'first', 'middle', 'last'
        for label, name in zip(labels, names):
            people = lookup(data, label, name)
            if people:
                people.append(full_name)
            else:
                data[label][name] = [full_name]


d = {}
init(d)
store(d, 'Han solo')

store(d, 'Luke Skywalker', 'Anakin Skywalker')
print(lookup(d, 'last', 'Skywalker'))
