package uq.entities;

import java.util.Date;

public class Point implements Cloneable {
	
	public Date time;
	public double[] coordinate;
	public int dimension;
	public String timeString="";
    public long timeLong;
        
        
	public Point(){}
		
        public Point(double[] x, long t){
            coordinate=new double[x.length];
            for(int i=0;i<x.length;i++){
            	coordinate[i] = x[i];
            }
            dimension = x.length;
            timeLong = t;
            time = new Date(timeLong);
            timeString = time.toString();
        }
        
	public Point(double[] x) {
            coordinate=new double[x.length];
            for(int i=0;i<x.length;i++){
		coordinate[i] = x[i];
            }
		dimension = x.length;
	}
        public Point(double[] x, String s) {
		coordinate=new double[x.length];
            for(int i=0;i<x.length;i++){
		coordinate[i] = x[i];
            }
		dimension = x.length;
                timeString=s;
	}
	
	public Point(double[] x, Date y) {
		time = y;
		coordinate=new double[x.length];
            for(int i=0;i<x.length;i++){
		coordinate[i] = x[i];
            }
		dimension = x.length;
		timeString = y.toString();
		timeLong = y.getTime();
	}

	public void setDimension(int d){
		dimension=d;
	}
        
        public boolean isSame(Point p){
            if(p.dimension!=this.dimension){
                return false;
            }
            
            for(int i=0;i<p.dimension;i++){
                if(this.coordinate[i]!=p.coordinate[i]){
                    return  false;
                }
            }
            
            if(this.timeString.compareTo(p.timeString)!=0){
                return false;
            }
            
            return true;
        }
        
        @Override
        public String toString(){
            return coordinate[0] + "-" + coordinate[1];
        }
        
        
        public double distanceTo(Point another)
	{
		double distance = 0.0;
		for (int count = 0, size = coordinate.length; count < size; ++count)
		{
			distance += Math.pow(coordinate[count] - another.coordinate[count],
			        2);
		}
		return Math.sqrt(distance);
	}
        
        /**
         * Makes an identical copy of this element
         */
        @Override
        public Point clone() {
			Point p_clone = new Point(coordinate,timeLong);
			
			return p_clone;
        }
}
