public class Activity
{
    private String type;
    private String date;
    private double duration;
    private double distance;
    private int heartRate;
    private Energy energy;

    //default constructor
    public Activity()
    {
        this.type = "";
        this.date = "";
        this.duration = 0;
        this.distance = 0;
        this.heartRate = 0;
        this.energy = EnergyExpended();
    }

    //full constructor
    public Activity(String type, String date, double duration, double distance,int heartRate)
    {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.heartRate = heartRate;
        this.energy = EnergyExpended();
    }

    //Getters
    public String getType() {return this.type;}
    public double getDuration() {return this.duration;}
    public double getDistance() {return this.distance;}
    public String getDate() {return  this.date;}
    public int getHeartRate() {return this.heartRate;}
    public Energy getEnergy() {return this.energy;}

    //Setters
    void setType(String type){this.type = type;}
    void setDuration(double duration)
    {
        this.duration = duration;
        this.energy = EnergyExpended();
    }
    void setDistance(double distance)
    {
        this.distance = distance;
        this.energy = EnergyExpended();
    }
    void setDate(String date){this.date = date;}
    void setHeartRate(int heartRate){this.heartRate = heartRate;}

    //Methods
    public Energy EnergyExpended() //calculates the energy expanded and assigns the value from the table
    {
        double duration = getDuration();
        double distance = getDistance();
        double speed = distance / duration * 60;
        String type = getType();
        Energy energy = Energy.Very_Light;
        switch (type) //uses different values based on the type of the activity
        {
            case "Swimming":
            {
                if(speed < 1.25) energy = Energy.Very_Light;
                if(1.25 <= speed && speed < 2) energy = Energy.Light;
                if(2 <= speed && speed < 2.75) energy = Energy.Moderate;
                if(2.75 <= speed && speed < 3.5) energy = Energy.Vigorous;
                if(3.5 <= speed) energy = Energy.Very_Vigorous;
            }
            case "Running":
            {
                if(speed < 4) energy = Energy.Very_Light;
                if(4 <= speed && speed < 8) energy = Energy.Light;
                if(8 <= speed && speed < 12) energy = Energy.Moderate;
                if(12 <= speed && speed < 16) energy = Energy.Vigorous;
                if(16 <= speed) energy = Energy.Very_Vigorous;
            }
            case "Cycling":
            {
                if(speed < 8) energy = Energy.Very_Light;
                if(8 <= speed && speed < 16) energy = Energy.Light;
                if(16 <= speed && speed < 25) energy = Energy.Moderate;
                if(25 <= speed && speed < 33) energy = Energy.Vigorous;
                if(33 <= speed) energy = Energy.Very_Vigorous;
            }
        }
        return energy;
    }

    public double CalculateCalories() //calculates calories based on energy expanded and the duration of the activity
    {
        double duration = getDuration();
        String type = getType();
        Energy energy = this.energy;
        double calories;
        double intensity = 0;
        switch (type) //depends on the type of the activity / assigns correct values from the tables
        {
            case "Swimming":
            {
                switch (energy)
                {
                    case Very_Light -> intensity = 5;
                    case Light -> intensity = 6.3;
                    case Moderate -> intensity = 7.6;
                    case Vigorous -> intensity = 8.9;
                    case Very_Vigorous -> intensity = 10.2;
                }
            }
            case "Running":
            {
                switch (energy)
                {
                    case Very_Light -> intensity = 4.1;
                    case Light -> intensity = 7.2;
                    case Moderate -> intensity = 10;
                    case Vigorous -> intensity = 15.4;
                    case Very_Vigorous -> intensity = 20.8;
                }
            }
            case "Cycling":
            {
                switch (energy)
                {
                    case Very_Light -> intensity = 2;
                    case Light -> intensity = 5;
                    case Moderate -> intensity = 7;
                    case Vigorous -> intensity = 13;
                    case Very_Vigorous -> intensity = 15;
                }
            }
        }
        calories = intensity * duration; //calculates calories
        return calories;
    }


    @Override
    public String toString()
    {
        return "Activity{" +
                "type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                ", distance=" + distance +
                ", heartRate=" + heartRate +
                ", calories=" + CalculateCalories() +
                '}';
    }
}
