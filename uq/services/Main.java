/**
 * 
 */
package uq.services;

import java.util.ArrayList;

import uq.entities.DistanceFile;
import uq.entities.Trajectory;
import uq.transformation.TimeShiftTransformation;

/**
 * @author uqdalves
 *
 */
public class Main {
	private static final double PI = Math.PI;
	
	// test parameters
	private static double [] parameters;
	
	// Get instance of the transformation functions
	private static TransformationService transfService = 
			new TransformationService();
	
	// Get instance of the distance functions
	private static DistanceService distService = 
			new DistanceService();

	/**
	 * Main
	 */
	public static void main(String[] args) {
		System.out.println("STARTED..\n");
		
		// ## Read the original trajectory files and re-sample them.
		// ## Change initial time to t=0 (zero)
		// ## Save the re-sampled trajectories to new files in the output folder.
		// ## 100 trajectories per file.
		// reSampleTrajectories();
		
		// ## Select a random number of trajectories from the sample folder. 
		// ## Read from the trajectory files (re-sampled trajectories). 
		// ## Save them to a file in the output folder.
		// selectAndSaveRandomTrajectories(100);
		
		// ## Calculate the distances BEFORE transformation.
		// ## Compare sample trajectories (1000) with test trajectories (100).
		// ## Run the distance functions and save the outcomes.
		// ## Each file is one experiment (transformation/distance/parameter).
		// ## These are the 'ground truth' files for every distance function.
		// calculaDistancesBeforeTransformation();
		
		// ## Calculate the distances AFTER transformation.
		// ## Compare sample trajectories (1000) with test trajectories (100)
		// ## for every transformation and parameter.
		// ## Run the distance functions after the transformation and save the outcomes.
		// ## Each file is one experiment (transformation/distance/parameter).
		// calculaDistancesAfterTransformation();
		
		// ## Calculate the distances between every trajectory and its transformation.
		// ## Read original trajectories, run the transformations and compare the
		// ## transformed trajectory with the original. Save the results to files.
		// ## Each file is one experiment (transformation/distance/parameter).
		// calculateDistancesForEveryTrajectory();
		
		// ## Normalize the distances for every experiment. 
		// ## Each file corresponds to one experiment.
		// ## Divide each distance per the maximum distance value in the file.
		// ## Save the outcomes to files (if required - true). 
		// normalizeDistances(true);
		
		// ## Read the distance files and calculate the mean and standard deviation
		// ## Save the results into a single file if required (true)
		// ## Each file line contains the distance file name and 
		// ## the mean and standard deviation.
		// ## Should read files from one transformation at time, so it would
		// ## save one file per transformation.
		// calculateDistancesMeanAndStd(true);
		
		// ## Compare the distances values for a certain distance function 
		// ## and print the variation outcomes.
		// ## Compare with the ground truth (ground truth file for
		// ## the respective distance function)
		// ## Output the results on sysout
		// runDistanceAnalysis();

		System.out.println("FINISHED");
	}
	
	/**
	 * Read the distance files and calculate the distances mean and standard deviation.
	 * Save the results into a single file if required (true).
	 * Each output file line contains three columns: 
	 * the distance file name | the mean | and standard deviation.
	 * @param save Whether or not to save the output file
	 */
	public static void calculateDistancesMeanAndStd(boolean save) {
		ArrayList<DistanceFile> distanceFiles = 
				FileService.readDistances(false);
	
		System.out.println("Distance Files Read: " + distanceFiles.size() + "\n");
		
		String fileScript = "";
		String fileName = "space_scale_transformation"; // transformation_name
		
		for(DistanceFile file : distanceFiles){
			// get mean and standard deviation of the distances
			double mean = mean(file.getDistancesList());
			double std  = std(file.getDistancesList(), mean);
			
			fileScript += file.fileName + " " + mean + " " + std + "\n";
			
			System.out.println(file.fileName);
			System.out.println("Mean: " + mean);
			System.out.println("Std :  " + std);
			System.out.println();
		}
		// should be one file per transformation function.
		if(save) FileService.saveDistancesAnalysisFile(fileScript, fileName); 
	}

	/**
	 * Calculate the distances BEFORE transformation.
	 * Compare sample trajectories with test trajectories.
	 * Run the distance functions and save the files to the output folder.
	 * These are the 'ground truth' files for every distance function.
	 */
	public static void calculaDistancesBeforeTransformation(){
		// read sample and test trajectories from folders
		ArrayList<Trajectory> sampleList = 
				FileService.readSampleTrajectories(); // read from sample folder
		ArrayList<Trajectory> testList = 
				FileService.readTestTrajectories();  // read from test folder
		
		System.out.println("Sample Data: " + sampleList.size());
		System.out.println("Test Data: " + testList.size());
		
		// run the distance functions
		distService.runDistanceFunctions_2(sampleList, testList);
	}
	
	/**
	 * Calculate the distances AFTER transformation.
	 * Compare sample trajectories with test trajectories for every transformation and parameter.
	 * Run the distance functions and save the files to the output folder.
	 */
	public static void calculaDistancesAfterTransformation(){
		// read sample and test trajectories from folders
		ArrayList<Trajectory> sampleList = 
				FileService.readSampleTrajectories();
		ArrayList<Trajectory> testList = 
				FileService.readTestTrajectories();

		System.out.println("Sample Data: " + sampleList.size());
		System.out.println("Test Data: " + testList.size());

		// Calculate the distances for every transformation and every parameter.
		// One file for every distance function and parameter
		ArrayList<Trajectory> newTestList;

		// Add Points Transformation
		parameters = new double[]{0.25,0.5,1.0,2.0};
		for(double addRate : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.addPointsTransformation(testList, addRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_add_pts_" + (int)(addRate*100) + "pct";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
		
		// Delete Points Transformation
		parameters = new double[]{0.1,0.2,0.4,0.8};
		for(double deleteRate : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.deletePointsTransformation(testList, deleteRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_delete_pts_" + (int)(deleteRate*100) + "pct";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
	
		// Different Time Rate Transformation
		parameters = new double[]{1,2,4,8};
		for(double sampleRate : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.diffSampleRateTransformation(testList, (int)sampleRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_dif_time_rate_" + (int)(sampleRate) + "pts";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
		
		// Whole Scale Transformation
		parameters = new double[]{0.25,0.5,2.0,4.0};
		for(double scaleRatio : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.scaleTransformation(testList, scaleRatio);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_scale_" + (int)(scaleRatio*100) + "pct";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
		
		// Time scale Transformation
		parameters = new double[]{0.25,0.5,2.0,4.0};
		for(double timeRatio : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.timeScaleTransformation(testList, timeRatio);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_time_scale_" + (int)(timeRatio*100) + "pct";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
		
		// Rotation Transformation
		parameters = new double[]{PI/6,PI/4,PI/3,PI/2,3*PI/4,PI};
		for(double angle : parameters){
			sampleList = FileService.readSampleTrajectories();
			testList = FileService.readTestTrajectories();
			
			// get the transformation
			newTestList = transfService.rotationTransformation(testList, angle);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_rotation_" + (int)Math.round(Math.toDegrees(angle)) + "dgr";
			distService.runDistanceFunctions_2(sampleList, newTestList);
		}
		
		// Add Noise Transformation
		double [] addRate = new double[]{0.01,0.02,0.05,0.1};
		double [] noiseDist = new double[]{0.02,0.05,0.1};
		for(double addRateParam : addRate){
			for(double noiseDistParam : noiseDist){
				sampleList = FileService.readSampleTrajectories();
				testList = FileService.readTestTrajectories();
				
				// get the transformation
				newTestList = transfService.addNoiseTransformation(testList, addRateParam, noiseDistParam);
				// calculate distances to transformed trajectories
				distService.TRANSF_NAME = "_add_noise_" + (int)(addRateParam*100) + 
						"pct_" + (int)(noiseDistParam*100) + "d";
				distService.runDistanceFunctions_2(sampleList, newTestList);
			}
		}
	}

	/**
	 * Calculate the distances between every trajectory and its transformation.
	 * Read original trajectories, run the transformations and compare the
	 * transformed trajectory with the original. Save the results to files.
	 */
	public static void calculateDistancesForEveryTrajectory() {
		// Calculate the distances for every transformation and every parameter.
		// One file for every distance function and parameter

		// Add Points Transformation
		parameters = new double[]{0.25,0.5,1.0,2.0};
		for(double addRate : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.addPointsTransformation(trajList, addRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_add_pts_" + (int)(addRate*100) + "pct";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Delete Points Transformation
		parameters = new double[]{0.1,0.2,0.4,0.8};
		for(double deleteRate : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.deletePointsTransformation(trajList, deleteRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_delete_pts_" + (int)(deleteRate*100) + "pct";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Different Time Rate Transformation
		parameters = new double[]{2,4,8,16};
		for(double sampleRate : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.diffSampleRateTransformation(trajList, (int)sampleRate);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_dif_time_rate_" + (int)(sampleRate) + "pts";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Whole Scale Transformation
		parameters = new double[]{0.25,0.5,2.0,4.0};
		for(double scaleRatio : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.scaleTransformation(trajList, scaleRatio);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_scale_" + (int)(scaleRatio*100) + "pct";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Time scale Transformation
		parameters = new double[]{0.25,0.5,2.0,4.0};
		for(double timeRatio : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.timeScaleTransformation(trajList, timeRatio);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_time_scale_" + (int)(timeRatio*100) + "pct";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Rotation Transformation
		parameters = new double[]{PI/6,PI/4,PI/3,PI/2,3*PI/4,PI};
		for(double angle : parameters){
			// read all the trajectories from files
			ArrayList<Trajectory> trajList = 
				FileService.readAllTrajectories();
			// Get the transformation
			ArrayList<Trajectory> transfTrajList = 
					transfService.rotationTransformation(trajList, angle);
			// calculate distances to transformed trajectories
			distService.TRANSF_NAME = "_rotation_" + (int)Math.round(Math.toDegrees(angle)) + "dgr";
			distService.runDistanceFunctions(trajList, transfTrajList);
		}
		
		// Add Noise Transformation
		double [] addRate = new double[]{0.01,0.02,0.05,0.1};
		double [] noiseDist = new double[]{0.02,0.05,0.1};
		for(double addRateParam : addRate){
			for(double noiseDistParam : noiseDist){
				// read all the trajectories from files
				ArrayList<Trajectory> trajList = 
					FileService.readAllTrajectories();
				// Get the transformation
				ArrayList<Trajectory> transfTrajList = 
						transfService.addNoiseTransformation(trajList,  addRateParam, noiseDistParam);
				// calculate distances to transformed trajectories
				distService.TRANSF_NAME = "_add_noise_" + (int)(addRateParam*100) + 
						"pct_" + (int)(noiseDistParam*100) + "d";
				distService.runDistanceFunctions(trajList, transfTrajList);
			}
		}
		
	}

	/**
	 * Normalize the distances for every experiment (every distance function). 
	 * Divide each distance per the maximum distance value of distance function.
	 * Must read all files from the same distance function for a given transformation
	 * function regardless the parameter.
	 * 
	 * @param save Whether to save the outcomes to files. 
	 * Files name same as the original file + _norm. Save to the 
	 * Output folder.
	 */
	public static void normalizeDistances(boolean save) {
		// read the files
		// files must be from the same distance measure 
		ArrayList<DistanceFile> distanceFilesList = 
				FileService.readDistances(false);
		
		System.out.println("Files Read: " + distanceFilesList.size() + "\n");
		System.out.println(" Running normalization..");
		
		// Get the maximum value from all files.
		double max = 0.0;
		for(DistanceFile distFile : distanceFilesList){
			for(double dist : distFile.getDistancesList()){
				if(dist > max) max = dist;
			}
		}

		// Divide the distances for max, and save the new files if required.
		for(DistanceFile distFile : distanceFilesList){
			// the new file for the normalized distances
			DistanceFile normalizedDistFile = 
					new DistanceFile(distFile.fileName + "_norm");
			
			double norm_dist=0;
			for(double dist : distFile.getDistancesList()){
				norm_dist = (max == 0.0 ? 0.0 : dist/max);
				if(!Double.isNaN(norm_dist))
					normalizedDistFile.addDistance(norm_dist);
			}
			// Save the file if required
			if(save) FileService.saveDistanceFile(normalizedDistFile);
			
			System.gc();
		}
		
		System.out.println("\n Finished normalization.. \n");
	}
	
	/**
	 * Compare the distances values for a certain distance function
	 * and print the variation outcomes.
	 */
	public static void runDistanceAnalysis(){
		ArrayList<DistanceFile> distanceFiles = 
				FileService.readDistances(false);
		DistanceFile groundTruthFile = 
				FileService.readDistances(true).get(0);
	
		System.out.println("ANALYSIS RESULTS: \n");
		for(DistanceFile file : distanceFiles){
			// for each distance file get the distances variation from the ground truth
			ArrayList<Double> distVariationList = 
					compareDistances(groundTruthFile.getDistancesList(), file.getDistancesList());
	
			// get mean and standard deviation of the distances variation
			double mean = mean(distVariationList);
			double std  = std(distVariationList, mean);
			
			System.out.println(file.fileName);
			System.out.println("Variation Mean: " + mean);
			System.out.println("Variation Std:  " + std);
			System.out.println();
		}
	}

	/**
	 * Read the original trajectory files from input 
	 * folder, shift the start time to 0 and safe them
	 * to new files.
	 */
	public static void reSampleTrajectories(){
		ArrayList<Trajectory> trajectoryList = 
				new ArrayList<Trajectory>();
		ArrayList<Trajectory> newTrajList = 
				new ArrayList<Trajectory>();
		
		// Read trajectory files 
		trajectoryList = FileService.readOriginalTrajectories();
		
		System.out.println("Read Trajectories: " + trajectoryList.size());
	
		// Shift time to start at t=0
		TimeShiftTransformation timeShiftTransf = 
				new TimeShiftTransformation(0);
		for(Trajectory traj : trajectoryList){
			Trajectory newTraj = new Trajectory();
			newTraj.setPointsList( timeShiftTransf.getTransformation(traj.getPointsList()) );
			
			newTrajList.add(newTraj);
		}
		
		System.out.println("Shift Trajectories: " + newTrajList.size());
		
		FileService.saveTrajectoriesFiles(newTrajList, 100);
		
		System.out.println("Finished..");
	}
	
	/**
	 * Select a random number of trajectories from the input 
	 * folder and save them to disc.
	 */
	public static void selectAndSaveRandomTrajectories(int numTrajectories) {
		ArrayList<Trajectory> sampleList = 
				FileService.readSampleTrajectories();
		System.out.println("Total Read: " + sampleList.size());
		
		ArrayList<Trajectory> randomTraj = 
				FileService.selectRandomTrajectories(sampleList, numTrajectories);
		
		FileService.saveTrajectoriesFiles(randomTraj, numTrajectories);
	}
	
	/**
	 * compare a list of distance with another (ground truth).
	 * Return a list of the variation for every distance.
	 */
	private static ArrayList<Double> compareDistances(ArrayList<Double> groundTruthList, ArrayList<Double> distanceList){
		ArrayList<Double> variationList = new ArrayList<Double>();
		
		for(int i=0; i<groundTruthList.size(); i++){
			double groundTruth = groundTruthList.get(i);
			double distance = distanceList.get(i);
			
			double variation = Math.abs((distance - groundTruth)) / groundTruth;
					
			variationList.add(variation);		
		}
		
		return variationList;
	}
	
	/**
	 * Return the mean of the values
	 */
	private static double mean(ArrayList<Double> valuesList){
		double mean = 0;
		
		for(double val : valuesList){
			if(!Double.isNaN(val)) 
				mean += val;
		}
		
		mean = mean/valuesList.size();
		
		return mean;
	}
	
	/**
	 * Return the standard deviation of the values
	 */
	private static double std(ArrayList<Double> valuesList, double mean){
		double std = 0;
		//double mean = mean(valuesList);
		int numNaN = 0;
		for(double val : valuesList){
			if(!Double.isNaN(val)){
				std += Math.pow(val - mean, 2);
			} else numNaN++;
		}
		
		std = Math.sqrt(std/(valuesList.size()-1-numNaN));
		
		return std;
	}
}
