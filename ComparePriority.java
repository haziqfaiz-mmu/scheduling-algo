import java.util.Comparator;

public class ComparePriority implements Comparator<Job>{
    
    public int compare(Job j1, Job j2){

        return j1.getPriority() - j2.getPriority();
    }
}