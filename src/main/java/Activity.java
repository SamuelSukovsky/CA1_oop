public class Activity
{
    private String type;
    private double duration;
    private String date;
    private double distance;
    private int heartRate;

    public Activity()
    {
        this.type = "";
        this.duration = 0;
        this.date = "";
        this.distance = 0;
        this.heartRate = 0;
    }

    public Activity(String type,double duration,String date, double distance,int heartRate)
    {
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.distance = distance;
        this.heartRate = heartRate;
    }

    //Getters
    String getType() {return this.type;}
    double getDuration() {return this.duration;}
    double getDistance() {return this.distance;}
    String getDate() {return  this.date;}
    int getHeartRate() {return this.heartRate;}

    //Setters
    void setType(String type){this.type = type;}
    void setDuration(double duration){this.duration = duration;}
    void setDistance(double distance){this.distance = distance;}
    void setDate(String date){this.date = date;}
    void setHeartRate(int heartRate){this.heartRate = heartRate;}

    //Methods
    public double EnergyExpanded()
    {
        double duration = getDuration();
        double distance = getDistance();
        double energy = distance / duration * 60;
        return energy;
    }

    public double CalculateCalories()
    {
        double duration = getDuration();
        double distance = getDistance();
        String type = getType();
        double energy = EnergyExpanded();
        double calories;
        double intensity = 0;
        switch (type)
        {
            case "Swimming":
            {
                if(0.5 <= energy && energy < 1.25) intensity = 5;
                if(1.25 <= energy && energy < 2) intensity = 6.3;
                if(2 <= energy && energy < 2.75) intensity = 7.6;
                if(2.75 <= energy && energy < 3.5) intensity = 8.9;
                if(3.5 <= energy) intensity = 10.2;
            }
            case "Running":
            {
                if(energy < 4) intensity = 4.1;
                if(4 <= energy && energy < 8) intensity = 7.2;
                if(8 <= energy && energy < 12) intensity = 10;
                if(12 <= energy && energy < 16) intensity = 15.4;
                if(16 <= energy && energy < 24) intensity = 20.8;
            }
            case "Cycling":
            {
                if(energy < 8) intensity = 2;
                if(8 <= energy && energy < 16) intensity = 5;
                if(16 <= energy && energy < 25) intensity = 7;
                if(25 <= energy && energy < 33) intensity = 13;
                if(33 <= energy && energy < 40) intensity = 15;
            }
        }
        calories = intensity * duration;
        return calories;
    }
}
