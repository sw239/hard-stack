// Name: Lan Niu    ID: 1320386
// Name: Shizhen Wang  ID: 1240171


public class Box{
	int height, width, length;
	
	public Box(int h, int w, int d){
		this.height = h;
		this.width = w;
		this.length = d;
	}
//return height
	public int getHeight(int num){
		if(num == 0) return this.height;
		if(num == 1) return this.length;
		if(num == 2) return this.width;
		return -1;
	}

	public int getWidth(int num){
		if(num == 0 || num == 1) return this.width;
		if(num == 2) return height;
		return -1;
	}

	public int getLength(int num){
		if(num == 0 || num == 2) return this.length;
		if(num == 1) return this.height;
		return -1;
	}

	public boolean checkstack(int thisNum, Box lastAdded, int numOfLast){
		if(lastAdded.getLength(numOfLast) > this.getLength(thisNum) && lastAdded.getWidth(numOfLast) > this.getWidth(thisNum))
			return true;
		if(lastAdded.getLength(numOfLast) > this.getWidth(thisNum) && lastAdded.getWidth(numOfLast) > this.getLength(thisNum))
			return true;
		return false;
	}

	public String toString(int n){
		if(getWidth(n) < getLength(n))
			return getHeight(n) + " " + getWidth(n) + " " + getLength(n);
		else
			return getHeight(n) + " " + getLength(n) + " " + getWidth(n);
	}
}
