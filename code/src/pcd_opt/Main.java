
package pcd_opt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	static int nb_items=6082;
	static int nb_users=943;
	static int nb_genres=18;
    static Genre [] g=new Genre[nb_genres];
    static Item [] p=new Item[nb_items];
    static Matrice sig=new Matrice(nb_users,nb_users);
    private static int it_gr [][] =new int [nb_items][nb_genres];
    private static double user_it [][] =new double [nb_users][nb_items];
    
    
    public static void input (int [][] mat1,double [][] mat2) 
	{
    	for(int i=0;i<mat1.length;i++)
		for(int j=0;j<mat1[0].length;j++)
			mat1[i][j]=0; 
    	for(int i=0;i<mat2.length;i++)
    		for(int j=0;j<mat2[0].length;j++)
    			mat2[i][j]=Double.NaN; 
   	
	 BufferedReader buf=null;
	 BufferedReader buf2=null;
		try {	       
		   File file = new File("MovieGenre.txt");
		  FileReader fr=new FileReader(file);
		  buf= new BufferedReader (fr);
		
		  String ligne;
		 
		  while((ligne=buf.readLine())!=null)
		
		  { 
		    String[] c=ligne.split("\t");
		   int i=Integer.parseInt(c[0])-1;
		   int j=Integer.parseInt(c[1])-1;
		   mat1[i][j]=1;
           }
		  
		  File file2 = new File("uT20.base");
		  FileReader fr2=new FileReader(file2);
		  buf2= new BufferedReader (fr2);
		  
		  String ligne2;
			 
		  while((ligne2=buf2.readLine())!=null)
		
		  { 
		    String[] c=ligne2.split("\t");
		   int i=Integer.parseInt(c[0])-1;
		   int j=Integer.parseInt(c[1])-1;
		   mat2[i][j]=Double.parseDouble(c[2]);
           }
		  
		  
		  
		 
			     
	}catch (FileNotFoundException e) {
         e.printStackTrace();} 
	 catch (IOException e) {
		e.printStackTrace();
	}
	try{//buf.close();
	   buf2.close();
	}catch (IOException e) {
		e.printStackTrace();
	}catch (NullPointerException e){
		e.printStackTrace();} 		
	}
	
	public static void main(String[] args) throws Exception {
		 //INITIALISATION 	
		
		for(int i=0;i<nb_items;i++) p[i]=new Item();        
        for(int i=0;i<nb_genres;i++)  g[i]=new Genre();
        input(it_gr,user_it);  
         /*   int ct;
        for (int i=0;i<nb_items;i++)
        {  for (int j=0;j<nb_users;j++)
        	p[i].x[j]=user_it[j][i];
          ct=0;
          for(int j=0;j<nb_genres;j++)
        	   if (it_gr[i][j]==1) ct++;
          for (int j=0;j<nb_genres;j++) 
            { if(it_gr[i][j]==1) p[i].pzi[j]=(double)1/ct; 
        	  else (p[i].pzi)[j]=0; }
         }
        
        double [][]denom =new double [nb_users][nb_genres];
        for(int i=0;i<nb_users;i++)
      	  for(int j=0;j<nb_genres;j++)
      		  denom[i][j]=0;

        int s=0;
        while(s<nb_items)
        { for(int j=0;j<nb_genres;j++)  g[j].pi+=p[s].pzi[j]; 
         for(int gr=0;gr<nb_genres;gr++)
        	{for(int u=0;u<nb_users;u++)
        	  if(!(Double.isNaN(p[s].x[u])))
        		 {g[gr].m[u]+=p[s].pzi[gr]*p[s].x[u];
        		  denom[u][gr]+=p[s].pzi[gr];}}
         s++;}
        
        for(int gr=0;gr<nb_genres;gr++)
            for(int u=0;u<nb_users;u++)
              { if(denom[u][gr]!=0) g[gr].m[u]/=denom[u][gr];
                else g[gr].m[u]=Double.NaN;}
 
        for(int gr=0;gr<nb_genres;gr++) g[gr].pi/=(double)nb_items;    
      
  //ALGO
        Matrice  vect=new Matrice(nb_users,1);
       double [] denominator=new double [nb_items];    
       double  x=0;double d=0;
        int c=nb_users;
        int iter=0;
  while(iter<2)
  {  
	  //ETAPE E  
	for(int i=0;i<nb_items;i++) denominator[i]=0;
    for(int gr=0;gr<nb_genres;gr++)
      {
    	for(int it=0;it<nb_items;it++)
          {   
    	   for(int i=0;i<nb_users;i++)
             {if(!(Double.isNaN(p[it].x[i])))
             	{for(int j=0;j<nb_users;j++)
        	     {x=sig.getvalue(i,j)+p[it].pzi[gr]*(p[it].x[i]-g[gr].m[i])*(p[it].x[j]-g[gr].m[j]);
             	 sig.setvalue(i,j,x);}}
		      else c-=1;}
           if (c!=0) d=d+p[it].pzi[gr];}
    	  sig.divbyd(d);
    for(int it=0;it<nb_items;it++)
    	{ 
    	  double det=Math.abs(sig.getdeterminant());
   	        for(int j=0;j<nb_users;j++) vect.setvalue(j, 0, p[it].x[j]-g[gr].m[j]);
   	        sig=(sig.getinverse().multiply(vect));
   	        sig=(vect.gettranspose()).multiply(sig);
   	p[it].pzi[gr]=sig.getvalue(0,0);
   	p[it].pzi[gr]=Math.exp(-(1/2)*p[it].pzi[gr]);
   	p[it].pzi[gr]=p[it].pzi[gr]/(Math.sqrt(Math.pow((2*3.141592),nb_users)));
   	p[it].pzi[gr]=p[it].pzi[gr]/Math.sqrt(det);
   	p[it].pzi[gr]=p[it].pzi[gr]*p[it].pzi[gr];
   	 denominator[it]+=p[it].pzi[gr]; 
        }
    } 
    for(int it=0;it<nb_items;it++) 
    	for(int gr=0;gr<nb_genres;gr++)
    	{p[it].pzi[gr]=p[it].pzi[gr]/denominator[it];
    	if(Double.isNaN(p[it].pzi[gr]) || p[it].pzi[gr]<0)
            p[it].pzi[gr]=0; }
  
  //ETAME M
    
    for(int i=0;i<nb_users;i++)
  	  for(int j=0;j<nb_genres;j++)
  		  denom[i][j]=0;
     s=0;
    while(s<nb_items)
    { for(int j=0;j<nb_genres;j++)  g[j].pi+=p[s].pzi[j]; 
     for(int gr=0;gr<nb_genres;gr++)
    	{for(int u=0;u<nb_users;u++)
    	  if(!(Double.isNaN(p[s].x[u])))
    		 {g[gr].m[u]+=p[s].pzi[gr]*p[s].x[u];
    		  denom[u][gr]+=p[s].pzi[gr];}}
     s++;}
    
    for(int gr=0;gr<nb_genres;gr++)
        for(int u=0;u<nb_users;u++)
          { if(denom[u][gr]!=0) g[gr].m[u]/=denom[u][gr];
            else g[gr].m[u]=Double.NaN;}

    for(int gr=0;gr<nb_genres;gr++) g[gr].pi/=(double)nb_items; 
  }*/
 
   for(int i=0;i<nb_users;i++)
     { //System.out.print("  ");
     	for(int j=0;j<nb_items;j++)
         // som[i]+=p[i].pzi[j];
     	{ System.out.print(user_it[i][j]+"\t");}
     	System.out.print("\n");}
	}

}