package examples.bookTrading;
import java.text.DecimalFormat;

public class Perseptron{
	
	public static void main(String[] args) {
		
	}
	
public double [] getWs(double[] x1,double[] x2,double[] x3, double[] y,int arguments,double[] LR){

	int n=x1.length;
	double a=0.1;
	double[] W=new double[arguments];
	
	for(int i=0;i<arguments;i++){
		W[i]=0;
	}
	
	for(int i=0; i<n; i++){	
		double res=0, test=0;
		for(int j=0; j<arguments; j++){
			if(j==0)res=LR[j];
			if(j==1)res=res+LR[j]*x1[i];
			if(j==2)res=res+LR[j]*x2[i];
			if(j==3)res=res+LR[j]*x3[i];
		}//end for j
		test=Sigmoid(res);
		if(test!=y[i]){
		double check=0;
		int aux=i;
		for(int l=0; l<arguments;l++){
		if(aux>=n)aux=0;
		for(int j=0;j<arguments;j++){
			if(j==0)check=check+(y[aux]-test);
			if(j==1)check=check+(y[aux]-test)*x1[aux];
			if(j==2)check=check+(y[aux]-test)*x3[aux];
			if(j==3)check=check+(y[aux]-test)*x3[aux];
		}//end for j
		aux++;
		W[l]=W[l]-a*check;
		LR[l]=W[l];
		}//end if
		}//end for l
	}//end for i
	System.out.print("\n\n Resultante perseptron--------------\n");
	for(int j=0; j<arguments; j++){
		System.out.print(LR[j]+"\t");
	}
	System.out.print("\n--------------------------------------\n\n");
	return LR;
}//end getws

public double Sigmoid(double res){
	double sig=0;
	sig= Math.exp(res)/(1+ Math.exp(res));
	if(sig>0.50){
		sig=1;
	}else{
		sig=0;
	}
	return sig;
}//end getws

}


