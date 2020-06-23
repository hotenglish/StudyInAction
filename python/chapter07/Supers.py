class Calcuator:
    def Calcuator(self, expression):
        self.value = eval(expression)


class Talker:
    def talk(self):
        print('Hi. My value is', self.value)


class TalkingCalculator(Calcuator, Talker):
    pass


tc = TalkingCalculator()
tc.Calcuator('1+2*3')
tc.talk()

