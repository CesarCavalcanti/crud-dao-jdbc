package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	Connection conn;
	
	public SellerDaoJDBC(Connection coon) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller" 
					+"(Name,Email,BirthDate,BaseSalary,DepartmentId) "
							+"VALUES"
					+"(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1,seller.getName());
			st.setString(2,seller.getEmail());
			st.setDate(3,new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4,seller.getBaseSalary());
			st.setInt(5,seller.getDepartment().getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Erro inesperado, nenhuma linha foi afetada!");
			}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName"
					+ "FROM seller INNER JOIN department"
					+ "ON seller.DepartmentId = department.Id"
					+ "WHERE seller.Id = ?");
			
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dp = instanciarDepartment(rs);
				Seller sl = instanciarSeller(rs,dp);
				return sl;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName"
					+ "FROM seller INNER JOIN department"
					+ "ON seller.DepartmentId = department.Id"
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instanciarDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sl = instanciarSeller(rs, dep);
				list.add(sl);
				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName"
					+ "FROM seller INNER JOIN department"
					+ "ON seller.DepartmentId = department.Id"
					+ "WHERE DepartmentId = ?"
					+ "ORDER BY Name");
			
			st.setInt(1,department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instanciarDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sl = instanciarSeller(rs, dep);
				list.add(sl);
				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	private Department instanciarDepartment(ResultSet rs) throws SQLException {
		Department dp = new Department ();
		
		dp.setId(rs.getInt("DepartmentId"));
		dp.setName(rs.getString("DepName"));
		return dp;
	}

	private Seller instanciarSeller(ResultSet rs, Department dp) throws SQLException {
		Seller sl = new Seller ();
		sl.setId(rs.getInt("id"));
		sl.setName(rs.getString("name"));
		sl.setEmail(rs.getString("email"));
		sl.setBirthDate(rs.getDate("birthDate"));
		sl.setBaseSalary(rs.getDouble("baseSalary"));
		sl.setDepartment(dp);
		return sl;
	}

	

}
