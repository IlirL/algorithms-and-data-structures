class BruteForce{
	int dali_se_napagaat(int i1, int j1, int i2, int j2) {
		if(i1 == i2) //ista redica
			return 1;
		if(j1 == j2) //ista kolona
			return 1;
		if(Math.abs(i1-i2) == Math.abs(j1 - j2))//ista dijagonala
			return 1;
		
		return 0;
	}
	
	int broj_na_nacini() {
		int i1, j1, i2, j2;
		int rezultat = 0;
		
		for (i1 = 0; i1 < 8; i1++) {
			for (j1 = 0; j1 < 8; j1++) {
			for (i2 = 0; i2 < 8; i2++) {
			for (j2 = 0; j2 < 8; j2++) {
			if (dali_se_napagaat(i1, j1, i2, j2)==0) {
			rezultat++;
			}
			}
			}
			}
			}
			return rezultat;
	}
}