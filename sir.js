/*
    a State Object represents the state of the contagion at a given moment
    the three main variabile are n (population), i (infected), ni (non infected)
    the infected are infective. The non infected are not infective.
    the day variable works as a label.
*/
class State
{
    //n -> population, i-> infected, infective, ni -> non-infected, non-infective
    constructor(n,i,ni,day)
    {
        this.day = day ? day : 0;
        this.n = n;
        this.i = i;
        this.ni = ni;
    }

    //s - in the SIR model it works as "susceptible"
    //can be infected. It's a simple difference
    s()
    {
        return this.n - this.i - this.ni;
    }

    //this is given as delta I / delta t = (beta * S * B / n) - (gamma * I)
    //it's the increase, or decrease, in infection 
    //notice that gamma * I is the recovery rate
    deltaI(beta, gamma)
    {
        return ((this.i * beta * this.s())/this.n) - this.deltaNI(gamma);
    }

    //this is the increase, or decrease, in recovery (or death)
    deltaNI(gamma)
    {
        return (gamma*this.i);
    }

    //this calculates the next state from the current date and a given beta and gamma couple 
    //another day is provided as label.
    //if none is given, the current day is provided, increased by one.
    next(beta, gamma,day)
    {
        if(!day) day = this.day++;

        return  new State
                (
                    this.n                                                     ,
                    parseInt(this.i     +   this.deltaI(beta,gamma))           ,
                    parseInt(this.ni    +   this.deltaNI(gamma))               ,
                    day               
                );
    }

    //in the case we don't know beta and gamma, we try to guess them from a previous state
    //through inverse formulae

    //gamma given a previous state
    gamma(previous)
    {
        //calculating gamma from the previous state
        return  (this.ni-previous.ni) / previous.i;
    }

    //calculating beta from a previous state
    beta(previous)
    {
        var DNI = this.ni-previous.ni; 
        var DI = this.i - previous.i;
        return  (DI + DNI) 
                / 
                (
                    (previous.i * previous.s())/this.n
                );
    }

    //the difference is given as beta - gamma
    difference(previous)
    {
        return {"beta":this.beta(previous), "gamma":this.gamma(previous)};
    }

}


// a sir object is given as an experiment, a run trying to guess the outcome of a contagion
// we provide fixed beta and gamma, and consider n days
class SIR
{

    constructor(state, beta, gamma)
    {
        this.state = state;
        this.beta = beta;
        this.gamma = gamma;
    }

    runfor(days, d)
    {
        if(!d) d = 0;
        var res = [this.state];
        for(var k=1;k<=days;k++)
            res.push(res[k-1].next(this.beta, this.gamma,k+d));
        return res;        
    }
    
}


var SIRFactory =
{
    //creating a SIR model trying to guess beta and gamma depending on a series of previous states
    strategies:
    {
        //ordinary least square
        linearOLS:function(x,y,newx)
        {
            
            
            //m =   [nsum(xy)-sum(x)sumy)]
            //      ----------------------
            //      nsum(x^2)-sum(x)^2
            
            
            //q =   sum(y)sum(x^2) - sum(x)sum(xy)
            //      ------------------------------
            //      nsum(x^2)-sum(x)^2
    
            var sumx  = x.reduce((a, c) => a + c);
            var sumy  = y.reduce((a, c) => a + c);
            var sumx2 = x.reduce((a,c)=>(a+(c*c)));
            var sumxy  = y.reduce((a,c,i)=>a+(x[i]*y[i]),0); 
    
    
            var m = (x.length * sumxy - sumx * sumy)
                    /
                    ((x.length * sumx2)-(sumx*sumx));
            
            var n = (sumy*sumx2 - sumx*sumxy)
                    /
                    ((x.length * sumx2)-(sumx*sumx));

            return m*newx+n;   
        },
        //average of the last n% percentage
        average:function(x,y,newx, p)
        {
            if(!p) p = 0.5;

            var thres = parseInt(x.length*p);
            
            return y.reduce((a,c,i)=>(a+(i>=thres ? c : 0)),0) / (x.length-thres);

        }
    
    },

    make:function(states, strategy)
    {
        if(!strategy) strategy = this.strategies.average;

        var res = [];
        //I consider the states making up my history
        //and try to guess beta and gamma
        
        // first of all, I need to calculate the betas and gammas for the states provided
        for(var i=1;i<states.length;i++)
            res.push(states[i].difference(states[i-1]));

        //now I have an array of {beta, gamma} objects
        //now, how to guess. Averaging would be poor practice, as beta and gamma
        //are likely to go a given path, which may be linear, parabolic, or something else
        //we may apply least square method (OLS), hoping it's linear

        var x    = res.map(function(x,i){return i;}); 
        var beta = res.map(function(x){return x.beta});
        var gamma = res.map(function(x){return x.gamma});

        //these are lines
        var beta = strategy(x,beta,x.length);
        var gamma = strategy(x,gamma,x.length);

        return new SIR(states[states.length-1], beta, gamma);


    }
}

// approximate data for Italy since the 10th of march
var states = 
[
   
    new State(60000000,8514,1635,1),
    new State(60000000,10590,1872,2),
    new State(60000000,12839,2274,3),
    new State(60000000,14955,2705,4),
    new State(60000000,17750,3407,5),
    new State(60000000,20603,4144,6),
    new State(60000000,23073,4907,7),
    new State(60000000,26062,5444,8),
    new State(60000000,28710,7003,9),
    new State(60000000,33190,7845,10),
    new State(60000000,37860,9161,11),
    new State(60000000,42681,10897,12),
    new State(60000000,46638,12500,13),
    new State(60000000,50418,13509,14),
    new State(60000000,54030,15146,15),
    new State(60000000,57521,16865,16),
    new State(60000000,62013,18526,17),
    new State(60000000,66414,20084,18),
    new State(60000000,70065,22407,19),
    new State(60000000,73880,23809,20),
    new State(60000000,75528,26211,21),
    new State(60000000,77635,28157,22),
    new State(60000000,80572,30002,23),
    new State(60000000,83049,32193,24),
    new State(60000000,85388,34439,25),
    new State(60000000,88274,36358,26),
    new State(60000000,91246,37702,27),
    new State(60000000,93187,39360,28),
    new State(60000000,94067,41519,29),
    new State(60000000,95262,44160,30),
    new State(60000000,96877,46749,31),
    new State(60000000,98273,49304,32),
    new State(60000000,100269,52002,33),
    new State(60000000,102253,54110,34),
    new State(60000000,103616,55900,35),
    new State(60000000,104291,58197,36),
    new State(60000000,105418,59737,37),
    new State(60000000,106607,62334,38),
    new State(60000000,106962,65472,39),
    new State(60000000,107771,68154,40),
    new State(60000000,108257,70715,41),
    new State(60000000,108237,72991,42),
    new State(60000000,107709,76248,43),
    new State(60000000,107699,79628,44)
   
];
var sir = SIRFactory.make(states);
console.log("Estimated SIR;");
console.log(sir);

console.log("Next estimate");
console.log(sir.runfor(1)[1]);
