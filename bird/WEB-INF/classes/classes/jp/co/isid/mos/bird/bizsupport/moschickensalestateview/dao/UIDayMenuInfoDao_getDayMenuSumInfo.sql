select 
/*IF groupKbn.equals("DAY")*/
	CASE 
	WHEN SUB1.DT is null THEN  SUB2.DT
 	ELSE SUB1.DT
 	END as DT,
/*END*/
/*IF groupKbn.equals("MENU")*/
	CASE 
	WHEN SUB1.MENU_CD is null THEN SUB2.MENU_CD
 	ELSE SUB1.MENU_CD
 	END as MENU_CD,
	CASE 
	WHEN SUB1.SUM_GROUP is null THEN SUB2.SUM_GROUP
 	ELSE SUB1.SUM_GROUP
 	END as SUM_GROUP,
	CASE
	WHEN SUB1.BASE_KAZU_KEI_SUM is null THEN  0
	ELSE SUB1.BASE_KAZU_KEI_SUM
 	END as BASE_KAZU_KEI_SUM,
	CASE
	WHEN SUB2.BASE_RESERVE_SUM is null THEN  0
	ELSE SUB2.BASE_RESERVE_SUM
 	END as BASE_RESERVE_SUM,
/*END*/
	CASE
	WHEN SUB1.KAZU_KEI_SUM is null THEN  0
 	ELSE SUB1.KAZU_KEI_SUM
 	END as KAZU_KEI_SUM,
	CASE
	WHEN SUB2.RESERVE_SUM is null THEN  0
 	ELSE SUB2.RESERVE_SUM
 	END as RESERVE_SUM
from
(
select
/*IF groupKbn.equals("DAY")*/
	BT66.MENU_DT  as DT,
	sum(BT66.KAZU_KEI * BM41.CONV_VALUE) as KAZU_KEI_SUM
/*END*/
/*IF groupKbn.equals("MENU")*/
	SUB3.MENU_CD as MENU_CD,
	SUB3.SUM_GROUP as SUM_GROUP,
	sum(SUB3.KAZU_KEI_SUM) as KAZU_KEI_SUM,
	sum(SUB3.BASE_KAZU_KEI_SUM) as BASE_KAZU_KEI_SUM
/*END*/
from
/*IF groupKbn.equals("DAY")*/
	BT66CMSL as BT66,
	BM41CMNU as BM41,
	BM38MMNU as BM38
/*END*/	
/*IF groupKbn.equals("MENU")*/
	(select 
		BT66.MENU_DT,
		BM41.MENU_CD,
		BM41.SUM_GROUP,
		sum(BT66.KAZU_KEI * BM41.CONV_VALUE) as KAZU_KEI_SUM,
		sum(BT66.KAZU_KEI) as BASE_KAZU_KEI_SUM
	from
		BM41CMNU as BM41
			left outer join BT66CMSL as BT66 
			on  BT66.COMPANY_CD = '00' and 
		   		BT66.MISE_CD = /*miseCd*/'01776' and
				BT66.MENU_DT between /*taishoKikanFr*/'20060801' and /*taishoKikanTo*/'20060930' and
				BT66.MENU_CD = BM41.MENU_CD 
		, BM38MMNU as BM38
	where
	 	BM41.CKANRI_NO =  /*ckanriNo*/'200619' and
	 	BM41.MENU_GROUP =  /*menuGroup*/'01' and
	 	BM41.MENU_CD = BM38.MENU_CD and
		BM38.COMPANY_CD ='00' and
		BM38.MISE_CD = /*miseCd*/'01776'
	group by BM41.MENU_CD, BM41.SUM_GROUP, BT66.MENU_DT) SUB3
/*END*/
/*IF groupKbn.equals("DAY") */
	where
 	BT66.COMPANY_CD = '00' and
 	BM41.CKANRI_NO =  /*ckanriNo*/'123456' and
 	BT66.MISE_CD = /*miseCd*/'12345' and
 	BM41.MENU_GROUP =  /*menuGroup*/'123456' and
 	BT66.MENU_DT between /*taishoKikanFr*/'20061201' and /*taishoKikanTo*/'20061231' and
 	BT66.MENU_CD = BM41.MENU_CD and
 	BT66.COMPANY_CD = BM38.COMPANY_CD and
 	BT66.MISE_CD = BM38.MISE_CD and
 	BM41.MENU_CD = BM38.MENU_CD
	group by BT66.MENU_DT
	order by BT66.MENU_DT
/*END*/
/*IF groupKbn.equals("MENU")*/
	group by SUB3.MENU_CD, SUB3.SUM_GROUP
	order by SUB3.SUM_GROUP,SUB3.MENU_CD
/*END*/
) as SUB1 full outer join 
(
select
/*IF groupKbn.equals("DAY")*/
	BT70.RESERVE_DT as DT,
	CASE 
	WHEN sum(BT71.RESERVE_AMT * BM41.CONV_VALUE) = 0 THEN  0
 	ELSE sum(BT71.RESERVE_AMT * BM41.CONV_VALUE)
 	END as RESERVE_SUM
/*END*/
/*IF groupKbn.equals("MENU")*/
 	BM41.MENU_CD as MENU_CD,
	BM41.SUM_GROUP as SUM_GROUP,
	sum(SUB3.RESERVE_AMT * BM41.CONV_VALUE) as RESERVE_SUM,
	sum(SUB3.RESERVE_AMT) as BASE_RESERVE_SUM
/*END*/
from
/*IF groupKbn.equals("DAY")*/
	BT70CRSV as BT70,
	BT71CRSD as BT71,
	BM41CMNU as BM41,
	BM38MMNU as BM38
where 
	BT70.COMPANY_CD = '00' and
	BT70.CANCEL_FLG = '0' and
	BT70.CKANRI_NO =  /*ckanriNo*/'123456' and
	BT70.CKANRI_NO =  BM41.CKANRI_NO and
	BT70.MISE_CD = /*miseCd*/'' and
	BM41.MENU_GROUP =  /*menuGroup*/'123456' and
	BT70.RESERVE_DT between /*taishoKikanFr*/'20061201' and /*taishoKikanTo*/'20061231' and
	BT70.CKANRI_NO =  BT71.CKANRI_NO and
	BT70.COMPANY_CD = BT71.COMPANY_CD and
	BT70.MISE_CD = BT71.MISE_CD and
	BT70.SEQ_NO = BT71.SEQ_NO and
	BT71.MENU_CD = BM41.MENU_CD and
 	BT70.COMPANY_CD = BM38.COMPANY_CD and
 	BT70.MISE_CD = BM38.MISE_CD and	
	BM41.MENU_CD = BM38.MENU_CD	
/*END*/
/*IF groupKbn.equals("MENU")*/
	BM41CMNU as BM41 left outer join
		(select  
			BT70.CKANRI_NO ,
			BT71.MENU_CD ,	
			BT70.RESERVE_DT ,
			BT71.RESERVE_AMT
		 from 
		  	BT70CRSV as BT70,
			BT71CRSD as BT71
		 where 
			BT70.COMPANY_CD = '00' and
			BT70.CANCEL_FLG = '0' and
			BT70.MISE_CD = /*miseCd*/'21235' and	
			BT70.RESERVE_DT between /*taishoKikanFr*/'20061201' and /*taishoKikanTo*/'20061231' and
			BT70.CKANRI_NO =  BT71.CKANRI_NO and
			BT70.COMPANY_CD = BT71.COMPANY_CD and
			BT70.MISE_CD = BT71.MISE_CD and
			BT70.SEQ_NO = BT71.SEQ_NO
		) SUB3
		on BM41.CKANRI_NO =  SUB3.CKANRI_NO and BM41.MENU_CD = SUB3.MENU_CD
		, BM38MMNU as BM38
	where 
		BM41.CKANRI_NO =  /*ckanriNo*/'123456' and
		BM41.MENU_GROUP =  /*menuGroup*/'123456' and
		BM38.COMPANY_CD = '00' and
		BM38.MISE_CD =  /*miseCd*/'21235' and	 	
		BM41.MENU_CD = BM38.MENU_CD 
/*END*/		
/*IF groupKbn.equals("DAY")*/	
	group by BT70.RESERVE_DT 
	order by BT70.RESERVE_DT 
/*END*/
/*IF groupKbn.equals("MENU")*/
	group by BM41.MENU_CD,BM41.SUM_GROUP
	order by BM41.SUM_GROUP, BM41.MENU_CD
/*END*/
	) as SUB2 on 
/*IF groupKbn.equals("DAY")*/
	SUB1.DT = SUB2.DT
	order by DT
/*END*/
/*IF groupKbn.equals("MENU")*/
	SUB1.MENU_CD = SUB2.MENU_CD
	order by SUM_GROUP, MENU_CD
/*END*/