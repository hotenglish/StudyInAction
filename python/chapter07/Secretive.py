class Secretive:

    def __inaccessible(self):
        print("Bet you can't see me...")

    def accessible(self):
        print("The secret message is:")
        self._inaccessible()


s=Secretive()
#s.__accessible()
# cause exception
s.accessible()
s._Secretive_.inaccessible()