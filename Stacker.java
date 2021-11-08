// Name: Lan Niu    ID: 1320386
// Name: Shizhen Wang  ID: 1240171


import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
public class Stacker implements Comparable<Stacker> {

	//declare variables
	private int stackHeight;
	private HashMap<Box, Integer> listBoxes;
	private ArrayList<Box> orderedBoxes;
//consuctor
	public Stacker(HashMap<Box, Integer> hm) {
		this.listBoxes = hm;
		this.stackHeight = 0;
		comparebox();
	}

//return the stack height
	public int getstackheight() {
		return stackHeight;
	}
	//calculate the height of stack
	private void calculatestackheight(ArrayList<Box> B) {
		int h = 0;
		for (Box b : B)
			h += b.getHeight(listBoxes.get(b));
		this.stackHeight = h;
	}

// use  mutate rate to mutate the box if need mutate then campare the box
	public void mutate() {
		boolean ifNeedMutation = false;
		for (Box b : this.listBoxes.keySet()) {
			if (NPStack.random.nextFloat() < NPStack.mutateRate) {
				this.listBoxes.put(b, NPStack.random.nextInt(3));
				ifNeedMutation = true;
			}
		}
		if (ifNeedMutation)
			comparebox();
	}
	//crossover 2 stackers
	public Stacker breed(Stacker s) {
		HashMap<Box, Integer> tmp = new HashMap<Box, Integer>();
		int i = 0;
		for (Box b : this.listBoxes.keySet()) {
			if ((i % 2) == 0)
				tmp.put(b, this.listBoxes.get(b));
			else
				tmp.put(b, s.listBoxes.get(b));
			i++;
		}

		for (Box b : tmp.keySet())
			if (NPStack.random.nextFloat() < NPStack.mutateRate)
				tmp.put(b, NPStack.random.nextInt(3));
		return new Stacker(tmp);
	}
	private void comparebox() {
		int area;
		TreeMap<Integer, Box> areaBoxes = new TreeMap<Integer, Box>();
		orderedBoxes = new ArrayList<Box>();
		for (Box b : this.listBoxes.keySet()) {
			area = b.getWidth(this.listBoxes.get(b))
					* b.getLength(this.listBoxes.get(b));
			areaBoxes.put(area, b);
		}
		Box[] ba = areaBoxes.descendingMap().values().toArray(new Box[0]);
		orderedBoxes.add(ba[0]);
		for (int i = 1; i < ba.length; i++) {
			Box next = ba[i];
			Box lastAdded = orderedBoxes.get(orderedBoxes.size() - 1);
			if (next.checkstack(listBoxes.get(next), lastAdded,
					listBoxes.get(lastAdded))) {
				orderedBoxes.add(next);
			}
		}
		calculatestackheight(orderedBoxes);
	}
	@Override
	public int compareTo(Stacker s) {
		if (!(s instanceof Stacker))
			throw new ClassCastException("Expected Stack Object.");
		return s.getstackheight() - this.getstackheight();
	}

	@Override
	public String toString() {
		String st = "";
		int h = 0;
		for (int i = 0; i < orderedBoxes.size(); i++) {
			Box b = orderedBoxes.get(i);
			int num = this.listBoxes.get(b);
			h += b.getHeight(num);
			st += b.toString(num) + " " + h + "\n";
		}
		return st;
	}

}
