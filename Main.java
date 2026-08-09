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
    
    public static class internalStatus(){
        int virus; // disease outbreak
        int terrorists; // terrorists inside the country
        int immigration; // migrant crisis
        int organized; // organized crime;
        
        int virusKnown;
        int terroristsKnown;
        int immigrationKnown;
        int organizedKnown;
        
        public internalStatus(){
            virus = ra.nextInt(5)+1;
            terrorists = ra.nextInt(5)+1;
            immigration = ra.nextInt(5)+1;
            organized = ra.nextInt(5)+1;
        }
        
        public void determineReportQuality(){
            for(int i=0; i<virus;i++){
                virusKnown += (advInternal.competenceFlip())? 1:0;
                
            }
            virusKnown++;
            
            if(advInternal.isHeJudas()){
                virusKnown += ra.nextInt(5);
            }
            
            for(int i=0; i<terrorists;i++){
                terroristsKnown += (advInternal.competenceFlip())? 1:0;
                
            }
            terroristsKnown++;
            if(advInternal.isHeJudas()){
                terroristsKnown += ra.nextInt(5);
            }
            for(int i=0; i<immigration;i++){
                immigrationKnown += (advInternal.competenceFlip())? 1:0;
                
            }
            immigrationKnown++;
            if(advInternal.isHeJudas()){
                immigrationKnown += ra.nextInt(5);
            }
            
            for(int i=0; i<organized;i++){
                organizedKnown += (advInternal.competenceFlip())? 1:0;
                
            }
            organizedKnown++;
            if(advInternal.isHeJudas()){
                organizedKnown += ra.nextInt(5);
            }
            
            virusKnown = Math.min(virusKnown,5);
            terroristsKnown = Math.min(terroristsKnown,5);
            immigrationKnown = Math.min(immigrationKnown,5);
            organizedKnown = Math.min(organizedKnown,5);
            
            virusKnown = Math.max(virusKnown,1);
            terroristsKnown = Math.max(terroristsKnown,1);
            immigrationKnown = Math.max(immigrationKnown,1);
            organizedKnown = Math.max(organizedKnown,1);
            
            
            
        }
        
        public void displayReport(){
            Map<int, String> virusReport = new HashMap<>();
            virusReport.put(1, "There is currently no health emergency");
            virusReport.put(2, "There are minor reports of a localized virus epidemic");
            virusReport.put(3, "Reports indicate viral epidemics across multiple population centers");
            virusReport.put(4, "Reports indicate a nationwide viral epidemic");
            virusReport.put(5, "We are currently experiencing a nationwide viral crisis");
            
            Map<int, String> terroristReport = new HashMap<>();
            terroristReport.put(1, "There are currently no internal terrorist threats");
            terroristReport.put(2, "Reports indicate minor suspicious activities by some groups");
            terroristReport.put(3, "Internal intelligence suggests terroristic activities in some regions");
            terroristReport.put(4, "There are reports of a localized terrorist network ");
            terroristReport.put(5, "There is currently a nationwide terrorist organization");
            
            Map<int, String> immigrationReport= new HashMap<>();
            immigrationReport.put(1, "There are no indications of any migrant crisis");
            immigrationReport.put(2, "There are localized reports of illegal border crossings");
            immigrationReport.put(3, "There are various reports of undocument immigrants across border regions");
            immigrationReport.put(4, "Widespread reports indicate a significant influx of undocumented immigrants");
            immigrationReport.put(5, "We are currently experiencing a nationwide migrant crisis;");
            
            Map<int, String> organizedReport= new HashMap<>();
            organizedReport.put(1, "Internal intelligence suggests no major crime organization has emerged");
            organizedReport.put(2, "There are minor incidences of localized racketeering and smuggling rings");
            organizedReport.put(3, "Internal intelligence suggests various localized crime syndicates in various regions");
            organizedReport.put(4, "Reports highly indicate a major crime syndicate is active");
            organizedReport.put(5, "There is currently a nationwide organized crime syndicate running rampant in our nation");
            
            
            
            System.out.println(virusReport.get(virusKnown));
            System.out.println(terroristReport.get(terroristsKnown));
            System.out.println(immigrationReport.get(immigrationKnown));
            System.out.println(organizedReport.get(organizedKnown));
            
        }
    }
    
    public static class Action{
        String desc;
        int year,week;
        
        
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
