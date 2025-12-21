select 
	'' as ENTRY_CD,
	'' as ENTRY_YEAR,
	'' as ENTRY_KAI,
	BM12.STAFF_ID,
	BM12.COMPANY_CD,
	BM12.ONER_CD,
	BT23.EXAM_NO,
	VBT32.SUB1_RESULT,
	BM12.STAFF_L_NAME_KJ,
	BM12.STAFF_F_NAME_KJ,
	BM12.MISE_CD_1,
	BM11.ONER_NAME_KJ,
	BM01.MISE_NAME_KJ,
    BM01.SIBU_CD,
    BM10.SIBU_NAME, 
    (case when VBT32.SUB1_RESULT is NULL THEN 'ñ¢éÛå±'
          when VBT32.SUB1_RESULT = ''  THEN 'ñ¢éÛå±'
          when VBT32.SUB1_RESULT = '0' THEN 'ïsçáäi'
          when VBT32.SUB1_RESULT = '1' THEN 'çáäi'
          when VBT32.SUB1_RESULT = '2' THEN 'ñ∆èú'
          when VBT32.SUB1_RESULT = '9' THEN 'ñ¢éÛå±'
     end) as SUB1_RESULT_DISP
from
	BM12STAF BM12
	left join (
	 select * from BT23MLEJ BT23
	 where BT23.ENTRY_CD = /*entryCd*/'10' 
	   and BT23.ENTRY_YEAR = /*entryYear*/'2006' 
	   and BT23.ENTRY_kAI = /*entryKai*/'001' 
    ) BT23 on (BM12.STAFF_ID = BT23.STAFF_ID)
    left join (
     select * from VBT32MLKR VBT32 
     where VBT32.TOTAL_LAST_YEAR = /*entryYear*/'2006'
       and VBT32.TOTAL_LAST_KAI = /*entryKai*/'001'
    ) VBT32 on (BM12.STAFF_ID = VBT32.STAFF_ID),
	BM01TENM BM01,
	BM11ONER BM11,
	BM10GSIB BM10
where
    BM01.COMPANY_CD = /*companyCd*/'00'
and BM12.COMPANY_CD = BM01.COMPANY_CD
and BM12.MISE_CD_1  = BM01.MISE_CD
and BM12.COMPANY_CD = BM01.COMPANY_CD
and BM11.ONER_CD    = BM01.ONER_CD
and BM01.COMPANY_CD = BM10.COMPANY_CD
and BM01.AREA_DAI   = BM10.SIBU_CD
/*IF condType == "1"*/
and BM10.SIBU_CD = /*sibuCd*/'031'
/*END*/
/*IF condType == "2"*/
and BM01.ONER_CD = /*onerCd*/'36478'
/*END*/
/*IF condType == "3"*/
and BM01.MISE_CD = /*miseCd*/'01776'
/*END*/
