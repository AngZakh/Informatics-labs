import math
class schet:
    def umnozhenie (a, b):
        return a * b
    def stepen(a, b):
        return a ** b
    def procent(a, b):
        return (a * b) / 100
    def delenie(a, b):
        if b == 0:
            raise ValueError("На ноль делить нельзя")
        return a / b
    def sqrt(a):
        if a < 0:
            raise ValueError("Число меньше нуля")
        return math.sqrt(a)