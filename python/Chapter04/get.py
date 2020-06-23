d = {}
# print(d['name'])

print(d.get('name'))

print(d.get('name', 'N/A'))

d['name'] = 'Eric'
print(d.get('name'))
