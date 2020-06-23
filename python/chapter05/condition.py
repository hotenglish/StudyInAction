name = input('what is your name?')
if name.endswith('Gumby'):
    print('Hello. Mr. Gumby')
else:
    print('Hello. stranger')

num = int(input('Enter a number:'))
if num > 0:
    print('The number is positive')
elif num < 0:
    print('The number is negative')
else:
    print('The number is zero')

name = input('what is your name?')
if name.endswith('Gumby'):
    if name.startswith('Mr.'):
        print('Hello, Mr. Gumby')
    elif name.startswith('Mrs.'):
        print('Hello, Mrs. Gumby')
    else:
        print('Hello, Gumby')
else:
    print('Hello, stranger')
