print("foo" == "foo")
print("foo" == "bar")

x = y = [1, 2, 3]
z = [1, 2, 3]
print(x == y)
print(x == z)
print(x is y)
print(x is z)

x = [1, 2, 3]
y = [2, 4]
print(x is not y)

del x[2]
y[1] = 1
y.reverse()
print(x == y)
print(x is y)

name = input('what is your name?')
if 's' in name:
    print('your name contains the letter "s".')
else:
    print('Your name does not contain the letter "S".')

print("alpha" < "beta")

print('FnOrD'.lower() == 'Fnord'.lower())
print([1, 2] < [2, 1])
print([2, [1, 4]] < [2, [1, 5]])

number =int(input('Enter a number between 1 and 10:'))
if number <= 10:
    if number >= 1:
        print('Great!')
    else:
        print('Wrong!')
else:
    print('Wrong!')
