def factorial(n):
    if n == 1:
        return 1
    else:
        return n * factorial(n - 1)


def power(x, n):
    result = 1
    for i in range(n):
        result *= x
    return result


def power_recursion(x, n):
    if n == 0:
        return 1
    else:
        return x * power_recursion(x, n - 1)


print(power(2, 3))
print(factorial(3))
