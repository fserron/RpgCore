package backend.entities.enumerators;


public class Rank {
	
	private Ranking ranking;
	
	private Ranking rankingInhumano; //Por defecto, para humanos, sera null.
	
	public Rank(){
		this.ranking = Ranking.F;
		this.ranking.setBonificador(0);
	}
	
	public Rank(Ranking ranking, boolean inhumano){
		if (inhumano)
			this.rankingInhumano = ranking;
		else
			this.ranking = ranking;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public Ranking getRankingInhumano() {
		return rankingInhumano;
	}

	public void setRankingInhumano(Ranking rankingInhumano) {
		this.rankingInhumano = rankingInhumano;
	}

	public static Rank parseRanking(String value) throws NumberFormatException{
		Rank rank = new Rank();
		if (value.equals("A") || value.equals("a"))
			rank.setRanking(Ranking.A);
		else if (value.equals("B") || value.equals("b"))
			rank.setRanking(Ranking.B);
		else if (value.equals("C") || value.equals("c"))
			rank.setRanking(Ranking.C);
		else if (value.equals("D") || value.equals("d"))
			rank.setRanking(Ranking.D);
		else if (value.equals("E") || value.equals("e"))
			rank.setRanking(Ranking.E);
		else if (value.equals("F") || value.equals("f"))
			rank.setRanking(Ranking.F);
		else {
			throw new NumberFormatException();
		}
		return rank;
	}
	
	public static Rank parseRanking(Integer value) throws NumberFormatException{
		Rank rank = new Rank();
		if (value.equals(6))
			rank.setRanking(Ranking.A);
		else if (value.equals(5))
			rank.setRanking(Ranking.B);
		else if (value.equals(4))
			rank.setRanking(Ranking.C);
		else if (value.equals(3))
			rank.setRanking(Ranking.D);
		else if (value.equals(2))
			rank.setRanking(Ranking.E);
		else if (value.equals(1))
			rank.setRanking(Ranking.F);
		else {
			throw new NumberFormatException();
		}
		return rank;
	}
	
	public int toInt(){
		return this.ranking.numericValue;
	}
	
	public String toString(){
		StringBuilder rankString = new StringBuilder();
		
		rankString.append("Ranking: ");
		rankString.append(ranking.toString());
		
		if (ranking.getBonificador() > 0){
			rankString.append(" Con un bonificador de : ");
			rankString.append(ranking.getBonificador());
		}
		
		if (rankingInhumano != null){
			rankString.append("/nRanking Inhumano: ");
			rankString.append(rankingInhumano.toString());
			
			if (rankingInhumano.getBonificador() > 0){
				rankString.append(" Con un bonificador de : ");
				rankString.append(rankingInhumano.getBonificador());
			}
		}
		
		return rankString.toString();
	}
	
	public boolean esInhumano(){
		if (this.rankingInhumano != null)
			return true;
		else
			return false;
	}

	public enum Ranking{
		A(6),
		B(5),
		C(4),
		D(3),
		E(2),
		F(1);
		
		private Integer numericValue;
		private Integer bonificador;
		
		private Ranking(Integer numericValue) {
			this.numericValue = numericValue;
		}

		public Integer getNumericValue() {
			return numericValue;
		}

		public Integer getBonificador() {
			return bonificador;
		}

		public void setBonificador(Integer bonificador) {
			this.bonificador = bonificador;
		}
		
		
	}
	
}
