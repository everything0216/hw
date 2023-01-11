package summer.test;

public class test{
    public static void main(String[] args){
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight [] sights = crawler.getItems("七堵");
        for (Sight s: sights) {
            if(s!=null){
                System.out.println(s);
            }
            
        }
    }
}
