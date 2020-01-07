### Determines exact value of trig functions
from tkinter import *
import tkinter as tk
import math
import sys
from fractions import Fraction

def trig(a):

    try:
        trig1 = [] 
        
        special = [15,30,45,60,75,90,105,120,135]

        sindict = {'0':'0','15':'((√3-1)√2/)/(4)','30':'1/2', '45':'(√2)/2', '60':'(√3)/2', '75':'(√2(1+√3))/(4)', '90':'1', '105':'(√2(1+√3))/(4)','120':'(√3)/2', '135':'(√2)/2', '150':'1/2','165':'(√2(√3-1))/(4)','180':'1/2','195':'(√2(- (√3-1)))/(4)', '210':'-1/2', '225':'- (√2)/2', '240':'- (√3)/2','255':'√2(- (1+√3))/(4)', '270':'-1','285':'√2(-(1+√3))/(4)', '300': '- (√3)/2', '315':'- (√2)/2', '330':'-1/2', '345':'√2(- (√3-1))/(4)','360':'0'}
        cosdict = {'0':'1','15':'(√2(1+√3))/(4)','30':'(√3)/2', '45':'(√2)/2', '60':'1/2','75':'(√2(√3-1))/(4)', '90':'0', '105':'√2(- (√3-1))/(4)', '120':'-1/2', '135':'- (√2)/2', '150':'- (√3)/2','165':'- (1+√3)/(2√2)', '180':'-1', '195':'(√2(- (1+√3))/(4)', '210':'- (√3)/2', '225':'- (√2)/2', '240':'-1/2', '255':'(√2(- (√3-1))/(4)','270':'0','285':'(√2(√3)-1))/(4)', '300': '1/2', '315':'(√2)/2', '330':'(√3)/2','345': '(√2(1+√3))/(4)', '360':'1'}
        tandict = {'0':'0','15':'2-√3','30':'(√3)/3', '45':'1', '60':'√3', '75':'2+√3','90':'undefined', '105':'- 2-√3', '120':'-√3', '135':'-1', '150':'- (√3)/3','165':'√3-2', '180':'0','195':'2-√3', '210':'- (√3)/3', '225':'1', '240':'√3','255':'2+√3', '270':'undefined', '285':'-2-√3', '300': '- √3', '315':'-1', '330':'- (√3)/3','345':'√3-2', '360':'0'}

        equation = a
        minus = len(equation)-1
        function = equation[0:3]
        numbers = equation[4:minus]   
        pi1 = False
        undef = False
        dvd = False
        subtract = False

        for i in range(len(numbers)-1):
            pie = numbers[i]+numbers[i+1]
            if pie == 'pi':
                begin = i
                end = i+3
                pi1 = True
            elif numbers[i] == '/':
                dvd = True
                
        if pi1 == True:
            if begin == 0:
                if len(numbers) == 2:
                    angle = math.pi
                else:
                    angle = math.pi/(int(equation[7:minus]))
                    define = 'pi/'+(equation[7:minus])
            else:
                if dvd == True:
                    a = int(numbers[0:begin])
                    b = int(numbers[end:minus])
                    angle = ((a*math.pi)/b)
                    if function == 'tan':
                        check = str(Fraction((a/b)))
                        a = check[0]
                        b = check[2]
                        if a == '1':
                            define = 'pi/'+str(b)
                        else:
                            define = str(a) +'pi/'+ str(b)
                    else:
                        define = ''
                else:
                    a = int(numbers[0:begin])
                    angle = (a*math.pi)
        else:
            angle = math.radians(int(numbers))
            define = ''

        if function == 'sin':
            approx = math.sin(angle)
        elif function == 'tan':
            if numbers == '90' or numbers == '270' or define == 'pi/2' or define == '3pi/2':
                approx = 'undefined'
                undef = True
            else:
                approx = math.tan(angle)
        elif function == 'cos':
            approx = math.cos(angle)
        else:
            approx = 'error'
                
        if pi1 == True:
            if function == 'sin' or function == 'cos':
                numbers2 = int(round(math.degrees(angle)))
            elif function == 'tan':
                if numbers == '90' or numbers == '270' or define == 'pi/2' or define == '3pi/2':
                    pass
                else:
                    numbers2 = int(round(math.degrees(angle)))
        else:
            numbers2 = int(numbers)

        try:
            de = (round(approx,4))
        except:
            de = (approx)
            
        if function == 'sin' or function == 'cos' or function == 'tan':
            if numbers2 % 15 == 0:
                if numbers2 <= 180:
                    if numbers2 <= 90:
                        subtract = True
                        for i in range(len(special)):
                            test = numbers2 - special[i]
                            if test <= 60:
                                specialnum = special[i]
                                numbernum = test
                                break
                    elif numbers2 > 90 and numbers2 <= 180:
                        for i in range(len(special)):
                            if function == 'tan':
                                test = 90 + special[i]
                                if test == numbers2:
                                    specialnum = special[i]
                                    numbernum = 90
                                    break
                            else:
                                test = 90 + special[i]
                                if test == numbers2:
                                    specialnum = special[i]
                                    numbernum = 90
                                    break            
                elif numbers2 > 180:
                    if numbers2 <= 270:
                        subtract = True
                        for i in range(len(special)):
                            test = numbers2 - special[i]
                            if test == 180:
                                specialnum = special[i]
                                numbernum = 180
                                break
                    elif numbers2 <= 360 and numbers2 > 270:
                        for i in range(len(special)):
                            test = 270 + special[i]
                            if test == numbers2:
                                specialnum = special[i]
                                numbernum = 270
                                break
        else:
            pass
        
        try:
            if subtract == False:
                if function == 'sin':
                    fe = ('('+sindict[str(specialnum)]+')*('+cosdict[str(numbernum)]+')-(',sindict[str(numbernum)]+')*('+cosdict[str(specialnum)],')')
                elif function == 'tan':
                    if numbers == '90' or numbers == '270' or define == 'pi/2' or define == '3pi/2':
                        fe = ''
                    else:
                        fe = ('('+tandict[str(specialnum)]+')-('+tandict[str(numbernum)]+')/1-(',tandict[str(specialnum)]+')*('+tandict[str(numbernum)]+')')    
                else:
                    fe = ('('+cosdict[str(specialnum)]+')*('+cosdict[str(numbernum)]+')-('+sindict[str(numbernum)]+')*('+sindict[str(specialnum)]+')')     
            else:
                if function == 'sin':
                     fe = ('('+sindict[str(specialnum)]+')*('+cosdict[str(numbernum)]+')+('+sindict[str(numbernum)]+')*('+cosdict[str(specialnum)]+')')    
                elif function == 'tan':
                    if numbers == '90' or numbers == '270' or define == 'pi/2' or define == '3pi/2':
                        fe = ''
                    else:
                         fe =('('+tandict[str(specialnum)]+')+('+tandict[str(numbernum)]+')/1+('+tandict[str(specialnum)]+')*('+tandict[str(numbernum)]+')')    
                else:
                   fe = ('('+cosdict[str(specialnum)]+')*('+cosdict[str(numbernum)]+')+('+sindict[str(numbernum)]+')*('+sindict[str(specialnum)]+')')
        except Exception as inst:
            fe = ''
            print(type(inst))
            print(inst.args)
            print(inst)
            print(numbers2,specialnum,numbernum)

        try:
            if function == 'sin':
                ex = (sindict[str(numbers2)])
            elif function == 'cos':
                ex = (cosdict[str(numbers2)])
            elif function == 'tan':
                ex = (tandict[str(numbers2)])
            else:
                ex = ('error')
        except:
            ex = ''

        trig1.append(fe)
        trig1.append(de)
        trig1.append(ex)
        
    except:
        trig1 = ['','error','error']

    return(trig1)

root = Tk()

#text
Label1 = Label(root, text = 'Enter in function in form funct(x) using pi as π for radians', fg = 'orange',bg = 'black',font= ('Courier',12))
Label1.pack()

#input
input1 = StringVar()
L2 = Entry(root, bd = 5,text = 'Enter Here', textvariable = input1, relief = tk.GROOVE, borderwidth = 0)
L2.pack()

#frame
topframe = Frame(root)
topframe.pack()
bottomframe = Frame(root)
bottomframe.pack(side = BOTTOM)
root['bg'] = 'black'

def del1(Label2,Label3,Label4,Label3_1,Label4_1):
    Label2.destroy()
    Label3.destroy()
    Label4.destroy()
    Label3_1.destroy()
    Label4_1.destroy()
    
    

def clicked():
    a = input1.get()
    b = trig(a)
    c = b[0]
    d = b[1]
    e = b[2]

    global Label2
    global Label3
    global Label3_1
    global Label4
    global Label4_1
    

    del1(Label2,Label3,Label4,Label3_1,Label4_1)
    
    Label2  = Label(root, text = (c), fg = 'orange',bg = 'black',font= ('Courier',12))
    Label2.pack()
    
    Label3_1 = Label(root, text = ('Decimal:'), fg = 'orange',bg = 'black',font= ('Courier',12))
    Label3_1.pack()
    Label3 = Label(root, text = (str(d)), fg = 'orange',bg = 'black',font= ('Courier',12))
    Label3.pack()

    Label4_1 = Label(root, text = ('Exact:'), fg = 'orange',bg = 'black',font= ('Courier',12))
    Label4_1.pack()
    Label4 = Label(root, text = (e), fg = 'orange',bg = 'black',font= ('Courier',12))
    Label4.pack()

button1 = Button(topframe,  text = 'Calculate', relief = tk.FLAT, overrelief = tk.SUNKEN, fg = 'orange', bg = 'black',font= ('Courier',12), borderwidth = 10, activebackground = 'black', activeforeground = 'orange', command = clicked)
button1.pack()

Label2  = Label(root, text = '_', fg = 'orange',bg = 'black',font= ('Courier',12))
Label2.pack()

Label3_1 = Label(root, text = ('Decimal:'), fg = 'orange',bg = 'black',font= ('Courier',12))
Label3_1.pack()
Label3 = Label(root, text = '_', fg = 'orange',bg = 'black',font= ('Courier',12))
Label3.pack()

Label4_1 = Label(root, text = ('Exact:'), fg = 'orange',bg = 'black',font= ('Courier',12))
Label4_1.pack()
Label4 = Label(root, text = '_', fg = 'orange',bg = 'black',font= ('Courier',12))
Label4.pack()




root.mainloop()
