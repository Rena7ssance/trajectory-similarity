/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uq.transformation;

import java.util.ArrayList;

import uq.entities.Point;
/**
 *
 * @author uqhsu1
 */
public class WholeTrajectoryScaleTransformation implements TransformationInterface{
    double translation;
    
    public WholeTrajectoryScaleTransformation(){}
    public WholeTrajectoryScaleTransformation(double timeRatio){
        translation=timeRatio;
    }
    
    public ArrayList<Point> getTransformation(ArrayList<Point> list, ArrayList<Point> escapeList){return null;}
    
    public ArrayList<Point> getTransformation(ArrayList<Point> list){
        ArrayList<Point> result=new ArrayList<Point>();
        long startTime=list.get(0).timeLong;
        
        assert(((Point)(list.get(0))).dimension==2);
        
        for(int i=0;i<list.size();i++){
            if(i==0){
                result.add((Point)list.get(i));
            }else{
                double timelong=(((Point)list.get(i)).timeLong-((Point)list.get(0)).timeLong)*translation+startTime;
                Point temp=new Point(list.get(i).coordinate,(long)timelong);
                result.add(temp);
            }
        }
        
        return result;
    }
}
