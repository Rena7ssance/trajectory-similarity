package uq.entities;

import java.util.ArrayList;

/**
 * Implements a distance file entity.
 * Distances values and file name.
 * 
 * @author uqdalves
 *
 */
public class DistanceFile {
	private ArrayList<Double> distancesList = 
			new ArrayList<Double>();
	public String fileName = "";

	public DistanceFile() {}
	public DistanceFile(String fileName) {
		this.fileName = fileName;
	}
	
	public ArrayList<Double> getDistancesList() {
		return distancesList;
	}
	public void setDistancesList(ArrayList<Double> distancesList) {
		this.distancesList = distancesList;
	}
	/**
	 * Add a distance to the list. 
	 * Append it to the end of the list.
	 */
	public void addDistance(double distance){
		distancesList.add(distance);
	}

}
