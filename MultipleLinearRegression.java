package examples.bookTrading;
import java.text.DecimalFormat;

public class MultipleLinearRegression{
	
	public static void main(String[] args) {
		
	}

	public static void calculation(String s) {
		String separador=",";
		String[] S=s.split(separador);
		int sizep=S.length;
		int arguments=sizep+1;
		double[] P=new double[sizep];
		for(int i=0;i<sizep;i++){
			P[i]=Double.parseDouble(S[i]);
		}
		
		DataSet DS= new DataSet();
		double[] y= DS.gety();
		double[] x1= DS.getx1();
		double[] x2= DS.getx2();
		double[] x3= DS.getx3();

		int n;
		n=y.length;
		double[][] X=new double[n][arguments];
		double[][] XT=new double[arguments][n];
		double[][] XXT=new double[arguments][arguments];
		double[] XTY=new double[arguments];
		
		AlgebraVectorial AV= new AlgebraVectorial();
		Crammer CR= new Crammer();
		if(arguments==2){
			double[] LR=new double[arguments];
			LR=CR.getLR(x1,y);
			System.out.print("\n\nYhat: ");
			for(int i=0;i<arguments;i++){
				if(i==0)System.out.print(LR[i]+"\t");
				if(i==1)System.out.print(LR[i]+"x1\t");
			}
			result(LR,P,arguments);
		}else{ //beggins principal else
		if(arguments==3){
			X=AV.SetX(x1,x2);
			XT=AV.Transpose(x1,x2);
		}else if(arguments==4){
			X=AV.SetX(x1,x2,x3);
			XT=AV.Transpose(x1,x2,x3);
		}
		XXT=AV.Product(X,XT,arguments,n);
		XTY=AV.Product(XT,y,arguments,n);
		double[][] I=AV.invert(XXT);

		System.out.print("\n\nYhat: ");
		//Multiplicacion INV(X'X)X'Y = I*R2
		double[] MLR=new double[arguments];
		for(int i=0;i<arguments;i++){
			for(int j=0;j<arguments;j++){
				MLR[i]=MLR[i] + I[i][j]*XTY[j];
			}
			if(i==0)System.out.print(MLR[i]+"\t");
			if(i==1)System.out.print(MLR[i]+"x1\t");
			if(i==2)System.out.print(MLR[i]+"x2 \n");
			if(i==3)System.out.print(MLR[i]+"x3 \n");
		}
		result(MLR,P,arguments);	

		} //end principal else
	}

	public static void result(double [] R, double [] P,int arguments) {
		System.out.print("\nPrediccion: ");
		double prediccion=0;
		int j=0;
		for(int i=0;i<arguments;i++){
			if(i==0)prediccion=R[i];
			if(i>0){
				prediccion=prediccion+R[i]*P[j];
				j++;
			}
		}
		System.out.print(prediccion+"\n");

		System.out.print("\nSigmoid: ");
		double sig=0;
		sig= Math.exp(prediccion)/(1+ Math.exp(prediccion));
		System.out.print(sig+"  =  ");
		if(sig>0.50){
			sig=1;
		}else{
			sig=0;
		}
		System.out.print(sig+"\n");
	}

}


