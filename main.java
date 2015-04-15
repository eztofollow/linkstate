import java.io.*;
import java.util.*;

public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> text = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader( new FileReader("network.txt"));
		String temp = in.readLine();
		
		while(temp.compareTo("EOF.") != 0)
		{
			text.add(temp);
			temp = in.readLine();
		}
		in.close();
		
		System.out.println(text.toString());
		
	}

}
