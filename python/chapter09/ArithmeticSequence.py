def checkIndex(key):
    if not isinstance(key, (int, int)): raise TypeError
    if key < 0: raise IndexError


class ArithmeticSequence:
    def __init__(self, start=0, step=1):
        self.start = start
        self.step = step
        self.changed = {}

    def __getitem__(self, key):
        """
        Get an item from the arithmetic sequence.
        """
        checkIndex(key)
        try:
            return self.changed[key]
        except KeyError:
            return self.start + key * self.step

    def __setitem__(self, key, value):
        checkIndex(key)
        self.changed[key] = value


s=ArithmeticSequence(1,2)
print(s[4])
s[4]=2
print(s[4])
print(s[5])

#cause exception
#print(s["four"])
#print(s[-42])