d = {}
d.setdefault('name', 'N/A')
print(d)
d['name'] = 'Gumby'
d.setdefault('name', 'N/A')
print(d)

d={}
print(d.setdefault('name'))
print(d)