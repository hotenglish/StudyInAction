print([x * x for x in range(10)])

print([x * x for x in range(10) if x % 3 == 0])

print([(x * y) for x in range(3) for y in range(3)])

result = []
for x in range(3):
    for y in range(3):
        result.append((x, y))
print(result)

girls = ['alice', 'bernice', 'clarice']
boys = ['chris', 'arnold', 'bob']
print([b + '+' + g for b in boys for g in girls if b[0] == g[0]])
