package com.extpricechange.rest;

public class AllConstants {
	
	static int col_zero=0;
    static int col_one=1;
	static int col_two=2;
	static int col_three=3;
	static int col_four=4;
	static int col_five=5;
	static int col_six=6;
	static int col_seven=7;
	static int col_eight=8;
	static int col_nine=9;
	static int col_ten=10;
	static int col_eleven=11;
	static int col_twelve=12;
	static int col_thirteen=13;
	static int col_fourteen=14;
	static int col_fifteen=15;
	static int col_sixfteen=16;
	static int col_seventeen=17;
	static int col_eightteen=18;
	static int col_nineteen=19;
	
	static int col_twenty=20;
	
	static String inPriceChangeInsertStmt ="insert into RPT_PRE_STAGE_PRICE_CHANGE"
			+ "(RPM_STAGE_PRICE_CHANGE_ID,PRICE_CHANGE_ID,REASON_CODE,ITEM,SKULIST,DIFF_ID,ZONE_GROUP_DISPLAY_ID,ZN_LOC_ID,ZONE_NODE_TYPE,LINK_CODE,EFFECTIVE_DATE,CHANGE_TYPE,CHANGE_AMOUNT,CHANGE_CURRENCY,PRCG_STAT_CDE,LAST_UPD_TMST,LAST_UPD_USR_ID,IPUT_FIL_NM,EXTR_CDE,REQUESTER_ID" + ")"
			+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
