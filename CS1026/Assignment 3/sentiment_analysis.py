### function uses keyname to create a dictionary with they key as the keyword and the value as the words corresponding sentiment value
def keysget(keysname):
    ### opens file and creates list from its content before closing it
    keysfile = open(keysname,'r',encoding='utf-8',errors='ignore')

    keyvalueslist = [line.strip().split(',') for line in keysfile]

    keysfile.close()

    keyvalues = {}

    ### creates dictionary
    for value in keyvalueslist:
        keyvalues[value[0].lower()] = int(value[1])
        

    return keyvalues

### function uses tweetsname to create a list of the tweets and location data in a tuple
def tweetsget(tweetsname):
    ### opens file the for each line adds the long and lat for the tweet along with the words of the tweet into a tuple in a list before closing the file
    tweetsfile = open(tweetsname,'r',encoding='utf-8',errors='ignore')

    tweets = []
    
    for line in tweetsfile:
        line1 = line.strip().split(' ')
        tweets.append((line1[0][1:len(line1[0])-1],line1[1][0:len(line1[0])-1],line1[5::]))

    tweetsfile.close()

    return tweets

### function uses coordinates of the tweet to determine the timezone of the tweet
def timezone(cord):
    ### timezone border values
    easterntimey = (49.189787,24.660845)
    easterntimex = (-67.444574,-87.518395)

    centraltimey = (49.189787,24.660845)
    centraltimex = (-87.518395,-101.998892)

    mountaintimey = (49.189787,24.660845)
    mountaintimex = (-101.998892,-115.236428)

    pacifictimey = (49.189787,24.660845)
    pacifictimex = (-115.236428,-125.242264)

    ### tweet coordinates
    x = cord[1]
    y = cord[0]

    ### determiens tweets timezone based on timezone border values and tweet coordinates
    if x < easterntimex[0] and x >= easterntimex[1] and y <= easterntimey[0] and y >= easterntimey[1]:
        return 'eastern'

    elif x < centraltimex[0] and x >= centraltimex[1] and y <= centraltimey[0] and y >= centraltimey[1]:
        return 'central'

    elif x < mountaintimex[0] and x >= mountaintimex[1] and y <= mountaintimey[0] and y >= mountaintimey[1]:
        return 'mountain'

    elif x <= pacifictimex[0] and x >= pacifictimex[1] and y <= pacifictimey[0] and y >= pacifictimey[1]:
        return 'pacific'

    else:
        return ''
        
### main function that does the final calculations and calls the other functions
def compute_tweets(tweetsname, keysname):
    ### constants
    final = []
    
    counteastern = 0
    senteastern = []

    countcentral = 0
    sentcentral = []

    countmountain = 0
    sentmountain = []

    countpacific = 0
    sentpacific = []

    ### trys to find the files and if it fails returns a empty list
    try:
        tweets = tweetsget(tweetsname)
    except:
        return final

    try:    
        keys = keysget(keysname)
    except:
        return final
        
    ### for each word makes it lower and strips all non letters before it checks if it is in the dictionary and adds its sentiment value to the constant after checking the whole tweet
    for tweet in tweets:
        timezonecord = (float(tweet[0]),float(tweet[1][0:len(tweet[1])-1]))
        sentiment = 0
        count = 0
        words = tweet[2]

        for word in words:
            word = word.lower()
            newword = []
            
            if word.isalpha() != True:
                for letter in word:
                    if letter.isalpha() == True:
                        newword.append(letter)

                word = ''.join(map(str,newword))

            if word in keys:
                sentiment += keys[word]
                count += 1

        ### ensures tweet contained keywords before adding it to the count for the timezone
        if sentiment != 0:
            tweettimezone = timezone(timezonecord)

            if tweettimezone == 'eastern':
                counteastern += 1
                senteastern.append(sentiment/count)

            elif tweettimezone == 'central':
                countcentral += 1
                sentcentral.append(sentiment/count)

            elif tweettimezone == 'mountain':
                countmountain += 1
                sentmountain.append(sentiment/count)

            elif tweettimezone == 'pacific':
                countpacific += 1
                sentpacific.append(sentiment/count)
                
    ### makes the final calculation and returns the avg and count but also checks if the value is 0 so that in that case no data and 0 can be returned
    if counteastern != 0:
        avgeastern = round((sum(senteastern)/counteastern),3)
        final.append((avgeastern,counteastern))

    else:
        final.append(('No Data',0))

    if countcentral != 0: 
        avgcentral = round((sum(sentcentral)/countcentral),3)
        final.append((avgcentral,countcentral))

    else:
        final.append(('No Data',0))

    if countmountain != 0:
        avgmountain = round((sum(sentmountain)/countmountain),3)
        final.append((avgmountain,countmountain))

    else:
        final.append(('No Data',0))
                     
    if countpacific != 0:  
        avgpacific = round((sum(sentpacific)/countpacific),3)
        final.append((avgpacific,countpacific))

    else:
        final.append(('No Data',0))

    return final
            
            
                
        
    

