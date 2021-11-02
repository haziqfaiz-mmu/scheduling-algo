import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin2 {

    public static void solve(){

        int cpuTime=0,avgWT, avgTT;
        ArrayList<Job> jobList = new ArrayList<Job>();
        ArrayList<GanttChart> gcArray = new ArrayList<GanttChart>();
        Job j1 = new Job("j1",3,11);
        Job j2 = new Job("j2",3,10);
        Job j3 = new Job("j3",3,10);
        Queue<Job> waitQueue = new LinkedList<Job>();

        jobList.add(j1);
        jobList.add(j2);
        jobList.add(j3);
        Collections.sort(jobList);

        System.out.println("Job list: "+jobList.toString());


        while(!jobList.isEmpty() || !waitQueue.isEmpty()){

            while((!jobList.isEmpty()&&waitQueue.isEmpty())){

                //add the first job to waitqueue this means joblist is not empty
                checkForNewArrival(jobList, waitQueue, cpuTime);
                //System.out.println(cpuTime);
                if(waitQueue.isEmpty())
                cpuTime++;  
            }
            System.out.println("Waitqueue: "+waitQueue);
            //check for new arrival again because cpuTime changed
            checkForNewArrival(jobList, waitQueue, cpuTime);
            System.out.println("Processsing "+ waitQueue.peek().getJobID()+" at "+(cpuTime+3));
            System.out.println("Before: process "+waitQueue.peek().getJobID()+" "+waitQueue.peek().getRemainingBurstTime());
            cpuTime = process(waitQueue.peek(), cpuTime, jobList, waitQueue,gcArray);
            System.out.println("After: process "+waitQueue.peek().getJobID()+" "+waitQueue.peek().getRemainingBurstTime());
    
            System.out.println(cpuTime);
            System.out.println(waitQueue);
                    
            //-----------------------------------//
            Job temp = waitQueue.poll();
            System.out.println(waitQueue);
            if(temp.getJobStatus()==false){
                waitQueue.add(temp);
            }
            System.out.println(waitQueue);
            
        }
        System.out.println(j1.getCompletionTime());
        System.out.println(j2.getCompletionTime());
        System.out.println(j3.getCompletionTime());
        System.out.println(gcArray);
    }


    public static int process(Job j, int cpuTime, ArrayList<Job> jobList, Queue<Job> waitQueue, ArrayList<GanttChart> gcArray){

        int qTime =3, i=1, cpuTimeCopy = cpuTime;
        while(i<=qTime && j.getRemainingBurstTime()>0){
            //System.out.println("Current burst time: "+ j.getRemainingBurstTime());
            //System.out.println("Current cpu time: "+ cpuTime);
            j.setRemainingBurstTime(j.getRemainingBurstTime()-1);
            cpuTime++;
            i++;
            checkForNewArrival(jobList, waitQueue, cpuTime);
        }
        GanttChart gcElement = new GanttChart(j.getJobID(),cpuTimeCopy,cpuTime);
        gcArray.add(gcElement);
        
        if(j.getRemainingBurstTime()==0){
            j.setJobStatus(true);
            j.setCompletionTime(cpuTime);
        }

        return cpuTime;
    }

    public static void checkForNewArrival(ArrayList<Job> jobList, Queue<Job> waitQueue, int cpuTime){
        
        boolean flag = true;
        int i=0;
        while(flag && i<jobList.size()){
            System.out.println("i is "+i);
            Job temp = jobList.get(i);

            if(temp.getArrivalTime()>cpuTime){
                flag = false;
            }
            
            if(temp.getArrivalTime()==cpuTime){
            waitQueue.add(temp);
            jobList.remove(i);
            i--;
            }
            i++;
        }
    }
    public static void main(String args[]){
        solve();
    }

}
