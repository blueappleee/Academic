def quitprogram(cubes,pyramids,ellipsoids):
### prints the final statements based on if there have been any previous inputs for each shape and the exits the program 
    print('You have reached the end of your sesson,')
    print('The volumes calculated for each shape are:')

    #checks for previous inputs and makes the final statement according to each shape
    if len(cubes) == 0:
        cube = 'No shapes entered'

    else:
        cube = (str(sorted(cubes)))[1:-1]

    if len(pyramids) == 0:
        pyramid = 'No shapes entered'

    else:
        pyramid = (str(sorted(pyramids)))[1:-1]

    if len(ellipsoids) == 0:
        ellipsoid = 'No shapes entered'

    else:
        ellipsoid = (str(sorted(ellipsoids)))[1:-1]
    
    print('Cube:',cube)
    print('Pyramid:',pyramid)
    print('Ellipsoid:',ellipsoid)

    exit()
        
def errorshape(cubes,pyramids,ellipsoids):
### reacalls the inp function to retake input if a invalid input is entered
    print('Invalid shape entered')
    return inp(cubes,pyramids,ellipsoids)

def inp(cubes,pyramids,ellipsoids):
    shape = input('Please enter a shape: ')

    ### this if statement separates the input into lengths to deal with each length in an efficient way
    if len(shape) == 1:
        if shape.lower() == 'c':
            shapeinput = 'cube'
            
        elif shape.lower() == 'p':
            shapeinput = 'pyramid'
            
        elif shape.lower() == 'e':
            shapeinput = 'ellipsoid'
            
        elif shape.lower() == 'q':
            quitprogram(cubes,pyramids,ellipsoids)
            
        else:
            shapeinput = errorshape(cubes,pyramids,ellipsoids)

    else:
        ### this loop takes each part of the string and makes it in lower case and then rebuilds the string
        ### this is to make it easier to deal with the possibility of any letters potentially being uppercase no matter where the uppercase letters are in the input
        shapesplit = []
        for i in range(len(shape)):
            shapesplit.append(shape[i].lower())

        shapelower = ''.join(map(str, shapesplit))

        if shapelower == 'cube' or shapelower == 'pyramid' or shapelower == 'ellipsoid':
            shapeinput = shapelower

        elif shapelower == 'quit':
            quitprogram(cubes,pyramids,ellipsoids)

        else:
            shapeinput = errorshape(cubes,pyramids,ellipsoids)

    return shapeinput

        
def volcalc(shape):
### the following statements take parameter inputs based on the shape value entered and calculates the volume by calling the volumes module and returns the volume along with parameters in a tuple
    if shape == 'cube':
        side = float(input('Please input side length: '))
        volume = volumes.cube(side)

        return (side,volume)
        
    elif shape == 'pyramid':
        base = float(input('Please input base: '))
        height = float(input('Please input height: '))
        volume = volumes.pyramid(base,height)

        return (base,height,volume)

    else:
        r1 = float(input('Please input radius 1: '))
        r2 = float(input('Please input radius 2: '))
        r3 = float(input('Please input radius 3: '))
        volume = volumes.ellipsoid(r1,r2,r3)

        return (r1,r2,r3,volume)
        

def main(cubes,pyramids,ellipsoids):
### calls functions to take input and then to calculate volume based on those inputs
    shape = inp(cubes,pyramids,ellipsoids)
    vol = volcalc(shape)

### the following statements print the volume of the shape and the parameters given and are organized based on shape due to different tuple lengths for each along with different parameter names
    if shape == 'cube':
        print('The volume of a cube with side length',vol[0],'is: ',"%.3f"%vol[1])
        cubes.append(float("%.3f"%vol[1]))

    elif shape == 'pyramid':
        print('The volume of a pyramid with base',vol[0],'and height',vol[1],'is: ',"%.3f"%vol[2])
        pyramids.append(float("%.3f"%vol[2]))
        
    else:
        print('The volume of a ellipsoid with raduis 1',vol[0],'and radius 2',vol[1],'and raduis 3',vol[2], 'is: ',"%.3f"%vol[3])
        ellipsoids.append(float("%.3f"%vol[3]))

### reacalls itself to loop through with recursion and calculate further volumes
    main(cubes,pyramids,ellipsoids)

### init imports the necessary modules and sets the constants before calling the rest of the program to start
def init():
    global volumes
    import volumes

    cubes = []
    pyramids = []
    ellipsoids = []

    main(cubes,pyramids,ellipsoids)

### calls main to begin the program
init()
