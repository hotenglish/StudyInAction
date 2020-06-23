words = ['this', 'is', 'an', 'ex', 'parrot']
for word in words:
    print(word)

numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

for number in numbers:
    print(number)

for number in range(1, 101):
    print(number)

d = {'x': 1, 'y': 2, 'z': 3}
for key in d:
    print(key, 'corresponds to', d[key])

for key, value in d.items():
    print(key, 'corresponds to', value)
