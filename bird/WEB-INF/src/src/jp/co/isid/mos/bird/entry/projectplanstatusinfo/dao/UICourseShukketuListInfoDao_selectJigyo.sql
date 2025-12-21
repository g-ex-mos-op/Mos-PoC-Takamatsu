SELECT DISTINCT BM11.SIBU_CD
     , BM10.SIBU_NAME
     , BM11.ONER_CD
     , BM11.ONER_NAME_KJ
     ,  (case when SHUKKETU.SHUSSEKI_KBN is null then 'Å|' else SHUKKETU.SHUSSEKI_KBN end) as SHUKKETU_KBN
     , DECIMAL((CASE WHEN BT46.SHUSSEKI IS NULL THEN 0 ELSE BT46.SHUSSEKI END), 6,0) as SHUSSEKI_CNT
FROM   BM10GSIB BM10
	 ,    (SELECT MISE.SIBU_CD
                , MISE.ONER_CD 
     			, ONER.ONER_NAME_KJ
    	   FROM   BM11ONER ONER
                , BM01TENM MISE
	       WHERE  ONER.COMPANY_CD   = /*companyCd*/'00'
	       AND    ONER.ONER_CD     != '39999'  
	       AND    MISE.CLOSE_DT     >= (select DISTINCT FROM_DT from BR18ENTD where ENTRY_CD = /*entryCd*/'25' and ENTRY_YEAR = /*entryYear*/'2005'  AND ENTRY_KAI = /*entryKai*/'001' AND USERTYPE_CD = '01' AND DAY_KBN = '04')
	       AND    ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
	       AND    ONER.KEIYAKU_END >= /*sysDate*/'20061213'
	       AND    MISE.COMPANY_CD   = ONER.COMPANY_CD		   
	       AND    MISE.ONER_CD      = ONER.ONER_CD
        GROUP BY MISE.SIBU_CD
                , MISE.ONER_CD 
                , ONER.ONER_NAME_KJ) BM11
	   LEFT JOIN (SELECT BM11.SIBU_CD
   			           , BM11.ONER_CD
   			           , SUM(case when BT46.JIGYO_FLG = '0' then 1 else 0 end) as SHUSSEKI
                  FROM   BT46KENS BT46
		               , (SELECT MISE.SIBU_CD
				               , MISE.ONER_CD 
				          FROM   BM11ONER ONER
				               , BM01TENM MISE
				          WHERE  ONER.COMPANY_CD   = /*companyCd*/'00'
				          AND    ONER.ONER_CD     != '39999' 
				          AND    MISE.CLOSE_DT     >= (select DISTINCT FROM_DT from BR18ENTD where ENTRY_CD = /*entryCd*/'25' and ENTRY_YEAR = /*entryYear*/'2005'  AND ENTRY_KAI = /*entryKai*/'001' AND USERTYPE_CD = '01' AND DAY_KBN = '04')
				          AND    ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
				          AND    ONER.KEIYAKU_END >= /*sysDate*/'20061213'
				          AND    MISE.COMPANY_CD   = ONER.COMPANY_CD
				          AND    MISE.ONER_CD      = ONER.ONER_CD
				        GROUP BY MISE.SIBU_CD
				               , MISE.ONER_CD) BM11
		               , BM01TENM BM01
				   WHERE BM01.COMPANY_CD   = /*companyCd*/'00'
				   AND   BT46.ENTRY_CD     = /*entryCd*/'25'
				   AND   BT46.ENTRY_YEAR   = /*entryYear*/'2005'
				   AND   BT46.ENTRY_KAI    = /*entryKai*/'001'
				   AND   BT46.COMPANY_CD   = BM01.COMPANY_CD 
				   AND   BT46.ONER_CD      = BM11.ONER_CD
				   AND   BM01.SIBU_CD      = BM11.SIBU_CD
				   AND   BT46.MISE_CD      = BM01.MISE_CD
			    GROUP BY BM11.SIBU_CD
				       , BM11.ONER_CD) BT46
	   ON (BT46.SIBU_CD = BM11.SIBU_CD AND BT46.ONER_CD = BM11.ONER_CD)
       LEFT JOIN (SELECT BM11.ONER_CD
			           , (case when MAX(case when BT46.JIGYO_FLG = '0' then 1 else 0 end) > 0 then 'Åõ' else 'Å~' end) as SHUSSEKI_KBN
				  FROM   BT46KENS BT46
				       , BM11ONER BM11
				       , BM01TENM BM01
				  WHERE  BM11.COMPANY_CD   = /*companyCd*/'00'
				  AND    BM11.ONER_CD     != '39999' 
				  AND    BM11.KEIYAKU_STA <= /*sysDate*/'20061213'
				  AND    BM11.KEIYAKU_END >= /*sysDate*/'20061213'
				  AND    BT46.ENTRY_CD     = /*entryCd*/'25'
				  AND    BT46.ENTRY_YEAR   = /*entryYear*/'2005'
				  AND    BT46.ENTRY_KAI    = /*entryKai*/'001'
				  AND    BM01.COMPANY_CD   = BM11.COMPANY_CD 
				  AND    BT46.COMPANY_CD   = BM01.COMPANY_CD 
				  AND    BT46.ONER_CD      = BM11.ONER_CD
				  AND    BT46.MISE_CD      = BM01.MISE_CD
			GROUP BY BM11.ONER_CD) SHUKKETU
			ON (SHUKKETU.ONER_CD = BM11.ONER_CD)
        
/*IF taishouJokenChoice.equals("2")*/     
		,(SELECT DISTINCT ONER_CD 
		  FROM   BM01TENM 
		  WHERE  MISE_CD in (SELECT MISE_CD 
		                     FROM   BM50TANM 
		                     WHERE  COMPANY_CD = /*companyCd*/'00'
		                     AND    SV_CD = /*svCd*/'00000085')
		  AND    COMPANY_CD = /*companyCd*/'00') BM50
/*END*/

WHERE BM10.COMPANY_CD   =  /*companyCd*/'00'
AND   BM10.SIBU_CD      = BM11.SIBU_CD 
/*IF limit*/
AND   BM10.SIBU_CD IN (select distinct
			                   sibu_cd
				        from   bm51svsb
				        where  company_cd = /*companyCd*/'00'
					    and    sv_cd      = /*userId*/'00000085')
/*END*/
/*IF taishouJokenChoice.equals("1") */
AND   BM10.SIBU_CD = /*sibuListChoice*/'011'
/*END*/
/*IF taishouJokenChoice.equals("2") */     
AND   BM50.ONER_CD = BM11.ONER_CD
/*END*/

order by SIBU_CD
       , ONER_CD
