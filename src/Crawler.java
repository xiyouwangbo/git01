
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Crawler {

    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter a URL: ");
//        String url = input.nextLine();
        craw("http://www.xupt.edu.cn"); // Traverse the Web from the a starting url
    }

    public static void craw(String startingURL) {
        ArrayList<String> listOfPendingURLs = new ArrayList<>();
        ArrayList<String> listOfTraversedURLs = new ArrayList<>();

        listOfPendingURLs.add(startingURL);
        while (!listOfPendingURLs.isEmpty() &&
                listOfTraversedURLs.size() <= 100000) {
            String urlString = listOfPendingURLs.remove(0);
            if (!listOfTraversedURLs.contains(urlString)) {
                listOfTraversedURLs.add(urlString);
                System.out.println("Craw " + urlString);

                try {
					for (String s: getSubURLs(urlString)) {
					    if (!listOfTraversedURLs.contains(s))
					        listOfPendingURLs.add(s);
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }

    public static ArrayList<String> getSubURLs(String urlString) throws MalformedURLException {
        ArrayList<String> list = new ArrayList<>();
        java.net.URL url = new java.net.URL(urlString);
        try( BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") ))  {
           
            int current = 0;
            while (true) {
                String line = input.readLine();
                
                System.out.println(line);
                if (line.contains("ÍõÖÒÃñ")) {
                    System.out.println("Found the word \"Computer Programming\".");
                    System.out.println("URL: " + url);
                    System.exit(0);
                }
                current = line.indexOf("http:", current);
                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    }
                    else
                        current = -1;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }
}
