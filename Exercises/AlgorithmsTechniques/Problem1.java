class Point{
	double x;
	double y;
	
	Point(double x, double y)
	{
		this.x=  x;
		this.y = y;
	}
}

class BruteForce{
	int INF = 100000;
	double min(double a, double b) {
		if(a<b) {
			return a;
		}
		else
			return b;
	}
	
	double rastojanie(Point a, Point b) {
		return Math.sqrt((a.x - b.x) *(a.x - b.x) + (a.y - b.y) *(a.y-b.y));
	}
	
	double najmalo_rastojanie(Point p[], int n) {
		int i, j;
		double best = INF;
		
		for(i = 0; i<n-1; i++)
		{
			for(j = i+1; j<n; j++)
			{
				best = Math.min(best, rastojanie(p[i], p[j]))l
			}
		}
		return best;
	}
}