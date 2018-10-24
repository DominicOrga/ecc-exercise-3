Exist Software Labs, Inc. (PH)  
Exist Code Camp (ECC)  

### Exercise 3: Java 8 Features  
Create a Horse Racing Simulator  

Conditions:  
1.  Generate 100 horses by default.  
2.  Each horse must have a name, status (healthy or unhealthy), and a warcry.  
    2.1.  Horse status can either be healthy or unhealthy.  
    2.2.  Horse warcries must be randomized from an pool of nullable warcries.  
    2.3.  Horse names are also randomized.  
3.  Among the 100 horses, get 10 healthy horses. If insufficient, re-generate the horses.  
4.  Initial position for every horse is randomized  between 1 to 10 units.  
5.  The target destination of the horses is 100 units.  
6.  Each horse can run between 1 to 10 u/s. The last horse is a special case, which can be boosted  
    to run between 1 to 20 u/s.  
    6.1.  Report the positions of every horses and their remaining distance to travel as they run.  
    6.2.  Boosted horses must first report all the positions of the other horses to inform the user  
          that it is indeed the last horse.  
7.  If a horse has won the race, it shouts its warcry. All preceding horses will not do so.  