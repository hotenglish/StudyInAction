print(set(range(10)))
print(set([0, 1, 2, 3, 0, 1, 2, 3, 4, 5]))
print(set(['fee', 'fie', 'foe']))
a = set([1, 2, 3])
b = set([2, 3, 4])
print(a.union(b))
print(a | b)

c = a & b
print(c.issubset(a))

c <= a

c >= a

print(a.intersection(b))
print(a & b)
print(a.difference(b))
print(a - b)
print(a.symmetric_difference(b))
print(a ^ b)
print(a.copy())
print(a.copy is a)

a=set()
b=set()