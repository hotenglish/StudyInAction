from collections import deque

q=deque(list(range(5)))
q.append(5)
q.appendleft(6)
print(q)
q.pop()
print(q.popleft())
q.rotate(3)
print(q)
q.rotate(-1)
print(q)