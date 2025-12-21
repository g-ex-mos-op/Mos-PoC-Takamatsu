select
	BM11.ONER_CD,
    rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
    BM10.SIBU_CD,
    rtrim(BM10.SIBU_NAME) as SIBU_NAME,
    BM01.MISE_CD,
    rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
    rtrim(BT32.TOTAL_LAST_YEAR) as TOTAL_LAST_YEAR,
    rtrim(BT32.TOTAL_LAST_KAI) as TOTAL_LAST_KAI,
    rtrim(BT32.EXAM_NO) as EXAM_NO,
    BM12.STAFF_ID,
    rtrim(BM12.STAFF_L_NAME_KJ) as STAFF_L_NAME_KJ,
    rtrim(BM12.STAFF_F_NAME_KJ) as STAFF_F_NAME_KJ,
    rtrim(BT32.TOTAL_RESULT) as TOTAL_RESULT,
    rtrim(BT32.SUB1_RESULT) as SUB1_RESULT,
    rtrim(BT32.SUB2_RESULT) as SUB2_RESULT,
    rtrim(BT32.SUB3_RESULT) as SUB3_RESULT,
    case when exists (
        select
            *
        from
            BT30ENKJ as BT30
        where
            BT30.ENTRY_YEAR <= BT32.TOTAL_LAST_YEAR and
            BT30.STAFF_ID = BT32.STAFF_ID and
            BT30.COURSE_STATUS = '1' and
            BT30.ENTRY_CD IN ('01','05')
    )
    then '1' else '0' end as COURSE_STATUS,
    BM11.ONER_NAME_KJ as DUMMY_ONER_NAME_KJ,
    BM01.MISE_NAME_KJ as DUMMY_MISE_NAME_KJ,
    BT32.TOTAL_LAST_YEAR as DUMMY_TOTAL_LAST_YEAR,
    BT32.TOTAL_LAST_KAI as DUMMY_TOTAL_LAST_KAI
from
    BT32MLKR as BT32
    inner join BM12STAF as BM12 on BM12.STAFF_ID = BT32.STAFF_ID
    inner join BM01TENM as BM01 on BM01.COMPANY_CD = BM12.COMPANY_CD and BM01.MISE_CD = BM12.MISE_CD_1
    inner join BM10GSIB as BM10 on BM10.COMPANY_CD = BM01.COMPANY_CD and BM10.SIBU_CD = BM01.AREA_DAI
    inner join BM11ONER as BM11 ON BM11.COMPANY_CD = BM12.COMPANY_CD and BM11.ONER_CD = BM12.ONER_CD
    inner join BR18ENTD as BR18 
       on BR18.ENTRY_CD = '10' and BR18.ENTRY_YEAR = BT32.TOTAL_LAST_YEAR 
       and BR18.ENTRY_KAI = BT32.TOTAL_LAST_KAI and BR18.USERTYPE_CD = /*userTypeCd*/'01' 
       and BR18.DAY_KBN = '05' and BR18.FROM_DT <= /*sysDate*/'20060825'
where
    /*IF dto.entryFlg == 0 */
    BT32.TOTAL_LAST_YEAR = /*dto.entryYear*/'2005' and
    BT32.TOTAL_LAST_KAI = /*dto.entryKai*/'002'
    /*END*/
    /*IF dto.entryFlg == 1 */
    BT32.TOTAL_LAST_YEAR = /*dto.entryYear*/'2005'
    /*END*/
    /*IF dto.entryFlg == 2 */
    BT32.TOTAL_LAST_YEAR IN (/*dto.bandleYear1*/'2006', /*dto.bandleYear2*/'2005', /*dto.bandleYear3*/'2004') and
    BR18.FROM_DT <= /*sysDate*/'20060825'
    /*END*/

    /*IF dto.entryFlg != 2 && dto.selectFlg == 0 && dto.areaDai != null*/
    and BM01.AREA_DAI = /*dto.areaDai*/'011'
    /*END*/
    /*IF userTypeCd == '02' || (dto.entryFlg != 2 && dto.selectFlg == 1) */
    and BM11.ONER_CD = /*dto.onerCd*/'36003'
    /*END*/
    /*IF dto.entryFlg != 2 && dto.selectFlg == 2 && dto.miseCd != null*/
    and BM01.MISE_CD = /*dto.miseCd*/'00014'
    /*END*/
	/*IF userTypeCd=='01' && limitFlg*/
	AND    BM01.SIBU_CD IN (
	         SELECT   BM51.SIBU_CD
	         FROM     BM51SVSB BM51
	         WHERE  BM51.COMPANY_CD = /*dto.companyCd*/'00'
	         AND    BM51.SV_CD      = /*userId*/'9999000a'
	         GROUP BY BM51.SIBU_CD
	)
	/*END*/
order by BM10.SIBU_CD
,        BM11.ONER_CD
,        BM01.MISE_CD
,        STAFF_ID
,        BT32.TOTAL_LAST_YEAR
,        BT32.TOTAL_LAST_KAI
