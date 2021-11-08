// Name: Lan Niu    ID: 1320386
// Name: Shizhen Wang  ID: 1240171

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class NPStack{
	static public float mutateRate = 0.01f;
	static public SecureRandom random;
	static public int stacklimit = 0;

///
//This method is used to check the input format
	public static boolean check(String st){
		try{
			if(Integer.parseInt(st.split(" ")[0]) < 1 || Integer.parseInt(st.split(" ")[1]) < 1 || Integer.parseInt(st.split(" ")[2]) < 1 )
			{return false;}
			else
			{
				return true;}
		}
		catch(Exception e){
			return false;
		}
	}

	public static void main(String args[]) throws IOException{
		// check the input arguments
		if(args.length != 2){
			System.err.println("ERROR: Usage java NPStack [file] [number of candidates]");
			return;
		}
		stacklimit = Integer.parseInt(args[1]);
		random = new SecureRandom();
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		ArrayList<Box> boxes = new ArrayList<Box>();
		String line;
		int tmp = 1;
		//read the file
		while ((line = br.readLine()) != null){
			if(check(line))
				boxes.add(new Box(Integer.parseInt(line.split(" ")[0]), 
						Integer.parseInt(line.split(" ")[1]), 
						Integer.parseInt(line.split(" ")[2])));
			tmp++;
		}
//check the number of candidate
		if(stacklimit < boxes.size()){
			System.err.println("The number of candidate should be greater than " + (boxes.size() - 1));
			return;
		}
		//first random generation
		ArrayList<Stacker> genpool = new ArrayList<Stacker>();

		for(int i = 0; i < boxes.size(); i++){
			HashMap<Box, Integer> hm = new HashMap<Box, Integer>();
			for(Box b : boxes)
				hm.put(b, random.nextInt(3));
			genpool.add(new Stacker(hm));
			stacklimit--;
		}
		while(stacklimit > 0){
			Collections.sort(genpool);
			Stacker[] sr = genpool.toArray(new Stacker[0]);
			for(int i = 0; i < (int)(boxes.size()*0.5); i += 2){
				if(stacklimit > 0)
					genpool.add(sr[i].breed(sr[i+1]));
				stacklimit--;
			}
			for(int i = (int)(boxes.size()*0.5); i < sr.length; i ++){
				if(stacklimit > 0)
					sr[i].mutate();
				stacklimit--;
			}
			Collections.sort(genpool);
			genpool = new ArrayList<Stacker>(genpool.subList(0, boxes.size()));
		}
		Stacker max = genpool.get(0);
		System.out.println(max.toString());

	}
	


}
