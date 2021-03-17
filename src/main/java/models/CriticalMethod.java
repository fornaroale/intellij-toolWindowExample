package models;

public class CriticalMethod {
    String desc;
    float relevancy;

    public CriticalMethod(){
        desc="void desc";
        relevancy=-1;
    }

    public CriticalMethod(String desc, String relevancy){
        this.desc=desc;
        this.relevancy=Float.parseFloat(relevancy);
    }

    public String getDesc() {
        return desc;
    }

    public float getRelevancy() {
        return relevancy;
    }

}
