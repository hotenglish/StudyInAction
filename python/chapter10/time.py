from random import *
from time import *

print(asctime())

date1 = (2008, 1, 1, 0, 0, 0, -1, -1, -1)
time1 = mktime(date1)
date2 = (2009, 1, 1, 0, 0, 0, -1, -1, -1)
time2 = mktime(date2)

random_time = uniform(time1, time2)

print(asctime(localtime(random_time)))

num = int(input('How many dice?'))
sides = int(input('How many sides per die?'))
sum = 0
for i in range(num): sum += randrange(sides) + 1
print('The result is ', sum)
