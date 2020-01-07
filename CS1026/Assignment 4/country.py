### creates a class called Country which has name, pop, area, continent as instance variables
class Country:
    ### initializes object and takes instance variables
    def __init__(self,name,pop,area,continent):
        self.name = name
        self.pop = int(pop)
        self.area = float(area)
        self.continent = continent

    ### returns name
    def getName(self):
        return self.name

    ### returns population
    def getPopulation(self):
        return self.pop

    ### returns area
    def getArea(self):
        return self.area

    ### returns continent
    def getContinent(self):
        return self.continent

    ### sets the population of the object
    def setPopulation(self,pop):
        self.pop = int(pop)

    ### sets the area of the object
    def setArea(self,area):
        self.area = float(area)
        
    ### sets the continent of the object
    def setContinent(self,continent):
        self.continent = continent

    ### returns the population density of the object
    def getPopDensity(self):
        popdensity = round((self.pop/self.area),2)
        return popdensity

    ### string representation of the object
    def __repr__(self):
        return self.name+' (pop:'+str(self.pop)+', size: '+str(self.area)+') in '+self.continent
