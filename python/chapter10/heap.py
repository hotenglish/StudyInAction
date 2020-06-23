from heapq import *
from random import shuffle

data = list(range(10))
shuffle(data)
heap = []
for n in data:
    heappush(heap, n)

print(heap)

heappush(heap, 0.5)

print(heap)

print(heappop(heap))

print(heappop(heap))

print(heappop(heap))

heap = [5, 8, 0, 3, 6, 7, 9, 1, 4, 2]
heapify(heap)
print(heap)