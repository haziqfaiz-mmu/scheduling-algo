import java.util.Comparator;

public class CompareArrivalThenPriority implements Comparator<Job>{
    
    public int compare(Job j1, Job j2){

        if(j1.getArrivalTime() != j2.getArrivalTime()){
            return j1.getArrivalTime() - j2.getArrivalTime(); 
        }

        else
        return j1.getPriority() - j2.getPriority();
    }
}