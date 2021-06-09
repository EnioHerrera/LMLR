package examples.bookTrading;
import java.text.DecimalFormat;

public class AlgebraVectorial{
	
	public double [][] SetX(double[] x1,double[] x2){
		int n=x1.length;
		double[][] X=new double[n][3];
		for(int i=0;i<n;i++){
			for(int j=0;j<3;j++){
				if(j==0){
					X[i][j]=1;
				}else if(j==1){
			 		X[i][j]=x1[i];
				}else {
					X[i][j]=x2[i];
				}
			}
		}
		return X;
	}//end method

	public double [][] SetX(double[] x1,double[] x2,double[] x3){
		int n=x1.length;
		double[][] X=new double[n][4];
		for(int i=0;i<n;i++){
			for(int j=0;j<4;j++){
				if(j==0){
					X[i][j]=1;
				}else if(j==1){
			 		X[i][j]=x1[i];
				}else if(j==2){
					X[i][j]=x2[i];
				}else{
					X[i][j]=x3[i];
				}
			}
		}
		return X;
	}//end method

	public double [][] Transpose(double[] x1,double[] x2){
		int n=x1.length;
		double[][] XT=new double[3][n];
		for(int i=0;i<3;i++){
			for(int j=0;j<n;j++){
				if(i==0){
					XT[i][j]=1;
				}else if(i==1){
					XT[i][j]=x1[j];
				}else{
					XT[i][j]=x2[j];
				}
			}
		}
		return XT;
	}//end method	

	public double [][] Transpose(double[] x1,double[] x2,double[] x3){
		int n=x1.length;
		double[][] XT=new double[4][n];
		for(int i=0;i<4;i++){
			for(int j=0;j<n;j++){
				if(i==0){
					XT[i][j]=1;
				}else if(i==1){
					XT[i][j]=x1[j];
				}else if(i==2){
					XT[i][j]=x2[j];
				}else{
					XT[i][j]=x3[j];
				}
			}
		}
		return XT;
	}//end method	

	public double [][] Product(double[][] X,double[][] XT,int arguments,int n){
		double[][] XXT=new double[arguments][arguments];
		for(int i=0;i<arguments;i++){
			for(int j=0;j<arguments;j++){
				for(int k=0;k<n;k++){
					XXT[i][j]=XXT[i][j] + XT[i][k]*X[k][j];
				}
			}
		}
		return XXT;
	}//end method	

	public double [] Product(double[][] XT,double[] y,int arguments,int n){
		double[] XTY=new double[arguments];
		for(int i=0;i<arguments;i++){
			for(int j=0;j<n;j++){
				XTY[i]=XTY[i] + XT[i][j]*y[j];
			}
		}
		return XTY;
	}//end method	

	public static double[][] invert(double a[][]){
		int n = a.length;
            	double x[][] = new double[n][n];
            	double b[][] = new double[n][n];
            	int index[] = new int[n];
            	for (int i=0; i<n; ++i)
                	b[i][i] = 1;
		
		gaussian(a, index);

		// Update the matrix b[i][j] with the ratios stored
            	for (int i=0; i<n-1; ++i)
                	for (int j=i+1; j<n; ++j)
                    		for (int k=0; k<n; ++k)
                        		b[index[j]][k]-= a[index[j]][i]*b[index[i]][k];
		
		// Perform backward substitutions
            	for (int i=0; i<n; ++i)             {
                	x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
                	for (int j=n-2; j>=0; --j){
                    		x[j][i] = b[index[j]][i];
                    		for (int k=j+1; k<n; ++k){			
                        		x[j][i] -= a[index[j]][k]*x[k][i];
                    		}
				x[j][i] /= a[index[j]][j];
                	}
            	}
     		return x;
	}

	public static void gaussian(double a[][], int index[]){
		int n = index.length;
            	double c[] = new double[n];

     		// Initialize the index
            	for (int i=0; i<n; ++i)
                	index[i] = i;
	
		// Find the rescaling factors, one from each row
            	for (int i=0; i<n; ++i) {
                	double c1 = 0;
                	for (int j=0; j<n; ++j) {
                    		double c0 = Math.abs(a[i][j]);
                    		if (c0 > c1) c1 = c0;
                	}
                	c[i] = c1;
            	}	

		// Search the pivoting element from each column
            	int k = 0;
            	for (int j=0; j<n-1; ++j) {
                	double pi1 = 0;
                	for (int i=j; i<n; ++i)  {
	            	    	double pi0 = Math.abs(a[index[i]][j]);
                    		pi0 /= c[index[i]];
                    		if (pi0 > pi1) {
                        		pi1 = pi0;
                        		k = i;
                    		}
                	}
			// Interchange rows according to the pivoting order	
			int itmp = index[j];
                	index[j] = index[k];
                	index[k] = itmp;
                	for (int i=j+1; i<n; ++i) {
                    		double pj = a[index[i]][j]/a[index[j]][j];
				// Record pivoting ratios below the diagonal
                    		a[index[i]][j] = pj;	
				// Modify other elements accordingly
                    		for (int l=j+1; l<n; ++l)
                        		a[index[i]][l] -= pj*a[index[j]][l];
                	}

		}
	}//gaussiana

}//end class