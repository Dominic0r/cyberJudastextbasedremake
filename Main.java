import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static enum department {
        INTERNAL, // Department of Homeland Security
        FOREIGN, // Department of Foreign Affairs
        ECONOMY, // Department of Finance
        DEFENSE, // Department of Defense 
        COVERT // CIA 
    };
    
    public static class Advisor{
        String name;
        department Dep;
        int competence;
        boolean isJudas;
        
        List<Action> actionHistory = new ArrayList<>();
        
        public Advisor(String name, department Dep){
            this.name = name;
            this.Dep = Dep;
            this.competence = ra.nextInt(100);
            isJudas = false;
        }
        
        public void setJudas(){
            isJudas = true;
            competence = Math.min(competence + (competence/2), 100);
        }
        
        public String getName(){ return name;}
        public department getDep(){ return Dep;}
        public int getCompetence(){ return competence;}
        public boolean isHeJudas(){ return isJudas;}
        
        public boolean competenceFlip(){
            if(!isJudas){
                return ra.nextInt(100)<= competence;
            }else{
                return ra.nextInt(100)>= competence;
            }
        }
        
        
    }
    
    public static class crisisSit{
        
        String name;
        int actual;
        int known;
        
        Map<Integer, String> report = new HashMap<>();
        
        
        public crisisSit(String name){
            this.name = name;
            actual = ra.nextInt(5)+1;
        }
        
        public HashMap<Integer, String> getReportList(){ return report;}
        public String getName(){return name;}
        public int getActual(){return actual;}
        public int getKnown(){return known;}
        
        public String displayReport(){return report.get(known);}
        
        public void addToReportList(int lev, String desc){
            report.put(lev,desc);
        }
        
        public void determineKnown(Advisor adv){
            for(int i=0; i<5;i++){
                known+= (adv.competenceFlip())? 1:0;
                if(i >= actual){
                    if(competenceFlip()){
                        break;
                    }
                }
            }
            known ++;
            
            known = Math.min(5. known);
            known = Math.max(1, known);
        }
    }
    
    public static class departmentStatus(){
        Advisor adv;
        
        List<crisisSit> situations = new ArrayList<>();
        
        public departmentStatus(Advisor adv){
            this.adv = adv;
        }
        
        public void addSituation(crisisSit newSit){
            situations.add(newSit);
        }
        
        public void determineReportQuality(){
            for(crisisSit sit : situations){
                sit.determineKnown();
            }
            
        }
        
        public void displayReport(){
            for(crisisSit sit: situations){
                sit.displayReport();
            }
        }
        
        public static void recommendAction(){
            int maxConcern = Integer.MIN_VALUE;
            crisisSit maxSit = null;
            for(crisisSit sit: situations){
                int curpo = sit.getKnown();
                if(curpo > maxConcern){
                    maxSit = sit;
                    maxConcern = curpo;
                }
            }
            
            if(maxConcern ==1){
                System.out.println("No actions recommended for now");
            }else{
                System.out.println("I recommend taking action here: ");
                System.out.println(maxSit.getName()+ ": "+ maxSit.displayReport());
                
                
                System.out.println("Do it? (y / n (default) )");
                String ans = sc.nextLine();
                if(ans.equalsIgnoreCase("y")){
                    
                    int timer = Math.max(((100-adv.getCompetence())/10),1);
                    actionqueue.add(new Action(maxSit, adv, timer) );
                }
            }
        }
    }
    
    public static class Action{
        crisisSit targetSit;
        Advisor adv;
        int timer;
        
        public Action(crisisSit targetSit, Advisor adv, int timer){
            this.targetSit = targetSit;
            this.adv = adv;
            this.timer = timer;
        }
        
        public boolean getResult(){
            return adv.competenceFlip();
        }
        
        public void countDown(){
            timer--;
        }
        
        public void displaySelf(){
            System.out.println("Action on " + targetSit.getName()+ " | Recommended by "+ adv.getName()+ " | Completed in "+ timer+ ((timer>1)?" weeks":" week"));
        }
        
    }
    
    public static List<Action> actionqueue = new ArrayList<>();
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
