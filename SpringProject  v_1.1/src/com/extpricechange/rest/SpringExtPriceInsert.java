package com.extpricechange.rest;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;


public class SpringExtPriceInsert {
	JdbcTemplate jt;
	
	 
	public void setJt(JdbcTemplate jt)
	{
		this.jt = jt;
	}
	
	
	
	public Boolean saveDataPreparedStatement() throws Exception{  
	
	    return jt.execute(AllConstants.inPriceChangeInsertStmt,new PreparedStatementCallback<Boolean>(){  
	    @Override  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	    	
	    	List inpPriceChVolist;
			try {
				inpPriceChVolist = ApachePoiExecUtil.prepareReturnInputPriceChangeList();
				
				Integer j = 0;
				
				while (inpPriceChVolist.size() > j) {
					
					StagePriceChangeValueObject vot=(StagePriceChangeValueObject)inpPriceChVolist.get(j);
					
					System.out.println(vot.REQUESTER_ID);
					
					ps.setInt(1,vot.getRPM_STAGE_PRICE_CHANGE_ID());ps.setInt(2, vot.getPRICE_CHANGE_ID());ps.setInt(3,vot.getREASON_CODE());
				    ps.setInt(4,vot.getITEM());ps.setInt(5,vot.getSKULIST()); ps.setInt(6,vot.getDIFF_ID());
				    ps.setInt(7,vot.getZONE_GROUP_DISPLAY_ID());ps.setInt(8,vot.getZN_LOC_ID());ps.setInt(9,vot.getZONE_NODE_TYPE());
				    ps.setInt(10,vot.getZONE_NODE_TYPE());ps.setDate(11, vot.getEFFECTIVE_DATE());ps.setInt(12, vot.getCHANGE_TYPE());
				    ps.setInt(13, vot.getCHANGE_AMOUNT());ps.setString(14, vot.getCHANGE_CURRENCY());ps.setInt(15, vot.getPRCG_STAT_CDE());
				    ps.setDate(16, vot.getEFFECTIVE_DATE());ps.setInt(17, vot.getLAST_UPD_USR_ID());ps.setString(18, vot.getIPUT_FIL_NM());
				    ps.setInt(19, vot.getEXTR_CDE()); ps.setInt(20, vot.getREQUESTER_ID());
				  
				    ps.addBatch();
					j++;
				}
				
				 ps.executeBatch();
				 System.out.println("Data Inserted");
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	    	
	              
	     return true; 
	              
	    }  
	    });  
	}  
	
	
	
	
	
}
