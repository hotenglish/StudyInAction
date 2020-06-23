print('With a moo-moo here, and a moo-moo there.'.find('moo'))
title = "Monty Python's Flying Circus"
print(title.find('Monty'))
print(title.find('Python'))
print(title.find('Flying'))
print(title.find('Zirquss'))

subject = '$$$ Get rich now!!! $$$'
print(subject.find('$$$'))
print(subject.find('$$$', 1))
print(subject.find('!!!'))
print(subject.find('!!!', 0, 16))