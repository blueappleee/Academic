### imports the class Country
from country import *

### creates the class CountryCatalogue
class CountryCatalogue:
    ### initializes the instance of CountryCatalogue and takes the data file and continent file as parameters
    def __init__(self,datafile,continentfile):
        countryCat = {}
        cDictionary = {}

        ### takes the data from the file and converts it into two lists
        with open(datafile,'r') as datafile:
            data = [line.strip().split('|') for line in datafile]

        with open(continentfile,'r') as continentfile:
            continentdata = [line.strip().split(',') for line in continentfile]

        ### converts the two lists into dictionaries, one with the continents and one with Country Objects
        for k in range(len(continentdata)):
            if k != 0:
                cDictionary[continentdata[k][0]] = continentdata[k][1]

        for i in range(len(data)):
            if i != 0:
                countrycontinent = cDictionary[data[i][0]]
                pop = data[i][1].replace(',','')
                area = data[i][2].replace(',','')
            
                countryCat[data[i][0]] = Country(data[i][0],pop,area,countrycontinent)

        self.countryCat = countryCat
        self.cDictionary = cDictionary

    ### returns the country object of a given country
    def findCountry(self,cname):
        try:
            return self.countryCat[cname]
        except:
            return None

    ### sets the population of a given country object by calling the setPopulation method of the Country class
    def setPopulationOfCountry(self,cname,newpop):
        try:
            self.countryCat[cname].setPopulation(newpop)
            return True
        except:
            return False
    ### sets the area of a given country object by calling the setArea method of the Country class
    def setAreaOfCountry(self,cname,newarea):
        try:
            self.countryCat[cname].setArea(newarea)
            return True
        except:
            return False
        
    ### creates a new country object and adds it to the countryCat and cDictioinary after checking if it already is there
    def addCountry(self,cname,cpop,carea,ccontinent):
        if cname not in self.cDictionary and cname not in self.countryCat:
            self.cDictionary[cname] = ccontinent
            self.countryCat[cname] = Country(cname,cpop,carea,ccontinent)
            return True

        else:
            return False

    ### deletes a given country from both cDictionary and countryCat after checking if it is there
    def deleteCountry(self,cname):
        if cname in self.cDictionary and cname in self.countryCat:
            del self.cDictionary[cname]
            del self.countryCat[cname]

    ### prints the countrycatalogue
    def printCountryCatalogue(self):
        print(self.countryCat)

    ### returns a list of countries which the .continent matches the given continent by checking all of the country objects continents
    def getCountriesByContinent(self,ccontinent):
        cont = []
        for element in self.countryCat:
            if self.countryCat[element].continent == ccontinent:
                cont.append(self.countryCat[element])

        return cont

    ### returns a sorted list in greatest to least in terms of population of the the countries in the given continent but sets the default continent as '' in which case it returns all countries sorted
    def getCountriesByPopulation(self,ccontinent = ''):
        pops = []
        if ccontinent == '':
            for element in self.countryCat:
                pops.append((self.countryCat[element].name,self.countryCat[element].pop))

            ### sorts by the second element in the tuple and reverses to sort from greatest to least
            return sorted(pops, key=lambda x: int(x[1]),reverse=True)

        else:
            for element in self.countryCat:
                if self.countryCat[element].continent == ccontinent:
                    pops.append((self.countryCat[element].name,self.countryCat[element].pop))
                    
            ### sorts by the second element in the tuple and reverses to sort from greatest to least
            return sorted(pops, key=lambda x: int(x[1]),reverse=True)

    ### returns a sorted list in greatest to least in terms of area of the the countries in the given continent but sets the default continent as '' in which case it returns all countries sorted
    def getCountriesByArea(self,ccontinent = ''):
        areas = []
        if ccontinent == '':
            for element in self.countryCat:
                areas.append((self.countryCat[element].name,self.countryCat[element].area))

            ### sorts by the second element in the tuple and reverses to sort from greatest to least
            return sorted(areas, key=lambda x: int(x[1]),reverse=True)

        else:
            for element in self.countryCat:
                if self.countryCat[element].continent == ccontinent:
                    areas.append((self.countryCat[element].name,self.countryCat[element].area))

            ### sorts by the second element in the tuple and reverses to sort from greatest to least
            return sorted(areas, key=lambda x: int(x[1]),reverse=True)

    ### returns the continent with the most people in the countries that are on that continent, this is done by creating a dictionary and adding each continent that comes up into it if it is not there already
    ### once there the amount of people is added to that keys value for each country in the catalogue and returns the max value and its corresponding key
    def findMostPopulousContinent(self):
        contpop = {}
        for element in self.countryCat:
            if self.countryCat[element].continent not in contpop:
                contpop[self.countryCat[element].continent] = self.countryCat[element].pop

            else:
                contpop[self.countryCat[element].continent] = contpop[self.countryCat[element].continent] + self.countryCat[element].pop 

        return (max(contpop,key = contpop.get),contpop[max(contpop,key = contpop.get)])
                

    ### returns a sorted list of countries within a given population density range, this is done by taking the upper and lower bounds of the range as parameters and calling the getPopDensity() method of the country class and checking if it is within the range
    def filterCountriesByPopDensity(self,lowerdensity,upperdensity):
        inbounds = []
        for element in self.countryCat:
            if self.countryCat[element].getPopDensity() >= lowerdensity and self.countryCat[element].getPopDensity() <= upperdensity:
                inbounds.append((element,self.countryCat[element].getPopDensity()))

        ### sorts by the second element in the tuple and reverses to sort from greatest to least
        return sorted(inbounds, key=lambda x: int(x[1]),reverse=True)

    ### saves the country catalogue to a .txt file of a given name and returns the number of items writen to the file or -1 if the write was unsuccessful
    def saveCountryCatalogue(self,filename):
        try:
            openfile = open(filename,'w+')
            
            countryCatsorted = {}

            ### creates a sorted version of countryCat 
            for key in sorted(self.countryCat):
                countryCatsorted[key] = self.countryCat[key]


            ### writes the header statement then goes through and writes each the statement for each country object
            openfile.write('Name|Continent|Population|AreaSize|PopulationDensity\n')
                           
            for element in countryCatsorted:
                finalstring = countryCatsorted[element].name +'|' + countryCatsorted[element].continent +'|'+ str(countryCatsorted[element].pop) +'|'+ str(round(countryCatsorted[element].area,2)) +'|'+ str(countryCatsorted[element].getPopDensity())+'\n'

                openfile.write(finalstring)

            openfile.close()

            return len(countryCatsorted)

        except:
            return -1
            
            
