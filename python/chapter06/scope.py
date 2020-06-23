x = 1
scope = vars()
print(scope['x'])
scope['x'] += 1
print(x)


def foo(): x = 42


foo()
print(x)


def output(x):print(x)


y = 2
output(y)

x = 1
def change_global():
    global x
    x = x + 1


change_global()
print(x)