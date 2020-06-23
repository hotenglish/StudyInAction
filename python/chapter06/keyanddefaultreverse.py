def hello_1(greeting, name):
    print('%s,%s!' % (greeting, name))


def hello_2(greeting, name):
    print('%s,%s!' % (name, greeting))


hello_1('Hello', 'world')
hello_2('Hello', 'world')

hello_1(greeting='Hello', name='world')
hello_1(name='world', greeting='Hello')
hello_2(greeting='Hello', name='world')


# store('Mr Bransample',10,20,13,5)

print('-------------------------------------')

def hello_4(name, greeting='Hello', punctuation='!'):
    print('%s,%s%s' % (greeting, name, punctuation))

hello_4('Mars')
hello_4('Mars','Howdy')
hello_4('Mars','Howdy','...')
hello_4('Mars',punctuation='.')
hello_4('Mars',greeting='Top of the morning to ya')
