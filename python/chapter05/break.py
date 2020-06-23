from math import sqrt

for n in range(99, 0, -1):
    root = sqrt(n)
    print(root)
    if root == int(root):
        print(n)
        break

range(0, 10, 2)

word = 'dummy'

# while word:
#    word = input('Please enter a word:')
#    print('The word was ' + word)

# word = input('Please enter a word:')
# while word:
#    print('The word was ' + word)
#    word = input('Please enter a word:')

while True:
    word = input('Please enter a word:')
    if not word:
        break
    print('The word was ' + word)

for n in range(99, 81, -1):
    root = sqrt(n)
    print(root)
    if root == int(root):
        print(n)
        break
    else:
        print("Don't find it!")