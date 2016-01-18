/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uq.distance;

import java.util.ArrayList;

import uq.entities.Point;
import uq.services.DistanceService;

/**
 *
 * @author Administrator
 */
public class STLCSSDistanceCalculator implements SequenceDistanceCalculator{

    double Distance;
	double Time;
    long startTime1;
    long startTime2;
        
	public STLCSSDistanceCalculator(double distanceThreshold, long timeIntervalThreshold){
		Distance=distanceThreshold;
        Time=timeIntervalThreshold;
	}
	
	@Override
	public double getDistance(ArrayList<Point> r, ArrayList<Point> s){
		 // make sure the original objects will not be changed
		ArrayList<Point> r_clone = DistanceService.clonePointsList(r);
		ArrayList<Point> s_clone = DistanceService.clonePointsList(s);
		
		// Time range (parameters - given)
		Time = getTimeEnd(r_clone, s_clone);
		startTime1=r_clone.get(0).timeLong;
        startTime2=s_clone.get(0).timeLong;
        
		return getSTLCSS(r_clone, s_clone); 
	}

	private double getSTLCSS(ArrayList<Point> r, ArrayList<Point> s){

		double[][] LCSSMetric = new double[r.size() + 1][s.size() + 1];
		
		for (int i = 0; i <= r.size(); i++){
			LCSSMetric[i][0] = 0;
		}
		for (int i = 0; i <= s.size(); i++){
			LCSSMetric[0][i] = 0;
		}
		
		
		LCSSMetric[0][0] = 0;
		
		
		for (int i = 1; i <= r.size(); i++){
			for (int j = 1; j <= s.size(); j++){
				if (subcost(r.get(i - 1), s.get(j - 1)) == 1){
					LCSSMetric[i][j] = LCSSMetric[i - 1][j - 1] + 1;
				}else{
					LCSSMetric[i][j] = max(LCSSMetric[i][j - 1], LCSSMetric[i - 1][j]);
				}
				
			}
		}		
                
                double lcss= LCSSMetric[r.size()][s.size()];
                
                double distanceV=1-(lcss/Math.min(r.size(), s.size()));
		
		return distanceV;
	}
	
	private double max(double a, double b){
		if (a >= b){
			return a;
		}else{
			return b;
		}
	}
	
	private int subcost(Point p1, Point p2){
		boolean isSame=true;
		for(int i=0;i<p1.dimension;i++){
			if(Math.abs(p1.coordinate[i]-p2.coordinate[i])>Distance){
				isSame=false;
			}
		}
                
                if(Math.abs((p1.timeLong-startTime1)-(p2.timeLong-startTime2))>Time){
                    isSame=false;
                }
		
		if(isSame){
			return 1;
		}
		return 0;
	}
    
	/**
	 *  Get final time period tn
	 */
	private long getTimeEnd(ArrayList<Point> r, ArrayList<Point> s){
		// Get the trajectory with earliest last point
		long tn = s.get(s.size()-1).timeLong < r.get(r.size()-1).timeLong ? 
				s.get(s.size()-1).timeLong : r.get(r.size()-1).timeLong;
		return tn;
	}
}




