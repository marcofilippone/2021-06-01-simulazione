package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void vertici(Map<String, Genes> idMap) {
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome "
				+ "FROM genes "
				+ "WHERE Essential='Essential'";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(!idMap.containsKey(res.getString("GeneID"))) {
					Genes genes = new Genes(res.getString("GeneID"), 
							res.getString("Essential"), 
							res.getInt("Chromosome"));
					idMap.put(res.getString("GeneID"), genes);
				}
			}
			res.close();
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Adiacenza> archi(Map<String, Genes> idMap){
		String sql = "SELECT DISTINCT i.GeneID1, i.GeneID2, i.Expression_Corr AS corr, g1.Chromosome, g2.Chromosome "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "WHERE g1.Essential='Essential' AND g2.Essential=g1.Essential AND i.GeneID1=g1.GeneID AND i.GeneID2=g2.GeneID "
				+ "AND i.GeneID1 != i.GeneID2";
		List<Adiacenza> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(idMap.containsKey(res.getString("i.GeneID1")) && idMap.containsKey(res.getString("i.GeneID2"))) {
					Adiacenza a = new Adiacenza( idMap.get(res.getString("i.GeneID1")), idMap.get(res.getString("i.GeneID2")), res.getDouble("corr"), res.getInt("g1.Chromosome"), res.getInt("g2.Chromosome") );
					result.add(a);
				}
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
