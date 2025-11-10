import math
class schet:
    def sum(a, b):
        return a + b
    def minus(a, b):
        return a - b
    def umnozhenie (a, b):
        return a * b
    def delenie(a, b):
        if b == 0:
            raise ValueError("На ноль делить нельзя")
        return a / b
    def sqrt(a):
        if a < 0:
            raise ValueError("Число меньше нуля")
        return math.sqrt(a)