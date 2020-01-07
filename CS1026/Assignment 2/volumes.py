### imports math at the beginning of the program in order to use the math.pi function in the volume of epllipsoids
import math

### the following functions compute their respective shapes volume based on the parameters given and returns the volume
def cube(side):
    volume = side**3

    return volume

def pyramid(base,height):
    volume = (1/3)*(base**2)*(height)

    return volume

def ellipsoid(r1,r2,r3):
    volume = ((4/3)*math.pi)*r1*r2*r3

    return volume
