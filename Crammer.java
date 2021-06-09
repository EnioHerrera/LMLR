package examples.bookTrading;
import java.text.DecimalFormat;

public class Crammer{
	
	public static void main(String[] args) {
		
	}
	
public double [] getLR(double[] x1, double[] y){
	int n;
	double SUMX=0,SUMY=0,SUMXY=0,SUMX2=0;
	n=x1.length;
	for(int i=0; i < n; i++) {
		SUMX= SUMX + x1[i];
		SUMY= SUMY + y[i];
		SUMXY= SUMXY + x1[i]*y[i];
		SUMX2= SUMX2 + x1[i]*x1[i];
	}

	double B1, B0;
	double[] LR = new double[2];
	B1= (n*SUMXY-SUMX*SUMY)/(n*SUMX2-SUMX*SUMX);
	B0= (SUMY*SUMX2-SUMX*SUMXY)/(n*SUMX2-SUMX*SUMX);
	LR[0]=(SUMY*SUMX2-SUMX*SUMXY)/(n*SUMX2-SUMX*SUMX);
	LR[1]=(n*SUMXY-SUMX*SUMY)/(n*SUMX2-SUMX*SUMX);
	return LR;
}

}


