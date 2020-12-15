### Assignment 1 Computer Science 1026A 001
### Program by Connor Ciavarella
### Student Number:
### Program uses python to take input from a user describing their beverage request and outputs the beverage details along with the cost including taxes

### all functions starting with invalid are built to handle invalid inputs by returning a invalid input statement along with ending the program
def invalid():
    print('Invalid input entered')
    exit()

def invalids():
    print('Invalid size entered')
    exit()

def invalidb():
    print('Invalid beverage entered')
    exit()

def invalidf():
    print('Invalid flavor entered')
    exit()

### function that takes the users name and checking the length of the input to ensure a name was entered
def name():
    customer = input('Please enter your name: ')
    if len(customer) == 0:
        invalid()

    return customer

### beveragetype takes input from the user and determines their beverage type
def beveragetype():
    beverage = input('Enter beverage type: ')

    #this if statement separates the input into lengths to deal with each length in an efficient way
    if len(beverage) == 0:
        invalidb()

    elif len(beverage) == 1:
        if beverage == 'C' or beverage == 'c':
            drink = 'coffee'

        elif beverage == 'T' or beverage == 't':
           drink = 'tea'

        else:
            invalidb()

    else:
        #this loop takes each part of the string and makes it in lower case and then rebuilds the string
        #this is to make it easier to deal with the possibility of any letters potentially being uppercase no matter where the uppercase letters are in the input
        beveragesplit = []
        for i in range(len(beverage)):
            beveragesplit.append(beverage[i].lower())

        beveragelower = ''.join(map(str, beveragesplit))

        if beveragelower == 'coffee':
            drink = beveragelower

        elif beveragelower == 'tea':
           drink = beveragelower

        else:
            invalidb()

    return drink

### beveragesize takes input from the user and determines the size of beverage the user would like
def beveragesize():
    size = input('Enter beverage size: ')

    #this if statement separates the input into lengths to deal with each length in an efficient way
    if len(size) == 0:
        invalids()

    elif len(size) == 1:
        if size == 'S' or size == 's':
            drinksize = 'small'

        elif size == 'M' or size == 'm':
            drinksize = 'medium'

        elif size == 'L' or size == 'l':
            drinksize = 'large'

        else:
            invalids()

    else:
        #this loop takes each part of the string and makes it in lower case and then rebuilds the string
        #this is to make it easier to deal with the possibility of any letters potentially being uppercase no matter where the uppercase letters are in the input
        sizesplit = []
        for j in range(len(size)):
            sizesplit.append(size[j].lower())

        sizelower = ''.join(map(str, sizesplit))

        if sizelower == 'small':
            drinksize = sizelower

        elif sizelower == 'medium':
           drinksize = sizelower

        elif sizelower == 'large':
           drinksize = sizelower

        else:
            invalids()

    return drinksize

### flavoring takes input from the user and determines the flavoring that is being added to the beverage based on that input and the beverage type
def flavoring(beveragetype):
    flavor = input('Enter flavoring: ')

    #this if statement separates the input into lengths to deal with each length in an efficient way
    if len(flavor.strip()) == 0:
        addflavor = 'no flavoring'

    elif len(flavor) == 1:
        if beveragetype == 'coffee':
            if flavor == 'V' or flavor == 'v':
                addflavor = 'vanilla'

            elif flavor == 'C' or flavor == 'c':
                addflavor = 'chocolate'

            else:
                invalidf()

        elif beveragetype == 'tea':
            if flavor == 'L' or flavor == 'l':
                addflavor = 'lemon'

            elif flavor == 'M' or flavor == 'm':
                addflavor = 'mint'

            else:
                invalidf()

    else:
        #this loop takes each part of the string and makes it in lower case and then rebuilds the string
        #this is to make it easier to deal with the possibility of any letters potentially being uppercase no matter where the uppercase letters are in the input
        flavorsplit = []
        for k in range(len(flavor)):
            flavorsplit.append(flavor[k].lower())

        flavorlower = ''.join(map(str, flavorsplit))

        if flavorlower == 'none':
            addflavor = 'no flavoring'

        elif beveragetype == 'coffee':
            if flavorlower == 'vanilla':
                addflavor = flavorlower

            elif flavorlower == 'chocolate':
                addflavor = flavorlower

            else:
                invalidf()

        elif beveragetype == 'tea':
            if flavorlower == 'lemon':
                addflavor = flavorlower

            elif flavorlower == 'mint':
                addflavor = flavorlower

            else:
                invalidf()

        else:
            invalidf()

    return addflavor

### statement function calls the other functions to get the details about the beverage and computes the final cost including tax
###  it then prints a statement based on the beverage details and the final cost
def statement():

    custname = name()
    custbeverage = beveragetype()
    custsize = beveragesize()
    custflavor = flavoring(custbeverage)

    cost = 0

    if custsize == 'small':
        cost += 1.50

    elif custsize == 'medium':
        cost += 2.50

    else:
        cost += 3.25

    if custflavor == 'vanilla':
        cost += 0.25

    elif custflavor == 'chocolate':
        cost += 0.75

    elif custflavor == 'lemon':
        cost += 0.25

    elif custflavor == 'mint':
        cost += 0.50

    costtax = cost + (cost*0.13)

    #rounds the cost with tax to two decimal places
    costfinal = round(costtax,2)

    print('For ',custname,', a ',custsize,' ',custbeverage,', with ',custflavor,', cost: $',"%.2f"%costfinal,'.',sep='')

### calls the statement function which also calls the other functions and runs the program
statement()










