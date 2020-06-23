names = ['anne', 'beth', 'george', 'damon']
ages = [12, 45, 32, 102]

for i in range(len(names)):
    print(names[i], 'is', ages[i], 'years old')

for name, age in zip(names, ages):
    print(names[i], 'is', ages[i], 'years old')

strings = "xxx test replace."

for index, string in enumerate(strings):
    if 'xxx' in string:
        strings[index] = 'els'

print(sorted([4, 3, 6, 8, 3]))

print(sorted('Hello, world!'))

print(list(reversed('Hello, world!')))

print(''.join(reversed('Hello, world!')))