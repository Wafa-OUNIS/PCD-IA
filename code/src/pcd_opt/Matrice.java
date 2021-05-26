package pcd_opt;


public class Matrice {
  double [][] mat=null;
  
	public Matrice(int i ,int j)
	{mat=new double [i][j];}
		
	public void  setvalue(int i,int j,double d)
	{ this.mat[i][j]=d; }
	
	public double getvalue(int i,int j)
	{  return(mat[i][j]);}

    public void divbyd (double d)
    { for(int i=0;i<mat.length;i++)
    	for(int j=0;j<mat[0].length;j++)
    		mat[i][j]/=d;}
    
    public Matrice getnewmatrice(int row, int columns)
    {Matrice a = new Matrice(mat.length-1, mat[0].length-1);
	int k = -1, m = 0;
	
	for (int i=0; i<mat.length; i++)
	{k++;
     if (i == row) 
		{k--;
		 continue;}
		m = -1;		
		for (int j=0; j<mat[0].length; j++)
		{m++;
	       if (j==columns) 
			{m--;
		     continue;					
			}
		a.setvalue(k,m,this.getvalue(i,j));	}
	}
	return a; 	  } 
    
    public double getdeterminant()
    {Matrice a = null;
	long value = 0;	
	if (mat.length < 3 && mat[0].length < 3)
		return (this.getvalue(0,0)*this.getvalue(1,1) - this.getvalue(1,0)*this.getvalue(0,1));		
	for (int j=0; j<mat[0].length; j++)
	{	a = this.getnewmatrice(0,j);
		value += (int)Math.pow(-1,j)*(this.getvalue(0,j)*a.getdeterminant());	}
		return value;   }    
    

  public Matrice gettranspose()
  { Matrice a = new Matrice(this.mat.length, this.mat[0].length);
	double tmp = 0;	
	for (int i=0; i<a.mat.length; i++)
		for (int j=0; j<a.mat[0].length; j++)
		{
			tmp = this.getvalue(j,i);
			a.setvalue(i,j,tmp);
		}
    return a;  }

   public Matrice getinverse()
   {Matrice a = new Matrice(this.mat.length, this.mat[0].length);
	Matrice tmp = null;
	double det = this.getdeterminant();	
	for (int i=0; i<this.mat.length; i++)
		for (int j=0; j<this.mat[0].length; j++)
		{tmp = this.getnewmatrice(i,j);
		 a.setvalue(i,j,(int)Math.pow(-1,i+j)*(tmp.getdeterminant()/det));}
	return a.gettranspose(); }
   
   public Matrice multiply(Matrice matrix) throws Exception
   {
	Matrice MC;
	    
		int l,c;
		
		if(this == null || matrix == null){
			throw new Exception("L'une des deux matrices est à null");
		}

		
		if(this.mat[0].length != matrix.mat.length){
			throw new Exception("La multiplication de deux matrices n'est possible que si le nombre de colonne du premier est égal au nombre de ligne du second!!");
		}
		 
		 if(this.mat.length * this.mat[0].length < matrix.mat.length * matrix.mat[0].length){
			l= matrix.mat.length;
			c= matrix.mat[0].length;
		 }else{
			 l= this.mat.length;
			 c= this.mat[0].length;
		 }
		 
		 MC = new Matrice(l,c);
		 
		 l = 0;
	     for (int i = 0;i < this.mat.length;i++){ /// Ligne de MA
	    	 c = 0;
           for (int n = 0;n < matrix.mat[0].length;n++){ /// colonne de  MB
           	
               int calcul= 0;
               for (int m = 0;m < matrix.mat.length;m++){  /// colone de MA et ligne de MB
               calcul += this.mat[i][m] * matrix.mat[m][n];
                  // if (m == 0)
                  //	System.out.printf("    + ");
               }      
               MC.setvalue(l,c,calcul); 
               c++;
           }         
           l++;
	     }
        return MC;
   

}
}


  