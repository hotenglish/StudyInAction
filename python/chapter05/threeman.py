scoundrel = {'age': 42, 'first name': 'Robin', 'last name': 'of Locksey'}
robin = scoundrel
print(scoundrel)
print(robin)
scoundrel = None
print(robin)
robin = None
print(robin)

# x = 1
# del x,
# print(x)

x = ["Hello", "world"]
y = x
y[1] = "Python"
print(x)

del x
print(y)

# del y
# print(y)

exec("print('Hello,world!')")

from math import sqrt
scope = {}
exec("sqrt = 1",scope)
print(int(sqrt(4)))
print(scope['sqrt'])
print(len(scope))
print(scope.keys())

print(eval(input("Enter an arithmetic expression:")))
