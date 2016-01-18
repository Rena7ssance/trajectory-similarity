package uq.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import uq.entities.DistanceFile;
import uq.entities.Point;
import uq.entities.Trajectory;

/**
 * Service to deal with files (open, read, save)
 * 
 * @author uqdalves
 */
public class FileService {
	// Root directory of the files in the disc
	private static final String ROOT_PATH = "C:\\TrajectorySimilarityProject\\";
	private static final String OUTPUT_FOLDER = "OutputData\\";
	private static final String SAMPLE_FOLDER = "SampleData\\";
	private static final String TEST_FOLDER = "TestData\\";
	private static final String ALL_TRAJECTORIES_FOLDER = "ReSampledTrajectories\\";
	private static final String DISTANCES_FOLDER = "DistancesToRead\\";
	private static final String GROUND_TRUTH_FOLDER = "GroundTruth\\";
	
	private static final int DIMENSION = 2;
	
	/*	
	public static void main(String[] args) {
		File diretory = new File(ROOT_PATH + INPUT_FOLDER);
		File files[] = openDirectoryFiles(diretory);
		
	    for (int i = 0; i < files.length; i++) {  
	        String name = files[i].getName();
	      System.out.println(name);  
	        name = name.replaceAll("_pct", "_10pct");
	        name = name.replaceAll("_d", "_10d");

	        files[i].renameTo(new File(ROOT_PATH + name));
	    } 
	    
	    System.out.println("Finished");
			
    } */
	
	/**
	 * Return a list of Trajectory object with the coordinates read
	 * from disc. The original file trajectories, without re-sampling.
	 */
	public static ArrayList<Trajectory> readOriginalTrajectories(){
		ArrayList<Trajectory> trajectoryList = 
				new ArrayList<Trajectory>();
		
		// input date format
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa"); 
		
		try {	
			// open files from folder
			File diretory = new File(ROOT_PATH + SAMPLE_FOLDER);
			File files[] = openDirectoryFiles(diretory);
			
			// read files
			for(int fileId=0; fileId<files.length; fileId++){
				File currentFile = files[fileId];

				// read file
				BufferedReader buffer = new BufferedReader(
	        			new FileReader(currentFile));
				
				// each line of the file
	        	String line;
	        	
	        	// read the first line of the file
	        	line = buffer.readLine();
				
				// fields to be read from the file
				double coordinate[] = new double[DIMENSION];
				String timeString;
				
				// new trajectory for this file, set features
				Trajectory trajectory = new Trajectory();
				
				// read file lines (coordinates)
				while (buffer.ready()) {
					line = buffer.readLine();
					String[] tokens = line.split(",");
					
					// if new trajectory
					if(tokens[0].equals("#")){
						// Add trajectories with more than 20 points only
						if(trajectory.numberOfPoints() >= 20){
							trajectoryList.add(trajectory);
						}
						// new trajectory for this file, set features
						trajectory = new Trajectory();
					} else {
						// Parse the inputs
						timeString = tokens[0];
						coordinate[1] = Double.parseDouble(tokens[1]);
						coordinate[0] = Double.parseDouble(tokens[2]);
						
						Date date = new Date();
						try {
							date = dt.parse(timeString);
						} catch (ParseException e) {
							e.printStackTrace();
						} 
			
						// create a new point from the line input, set features
						Point point = new Point(coordinate,date);
						point.timeString = timeString;
				    	
				    	trajectory.addPoint(point);					
					}
				}
		    	
				// Add trajectories with more than 20 points only
				if(trajectory.numberOfPoints() >= 20){
					trajectoryList.add(trajectory);
				}
				
				// close file
				buffer.close();
			} 
			
		} catch (IOException e) {
			System.out.println("Error opening input files.");
			e.printStackTrace();
		}

		return trajectoryList;
	}
	
	
	/**
	 * Return a list of all Trajectory objects with the coordinates read
	 * from disc. From the re-sampled folder.
	 */
	public static ArrayList<Trajectory> readAllTrajectories(){
		return readTrajectories(ALL_TRAJECTORIES_FOLDER);	
	}
	
	/**
	 * Return a list of Trajectory objects with the coordinates read
	 * from disc. Sampled trajectories, form the sample folder.
	 */
	public static ArrayList<Trajectory> readSampleTrajectories(){
		return readTrajectories(SAMPLE_FOLDER);		
	}
	
	/**
	 * Return a list of test Trajectory objects with the coordinates read
	 * from disc. Previously selected random trajectories, form the test folder.
	 */
	public static ArrayList<Trajectory> readTestTrajectories(){
		return readTrajectories(TEST_FOLDER);
	}
	
	/**
	 * Return a list of Trajectory objects with the coordinates read
	 * from disc. Read the re-sampled trajectories.
	 */
	private static ArrayList<Trajectory> readTrajectories(final String folderToRead){
		// trajectories to read
		ArrayList<Trajectory> trajectoryList = 
				new ArrayList<Trajectory>();
		
		try {	
			// open files from folder
			File diretory = new File(ROOT_PATH + folderToRead);
			File files[] = openDirectoryFiles(diretory);
			
			// read files
			for(int fileId=0; fileId<files.length; fileId++){
				File currentFile = files[fileId];

				// read file
				BufferedReader buffer = new BufferedReader(
	        			new FileReader(currentFile));
				
				// each line of the file
	        	String line;
	        	
	        	// read the first line of the file
	        	line = buffer.readLine();
				
				// fields to be read from the file
				double coordinate[] = new double[DIMENSION];
				long timeLong;
				String timeString;
				
				// new trajectory for this file, set features
				Trajectory trajectory = new Trajectory();
				
				// read file lines (coordinates)
				while (buffer.ready()) {
					line = buffer.readLine();
					String[] tokens = line.split(",");
					
					// if new trajectory
					if(tokens[0].equals("#")){
						trajectoryList.add(trajectory);
						// new trajectory for this file, set features
						trajectory = new Trajectory();
					} else {
						// Parse the inputs
						coordinate[0] = Double.parseDouble(tokens[0]);
						coordinate[1] = Double.parseDouble(tokens[1]);
						timeString = tokens[2];
						timeLong = Long.parseLong(tokens[2]);

			
						// create a new point from the line input, set features
						Point point = new Point(coordinate,timeLong);
						point.timeString = timeString;
				    	
				    	trajectory.addPoint(point);					
					}
				}
				
				// adds the last trajectory in the file
		    	trajectoryList.add(trajectory);
				
				// close file
				buffer.close();
			} 
			
		} catch (IOException e) {
			System.out.println("Error opening input files.");
			e.printStackTrace();
		}
		
		return trajectoryList;
	}

	
	/**
	 * Return a list of distances from files. 
	 * One DistanceFile object per file.
	 * @param readGroundTruth True whether to read the ground truth file 
	 * (ground truth folder), false otherwise.
	 */
	public static ArrayList<DistanceFile> readDistances(boolean readGroundTruth){
		String folderToRead = readGroundTruth ? GROUND_TRUTH_FOLDER : DISTANCES_FOLDER;
		
		ArrayList<DistanceFile> distanceFilesList = 
				new ArrayList<DistanceFile>();

		System.out.println(" Reading Distance Files.. \n");
		try {	
			// open files from folder
			File diretory = new File(ROOT_PATH + folderToRead);
			File files[] = openDirectoryFiles(diretory);
			
			// read files
			for(int fileId=0; fileId<files.length; fileId++){
				File currentFile = files[fileId];

				// read file
				BufferedReader buffer = new BufferedReader(
	        			new FileReader(currentFile));
				
				// each line of the file
	        	String line;

				// new distance file
	        	DistanceFile distFile = new DistanceFile();
				
				// read file lines (distances)
				while (buffer.ready()) {
					line = buffer.readLine();
					double distance = Double.parseDouble(line);
					distFile.addDistance(distance);
				}
				
				distFile.fileName = currentFile.getName();
				distanceFilesList.add(distFile);
				
				// close file
				buffer.close();
			} 
			
		} catch (IOException e) {
			System.out.println("Error opening input files.");
			e.printStackTrace();
		}
		
		return distanceFilesList;
	}
	
	/**
	 * Select a random number of trajectories from the list.
	 * Return a clone of them.
	 */
	public static ArrayList<Trajectory> selectRandomTrajectories(
			ArrayList<Trajectory> trajList, int randomNumber){
		ArrayList<Trajectory> randomList = new ArrayList<Trajectory>();

		Random rand = new Random();
		for(int i=0; i<randomNumber; i++){
			int index = rand.nextInt(trajList.size());
			Trajectory traj = trajList.get(index);
			randomList.add(traj.clone());
		}
		
		return randomList;
	}
	
	/**
	 * Creates and save a file with the original trajectory
	 * and its respective transformation
	 */
	public static void saveTransformationFile(Trajectory traj, Trajectory newTraj, String fileName) {
		String script = "";
		
		for(Point p : traj.getPointsList()){
			script += p.coordinate[0] + "," + p.coordinate[1] + "," + p.timeLong + "\n";
		}
		
		script += "#\n";
		
		for(Point p : newTraj.getPointsList()){
			script += p.coordinate[0] + "," + p.coordinate[1] + "," + p.timeLong + "\n";
		}
		
		saveFile(script, fileName);
	}
	
	/**
	 * Creates and save a file with the distances
	 */
	public static void saveDistanceFile(DistanceFile distFile){
		saveDistanceFile(distFile.getDistancesList(), distFile.fileName);
	}
	
	/**
	 * Creates and save a file with the distances
	 */
	public static void saveDistanceFile(ArrayList<Double> distancesList, String fileName){
		String script = "";
		for(Double val : distancesList){
			script += val.toString() + "\n";
		}
		saveFile(script, fileName);
	}

	/**
	 * Creates and save a file with the distances mean and standard deviation.
	 * Each output file line contains three columns: 
	 * the distance file name | the mean | and standard deviation.
	 */
	public static void saveDistancesAnalysisFile(String script, String fileName){
		saveFile(script, fileName);
	}
	
	/**
	 * Read the files inside a directory. Recursively read
	 * directories into other directory.
	 * 
	 * @param diretory
	 * @return File[] a list with the files read
	 */
	private static File[] openDirectoryFiles(File diretory) {  
	    List<File> fileList = new ArrayList<File>(); 
	    
	    File[] files = diretory.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        if (files[i].isDirectory()) {  
	            // add in the list the files found in 'files[i]':  
	            File[] recFiles = openDirectoryFiles(files[i]);  
	            for (int j = 0; j < recFiles.length; j++) { 
	            	fileList.add(recFiles[j]);  
	            }  
	        } else {  
	            // add in the list the file found in 'directory'  
	            fileList.add(files[i]);  
	        }  
	    }  
	      
	    // turn the List into a File[]:  
	    File[] filesFound = new File[fileList.size()];  
	    for (int i=0; i<fileList.size(); i++) {  
	        filesFound[i] = fileList.get(i);  
	    }  
	    
	    return filesFound;  
	} 
	
	/**
	 * Save the file to the disc folder.
	 * @param script The content of the file
	 * @param fileName Name of the file, with its extension
	 */
	private static void saveFile(String script, String fileName){
		File file = new File(ROOT_PATH + OUTPUT_FOLDER + fileName);

		try {
			
			file.createNewFile();
		/*	if(!file.exists()){  
				file.createNewFile();
	        // System.out.println("File '" + fileName + "' successfully created.");  
	        }else{  
	            System.out.println("File '" + fileName + "' already exists.");  
	        } 
			*/
			BufferedWriter buffer = 
					new BufferedWriter(new PrintWriter(file));
			buffer.write(script); 
			//buffer.flush();  
		    buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}

	/**
	 * Save this list of trajectories to disc files, 
	 * save every trajPerFile trajectories into a different file.
	 */
	public static void saveTrajectoriesFiles(
			ArrayList<Trajectory> trajectoryList, int trajPerFile) {
		
		int count=1;
		String script = "";
		int fileCount = trajPerFile;
		for(Trajectory traj : trajectoryList){
			script += "#\n";
			for(Point p : traj.getPointsList()){
				script += p.coordinate[0] + "," +
						  p.coordinate[1] + "," +
						  p.timeLong + "\n";
			}
			
			if(count==trajPerFile){
				count=1;
				
				// save the script into a file
				saveFile(script, ""+fileCount);
				fileCount += trajPerFile;
				script="";
			} else{
				count++;
			}
		}
	}
	
}
