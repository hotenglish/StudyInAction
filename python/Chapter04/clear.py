d = {}
d['name'] = 'Gumby'
d['age'] = 42
print(d)

returned_value = d.clear()
print(d)

x = {}
y = x
x['key'] = 'value'
print(y)
x = {}
print(y)

print('--------------------------------------------------')

x = {}
y = x
x['key'] = 'value'
print(y)
x.clear()
print(y)