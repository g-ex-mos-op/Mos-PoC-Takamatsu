SELECT 
count(distinct BM01TENM.MISE_CD) AS COUNT
from (BM01TENM 
JOIN BM10GSIB on (
 BM01TENM.COMPANY_CD = BM10GSIB.COMPANY_CD 
)  
JOIN BN01DTEN on (
BN01DTEN.COMPANY_CD = BM01TENM.COMPANY_CD AND 
BN01DTEN.MISE_CD = BM01TENM.MISE_CD 
/*IF taishoKikan.equals("APPMONTH")*/
	AND BN01DTEN.EIGYO_YM = /*kikanFrom*/
/*END*/
/*IF taishoKikan.equals("MONTH")*/
	AND BN01DTEN.EIGYO_YM = /*kikanFrom*/
/*END*/
/*IF taishoKikan.equals("KIBETU")*/
	AND (BN01DTEN.EIGYO_YM between /*kikanFrom*/ AND /*kikanTo*/) 
/*END*/
/*IF !tenShu.equals("ALL")*/
       /*IF tenShu.equals("1")*/
			AND BN01DTEN.KBN1 = '1'
       -- ELSE
       		/*IF tenShu.equals("2")*/
				AND ( BN01DTEN.KBN1 = '1' OR BN01DTEN.KBN1 = '2' )
       		-- ELSE
				AND BN01DTEN.KBN1 = '3'
       		/*END*/
       /*END*/
/*END*/
)
JOIN BT74YAGO on (
 BT74YAGO.COMPANY_CD = BN01DTEN.COMPANY_CD AND
 BT74YAGO.EIGYO_DT = BN01DTEN.EIGYO_YM AND
 BT74YAGO.MISE_CD = BN01DTEN.MISE_CD AND 
 BT74YAGO.OLDM_FLG = '0'
 AND BT74YAGO.OPEN_KBN = 1 
) 
 )
WHERE
 	BM01TENM.COMPANY_CD = /*companyCd*/ 
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
AND BM01TENM.MISE_KBN like '_1_'
       -- ELSE
AND BM01TENM.MISE_KBN like '_2_'
       /*END*/
/*END*/
 
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
	AND BM01TENM.AREA_DAI = BM10GSIB.SIBU_CD AND BM10GSIB.AREA_DAI_FLG = '1'
-- ELSE
	AND BM01TENM.SIBU_CD = BM10GSIB.SIBU_CD
/*END*/

/*IF limitFlg == true*/
	AND BM01TENM.SIBU_CD IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/

AND BT74YAGO.YAGO_DETAIL_CD != ''

