package week1;

import java.util.ArrayList;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MagnitudeFilter mag=new MagnitudeFilter(4.0,5.0);
        DepthFilter depth =  new DepthFilter(-35000.0,-12000.0);
        PhraseFilter phrase = new PhraseFilter("end","Japan");
        DistanceFilter loc = new DistanceFilter(new Location(35.42,139.43),10000000.0);
        
//        ArrayList<QuakeEntry> maggy  = filter(list, mag);
//        ArrayList<QuakeEntry> maggyAndDepthy  = filter(maggy, depth);
//        ArrayList<QuakeEntry> japan  = filter(list, phrase);
//        ArrayList<QuakeEntry> close = filter(japan,loc);
        MatchAllFilter clJapan = new MatchAllFilter();
        clJapan.addFilter(loc);
        clJapan.addFilter(phrase);
        ArrayList<QuakeEntry> closeJapan = filter(list,clJapan);
        for (QuakeEntry qe: closeJapan) { 
            System.out.println(qe);
        }
        System.out.println("Filters used are "+ clJapan.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
