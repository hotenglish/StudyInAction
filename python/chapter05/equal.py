x, y, z = 1, 2, 3
print(x, y, z)

x, y = y, x
print(x, y, z)

values = 1, 2, 3
x, y, z = values
print(x)

scoundrel = {'name': 'Robin', 'girlfriend': 'Marion'}
key, value = scoundrel.popitem()
print(key, value)


# x,y,z=1,2
# x,y,z=1,2,3,4
# Aboved two lines cause exceptions.
def somefunction():
    return 3


x = y = somefunction()
print(x)

y = somefunction()
x = y
print(x)

y = somefunction()
x = somefunction()
print(x)

x = 2
x += 1
x *= 2
print(x)

fnord = 'foo'
fnord += 'bar'
fnord *= 2
print(fnord)
