/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uq.distance;

import java.util.ArrayList;

import uq.entities.Point;
import uq.entities.ThreeDLine;
import uq.services.DistanceService;

/**
 *
 * @author Administrator
 */
public class STEDDistanceCalculator implements SequenceDistanceCalculator{
   
	@Override
	public double getDistance(ArrayList<Point> r, ArrayList<Point> s){
		 // make sure the original objects will not be changed
		ArrayList<Point> r_clone = DistanceService.clonePointsList(r);
		ArrayList<Point> s_clone = DistanceService.clonePointsList(s);
		
		return getSTED(r_clone, s_clone); 
	}
	
	private double getSTED(ArrayList<Point> r, ArrayList<Point> s){
        double duration=0;
        assert(r.size()>1&&s.size()>1);
        //System.out.println(r.size() + " " + s.size());
        double dR=r.get(r.size()-1).timeLong-r.get(0).timeLong;
        double dS=s.get(s.size()-1).timeLong-s.get(0).timeLong;
        
        if(dR<=0||dS<=0){
            return 0;
        }
        
        if(dR>dS){
            duration=dS;
        }else{
            duration=dR;
        }
        
        if(duration<0){
            int k=0;
        }
        
        double calculateTimeInterval=duration/1000;
        
        ArrayList<ThreeDLine> r3d=getThreeDLine(r);
        ArrayList<ThreeDLine> s3d=getThreeDLine(s);
        long rStartTime=r.get(0).timeLong;
        long sStartTime=s.get(0).timeLong;
        
        double sum=0;
        EuclideanDistanceCalculator eclidean=new EuclideanDistanceCalculator();
        
        for(int i=0;i<1000;i++){
            Point rCurrentPoint;
            Point sCurrentPoint;
            
            int rCurrentLine=0;
            int sCurrentLine=0;
            
            for(int j=0;j<r.size()-1;j++){
                if(rStartTime+i*calculateTimeInterval>=r.get(j).timeLong&&rStartTime+i*calculateTimeInterval<=r.get(j+1).timeLong){
                    rCurrentLine=j;
                    break;
                }
            }
            
            for(int j=0;j<s.size()-1;j++){
                if(sStartTime+i*calculateTimeInterval>=s.get(j).timeLong&&sStartTime+i*calculateTimeInterval<=s.get(j+1).timeLong){
                    sCurrentLine=j;
                    break;
                }
            }
            
            rCurrentPoint=r3d.get(rCurrentLine).getPointByTime((long)(rStartTime+i*calculateTimeInterval));
            sCurrentPoint=s3d.get(sCurrentLine).getPointByTime((long)(sStartTime+i*calculateTimeInterval));
            
            double distance=eclidean.getDistance(rCurrentPoint, sCurrentPoint);
            if(distance<0){
                int k=0;
            }
            
            sum+=distance;
        }
        
        
        return sum/Math.abs(duration);
    }
    
    private ArrayList<ThreeDLine> getThreeDLine(ArrayList<Point> r){
         ArrayList<ThreeDLine> result=new ArrayList<ThreeDLine>();
        
        if(r.size()<2){
            return result;
        }
        
        for(int i=0;i<r.size()-1;i++){
            ThreeDLine tempLine=new ThreeDLine(r.get(i),r.get(i+1));
            result.add(tempLine);
        }
        
        return result;
     }
    
}
