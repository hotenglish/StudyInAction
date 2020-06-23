from random import *

values = list(range(1, 11)) + 'Jack Queen King'.split()
suits = 'diamonds clubs hearts spades'.split()
deck = ['%s of %s' % (v, s) for v in values for s in suits]
print(deck[:12])

shuffle(deck)
print(deck[:12])