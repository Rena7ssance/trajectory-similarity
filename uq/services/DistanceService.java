package uq.services;

import java.util.ArrayList;

import uq.distance.DISSIMDistanceCalculator;
import uq.distance.DTWDistanceCalculator;
import uq.distance.EDRDistanceCalculator;
import uq.distance.EDwPDistanceCalculator;
import uq.distance.ERPDistanceCalculator;
import uq.distance.EuclideanDistanceForMultiDimensionTimeSeriesCalculator;
import uq.distance.FrechetDistanceCalculator;
import uq.distance.LCSSDistanceCalculator;
import uq.distance.LIPDistanceCalculator;
import uq.distance.OWDDistanceCalculator;
import uq.distance.PDTWDistanceCalculator;
import uq.distance.POIOnRoadNetworkDistanceCaculator;
import uq.distance.STEDDistanceCalculator;
import uq.distance.STLCSSDistanceCalculator;
import uq.distance.STLCSSWithSigmoidDistanceCalculator;
import uq.distance.STLIPDistanceCalculator;
import uq.distance.TIDDistanceCalculator;
import uq.entities.Point;
import uq.entities.Trajectory;

/**
 * Service (proxy) to put together all the distance functions.
 * 
 * @author uqdalves
 *
 */
public class DistanceService {
	public String TRANSF_NAME = "";
	
	// EDwP Distance
	public void EDwP(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			EDwPDistanceCalculator EDwP = new EDwPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(EDwP.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "EDwP" + TRANSF_NAME);		
	}
		
	// EDwP Distance
	public void EDwP_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			EDwPDistanceCalculator EDwP = new EDwPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(EDwP.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "EDwP" + TRANSF_NAME);		
	}
	
	// DTW Distance
	public void DTW(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			DTWDistanceCalculator DTW = new DTWDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(DTW.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "DTW" + TRANSF_NAME);	
	}	
	
	// DTW Distance
	public void DTW_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			DTWDistanceCalculator DTW = new DTWDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(DTW.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "DTW" + TRANSF_NAME);	
	}
	
	// ERP Distance
	public void ERP(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			ERPDistanceCalculator ERP = new ERPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(ERP.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "ERP" + TRANSF_NAME);		
	}
	
	// ERP Distance
	public void ERP_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			ERPDistanceCalculator ERP = new ERPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(ERP.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "ERP" + TRANSF_NAME);		
	}
		
	// TID Distance			
	public void TID(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			TIDDistanceCalculator TID = new TIDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(TID.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "TID" + TRANSF_NAME);	
	}
	
	// TID Distance			
	public void TID_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			TIDDistanceCalculator TID = new TIDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(TID.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "TID" + TRANSF_NAME);	
	}	
	
	// OWD Distance	
	public void OWD(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){	
			OWDDistanceCalculator OWD = new OWDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(OWD.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "OWD" + TRANSF_NAME);		
	}
	
	// OWD Distance	
	public void OWD_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){	
			OWDDistanceCalculator OWD = new OWDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(OWD.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "OWD" + TRANSF_NAME);		
	}

	// STLIP Distance
	public void STLIP(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			STLIPDistanceCalculator STLIP = new STLIPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(STLIP.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "STLIP" + TRANSF_NAME);	
	}
	
	// STLIP Distance
	public void STLIP_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			STLIPDistanceCalculator STLIP = new STLIPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(STLIP.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "STLIP" + TRANSF_NAME);	
	}

	// PDTW Distance
	public void PDTWD(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			PDTWDistanceCalculator PDTW = new PDTWDistanceCalculator(5);  // threshold 5
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(PDTW.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "PDTW_5" + TRANSF_NAME);
	}
	
	// PDTW Distance
	public void PDTWD_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			PDTWDistanceCalculator PDTW = new PDTWDistanceCalculator(5);  // threshold 5
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(PDTW.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "PDTW_5" + TRANSF_NAME);
	}
		
	// LCSS Distance 
	public void LCSS(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList, double threshold){
			LCSSDistanceCalculator LCSS = new LCSSDistanceCalculator(threshold); 
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(LCSS.getDistance(r, s));
			}
			String t = threshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "LCSS_" + t + TRANSF_NAME);	
	}
	
	// LCSS Distance 
	public void LCSS_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList, double threshold){
			LCSSDistanceCalculator LCSS = new LCSSDistanceCalculator(threshold); 
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(LCSS.getDistance(r, s));
				}
			}
			String t = threshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "LCSS_" + t + TRANSF_NAME);	
	}
	
	// Frechet Distance		
	public void Frechet(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){	
			FrechetDistanceCalculator FRECH = new FrechetDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(FRECH.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "FRECHET" + TRANSF_NAME);
	}

	// Frechet Distance		
	public void Frechet_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){	
			FrechetDistanceCalculator FRECH = new FrechetDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(FRECH.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "FRECHET" + TRANSF_NAME);
	}
	
	// STED Distance			
	public void STED(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			STEDDistanceCalculator STED = new STEDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(STED.getDistance(r, s));
			}		
			FileService.saveDistanceFile(distancesList, "STED" + TRANSF_NAME);
	}

	// STED Distance			
	public void STED_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			STEDDistanceCalculator STED = new STEDDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(STED.getDistance(r, s));
				}
			}		
			FileService.saveDistanceFile(distancesList, "STED" + TRANSF_NAME);
	}
	
	// LIP Distance
	public void LIPD(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			LIPDistanceCalculator LIP = new LIPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(LIP.getDistance(r, s));
			}	
			FileService.saveDistanceFile(distancesList, "LIP" + TRANSF_NAME);
	}		

	// LIP Distance
	public void LIPD_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			LIPDistanceCalculator LIP = new LIPDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(LIP.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "LIP" + TRANSF_NAME);
	}

	// EDR Distance
	public void EDR(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList, double threshold){
			EDRDistanceCalculator EDR = new EDRDistanceCalculator(threshold); 
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(EDR.getDistance(r, s));
			}	
			String t = threshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "EDR_" + t + TRANSF_NAME);
	}
	
	// EDR Distance
	public void EDR_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList, double threshold){
			EDRDistanceCalculator EDR = new EDRDistanceCalculator(threshold); 
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(EDR.getDistance(r, s));
				}
			}
			String t = threshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "EDR_" + t + TRANSF_NAME);
	}
	
	// STLCSS with SIGMOID Distance
	public void STLCSS_SIGMOID(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList, 
			double distanceThreshold, long timeIntervalThreshold){
			STLCSSWithSigmoidDistanceCalculator STLCSS_SIG = 
					new STLCSSWithSigmoidDistanceCalculator(distanceThreshold, timeIntervalThreshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(STLCSS_SIG.getDistance(r, s));
			}			
			String d = distanceThreshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "STLCSS-SIGMOID_" + d + TRANSF_NAME);
	}		

	// STLCSS with SIGMOID Distance
	public void STLCSS_SIGMOID_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList, 
			double distanceThreshold, long timeIntervalThreshold){
			STLCSSWithSigmoidDistanceCalculator STLCSS_SIG = 
					new STLCSSWithSigmoidDistanceCalculator(distanceThreshold, timeIntervalThreshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(STLCSS_SIG.getDistance(r, s));
				}
			}		
			String d = distanceThreshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "STLCSS-SIGMOID_" + d + TRANSF_NAME);
	}	
	
	// STLCSS Distance
	public void STLCSS(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList, 
			double distanceThreshold, long timeIntervalThreshold){
			STLCSSDistanceCalculator STLCSS = 
					new STLCSSDistanceCalculator(distanceThreshold, timeIntervalThreshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(STLCSS.getDistance(r, s));
			}
			String d = distanceThreshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "STLCSS_" + d + TRANSF_NAME);
	}	

	// STLCSS Distance
	public void STLCSS_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList, 
			double distanceThreshold, long timeIntervalThreshold){
			STLCSSDistanceCalculator STLCSS = 
					new STLCSSDistanceCalculator(distanceThreshold, timeIntervalThreshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(STLCSS.getDistance(r, s));
				}
			}
			String d = distanceThreshold == 0.01 ? "01" : "0004";
			FileService.saveDistanceFile(distancesList, "STLCSS_" + d + TRANSF_NAME);
	}	
	
	// DISSIM Distance
	public void DISSIM(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			DISSIMDistanceCalculator DISSIM = new DISSIMDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(DISSIM.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "DISSIM" + TRANSF_NAME);
	}

	// DISSIM Distance
	public void DISSIM_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
			DISSIMDistanceCalculator DISSIM = new DISSIMDistanceCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(DISSIM.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "DISSIM" + TRANSF_NAME);
	}
	
	// POI Distance
	public void POI(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList,
			ArrayList<Point> poi, double threshold){
			POIOnRoadNetworkDistanceCaculator POI = 
					new POIOnRoadNetworkDistanceCaculator(poi, threshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(POI.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "POI" + TRANSF_NAME);
	}
	
	// POI Distance
	public void POI_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList, 
			ArrayList<Point> poi, double threshold){
			POIOnRoadNetworkDistanceCaculator POI = new 
					POIOnRoadNetworkDistanceCaculator(poi, threshold);
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(POI.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "POI" + TRANSF_NAME);
	}

	// Euclidean Distance 
	public void Euclidean(ArrayList<Trajectory> trajList, ArrayList<Trajectory> tranfTrajList){
			EuclideanDistanceForMultiDimensionTimeSeriesCalculator EUCLIDEAN = 
					new EuclideanDistanceForMultiDimensionTimeSeriesCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(int i=0; i<trajList.size(); i++){
				ArrayList<Point> r = trajList.get(i).getPointsList();
			    ArrayList<Point> s = tranfTrajList.get(i).getPointsList();
			    distancesList.add(EUCLIDEAN.getDistance(r, s));
			}
			FileService.saveDistanceFile(distancesList, "EUCLIDEAN" + TRANSF_NAME);
	}
	
	// Euclidean Distance 
	public void Euclidean_2(ArrayList<Trajectory> sampleList, ArrayList<Trajectory> testList){
		EuclideanDistanceForMultiDimensionTimeSeriesCalculator EUCLIDEAN = 
				new EuclideanDistanceForMultiDimensionTimeSeriesCalculator();
			ArrayList<Double> distancesList = new ArrayList<Double>();
			for(Trajectory sample : sampleList){
				for(Trajectory test : testList){
					ArrayList<Point> r = sample.getPointsList();
				    ArrayList<Point> s = test.getPointsList();
				    distancesList.add(EUCLIDEAN.getDistance(r, s));
				}
			}
			FileService.saveDistanceFile(distancesList, "EUCLIDEAN" + TRANSF_NAME);
	}
	
	/**
	 * Run all the distance functions for the given trajectories lists. 
	 * Distances between every trajectory and its transformation. 
	 * Calculates the distance between every trajectory in the trajectories 
	 * list with its transformation in the transformation list.
	 * Save the outcomes to files.
	 */
	public void runDistanceFunctions(final ArrayList<Trajectory> trajList, 
			final ArrayList<Trajectory> transfTrajList){

		System.out.println("\n Running Distance Functions.. \n");
	
		EDwP(trajList, transfTrajList);
		System.out.println("EDwP");
		
		DTW(trajList, transfTrajList);
		System.out.println("DTW");
		
		ERP(trajList, transfTrajList);
		System.out.println("ERP");
		
		TID(trajList, transfTrajList);
		System.out.println("TID");
		
		OWD(trajList, transfTrajList);
		System.out.println("OWD");
		
		STLIP(trajList, transfTrajList);
		System.out.println("STLIP");
		
		PDTWD(trajList, transfTrajList);
		System.out.println("PDTW");
		
		Frechet(trajList, transfTrajList);
		System.out.println("FRECHET");
		
		STED(trajList, transfTrajList);
		System.out.println("STED");
		
		LIPD(trajList, transfTrajList);
		System.out.println("LIPD");
		
		DISSIM(trajList, transfTrajList);		
		System.out.println("DISSIM");
		
		EDR(trajList, transfTrajList, 0.01);
		System.out.println("EDR 01");
		
		EDR(trajList, transfTrajList, 0.0004);
		System.out.println("EDR 0004");
		
		LCSS(trajList, transfTrajList, 0.01);
		System.out.println("LCSS 01");
		
		LCSS(trajList, transfTrajList, 0.0004);
		System.out.println("LCSS 004");
		
		STLCSS(trajList, transfTrajList, 0.01, 1000);
		System.out.println("STLCSS 01");
		
		STLCSS(trajList, transfTrajList, 0.0004, 1000);
		System.out.println("STLCSS 0004");
		
		STLCSS_SIGMOID(trajList, transfTrajList, 0.01, 1000);
		System.out.println("STLCSS-SIGMOID 01");
		
		STLCSS_SIGMOID(trajList, transfTrajList, 0.0004, 1000);
		System.out.println("STLCSS-SIGMOID 0004");
		
		Euclidean(trajList, transfTrajList);
		System.out.println("EUCLIDEAN");
		
		// POIDistance
		
		System.gc();
		
		System.out.println("\n Run distances finished.. \n");
	}
		
	/**
	 * Run all the distance functions for the given trajectories lists. 
	 * Distances between sample and test trajectories. 
	 * Calculates the distance between every  trajectory in the sample 
	 * list with every trajectory in the test list.
	 * Save the outcomes to files.
	 */
	public void runDistanceFunctions_2(ArrayList<Trajectory> sampleList, 
			ArrayList<Trajectory> testList){

		System.out.println("\n Running Distance Functions.. \n");
	
		EDwP_2(sampleList, testList);
		System.out.println("EDwP");
		
		DTW_2(sampleList, testList);
		System.out.println("DTW");
		
		ERP_2(sampleList, testList);
		System.out.println("ERP");
		
		TID_2(sampleList, testList);
		System.out.println("TID");
		
		OWD_2(sampleList, testList);
		System.out.println("OWD");
		
		STLIP_2(sampleList, testList);
		System.out.println("STLIP");
		
		PDTWD_2(sampleList, testList);
		System.out.println("PDTW");
		
		Frechet_2(sampleList, testList);
		System.out.println("FRECHET");
		
		STED_2(sampleList, testList);
		System.out.println("STED");
		
		LIPD_2(sampleList, testList);
		System.out.println("LIPD");
		
		DISSIM_2(sampleList, testList);		
		System.out.println("DISSIM");
		
		EDR_2(sampleList, testList, 0.01);
		System.out.println("EDR 01");
		
		EDR_2(sampleList, testList, 0.0004);
		System.out.println("EDR 0004");
		
		LCSS_2(sampleList, testList, 0.01);
		System.out.println("LCSS 01");
		
		LCSS_2(sampleList, testList, 0.0004);
		System.out.println("LCSS 004");
		
		STLCSS_2(sampleList, testList, 0.01, 1000); 
		System.out.println("STLCSS 01");
		
		STLCSS_2(sampleList, testList, 0.0004, 1000);
		System.out.println("STLCSS 0004");
		
		STLCSS_SIGMOID_2(sampleList, testList, 0.01, 1000); 
		System.out.println("STLCSS-SIGMOID 01");
		
		STLCSS_SIGMOID_2(sampleList, testList, 0.0004, 1000);
		System.out.println("STLCSS-SIGMOID 0004");
		
		Euclidean_2(sampleList, testList);
		System.out.println("EUCLIDEAN");
		
		// POIDistance_2
		
		System.gc();
		
		System.out.println("\n Run distances finished.. \n");
	}
	
	/**
	 * Make a clone of this list of points.
	 */
	public static ArrayList<Point> clonePointsList(ArrayList<Point> list){
		ArrayList<Point> new_list = new ArrayList<Point>();
		
		for(Point p : list){
			Point p_clone = p.clone();
			new_list.add(p_clone);
		}
		
		return new_list;
	}
}
