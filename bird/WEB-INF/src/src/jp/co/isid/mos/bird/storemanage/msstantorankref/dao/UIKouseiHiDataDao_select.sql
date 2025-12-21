SELECT ALLMISE.NENDO
,      ALLMISE.KAI
,      ALLMISE.COMPANY_CD
,      ALLMISE.CNT AS ALL_MISECNT
,      (CASE WHEN PTN1 IS NULL THEN 0 ELSE PTN1 END) AS MISECNT1
,      (CASE WHEN PTN2 IS NULL THEN 0 ELSE PTN2 END) AS MISECNT2
,      (CASE WHEN PTN3 IS NULL THEN 0 ELSE PTN3 END) AS MISECNT3
,      (CASE WHEN PTN4 IS NULL THEN 0 ELSE PTN4 END) AS MISECNT4
,      (CASE WHEN PTN1 IS NULL THEN 0.00 
             WHEN PTN1 = 0     THEN 0.00
             ELSE DECIMAL((DOUBLE(PTN1)/DOUBLE(ALLMISE.CNT))*100+0.005, 5,2) END) AS KOUSEIHI1
,      (CASE WHEN PTN2 IS NULL THEN 0.00 
             WHEN PTN2 = 0     THEN 0.00
             ELSE DECIMAL((DOUBLE(PTN2)/DOUBLE(ALLMISE.CNT))*100+0.005, 5,2) END) AS KOUSEIHI2
,      (CASE WHEN PTN3 IS NULL THEN 0.00 
             WHEN PTN3 = 0     THEN 0.00
             ELSE DECIMAL((DOUBLE(PTN3)/DOUBLE(ALLMISE.CNT))*100+0.005, 5,2) END) AS KOUSEIHI3
,      (CASE WHEN PTN4 IS NULL THEN 0.00 
             WHEN PTN4 = 0     THEN 0.00
             ELSE DECIMAL((DOUBLE(PTN4)/DOUBLE(ALLMISE.CNT))*100+0.005, 5,2) END) AS KOUSEIHI4
FROM ( 
	SELECT BT33.NENDO
	,      BT33.KAI
	,      BT33.COMPANY_CD
	,      COUNT(DISTINCT BT33.MISE_CD) CNT 
	FROM BM31MSPM BM31, BT33MSPD BT33
	,    ( 
		  SELECT MISEMST.MISE_CD 
		  FROM BM01TENM MISEMST
/*IF searchType == 'SV' */
		  ,    BM50TANM BM50
/*END*/
/*IF searchType == 'MISE' */
	     ,    (
	         SELECT ONER_CD FROM BM01TENM 
	         WHERE COMPANY_CD = /*companyCd*/'00'
	         AND   MISE_CD = /*miseCd*/'01776'
	         GROUP BY ONER_CD
		) KOTEN
/*END*/
		WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
 		AND   BM50.SV_CD = /*svCd*/'00000921' 
		AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
		AND   BM50.MISE_CD = MISEMST.MISE_CD 
/*END*/
/*IF searchType == 'ONER' */
 		AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  		AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  			  				   	  from bm51svsb 
  						    	  where company_cd = /*companyCd*/'00' 
  						    	  and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
  	  AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
		GROUP BY MISEMST.MISE_CD 
	) BM01 
	WHERE BT33.NENDO = /*nendo*/'2006' 
	AND   BT33.KAI = /*kai*/'01' 
	AND   BT33.COMPANY_CD = /*companyCd*/'00'
	AND   BM31.HYOUKA_KBN = '3' 
	AND   BM31.LIMIT_U = 100 
	AND   BM31.NENDO = BT33.NENDO 
	AND   BM31.KAI = BT33.KAI 
	AND   BM31.KOUMOKU_NO = BT33.KOUMOKU_NO 
	AND   BM31.KOUMOKU_SUB = BT33.KOUMOKU_SUB 
	AND   BT33.MISE_CD = BM01.MISE_CD 
	GROUP BY BT33.NENDO
	,        BT33.KAI
	,        BT33.COMPANY_CD
  ) ALLMISE 
LEFT JOIN ( 
	SELECT BT33.NENDO
	,      BT33.KAI
	,      COUNT(DISTINCT BT33.MISE_CD) PTN1 
	FROM BM31MSPM BM31, BT33MSPD BT33
	,    ( 
		SELECT MISEMST.MISE_CD 
		FROM BM01TENM MISEMST
/*IF searchType == 'SV' */
		,    BM50TANM BM50
/*END*/
/*IF searchType == 'MISE' */
	    ,    (
	         SELECT ONER_CD FROM BM01TENM 
	         WHERE COMPANY_CD = /*companyCd*/'00'
	         AND   MISE_CD = /*miseCd*/'01776'
	         GROUP BY ONER_CD
		) KOTEN
/*END*/
		WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
		AND   BM50.SV_CD = /*svCd*/'00000921' 
		AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
		AND   BM50.MISE_CD = MISEMST.MISE_CD 
/*END*/
/*IF searchType == 'ONER' */
		AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  		AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  			  				   	  from bm51svsb 
  						    	  where company_cd = /*companyCd*/'00' 
  						    	  and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
		AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
		GROUP BY MISEMST.MISE_CD 
	) BM01 
	WHERE BT33.NENDO = /*nendo*/'2006' 
	AND BT33.KAI = /*kai*/'01' 
	AND BT33.COMPANY_CD = /*companyCd*/'00'
	AND BM31.HYOUKA_KBN = '3' 
	AND BM31.LIMIT_U = 100 
	AND BM31.NENDO = BT33.NENDO 
	AND BM31.KAI = BT33.KAI 
	AND BM31.KOUMOKU_NO = BT33.KOUMOKU_NO 
	AND BM31.KOUMOKU_SUB = BT33.KOUMOKU_SUB 
	AND BT33.MISE_CD = BM01.MISE_CD 
	AND BT33.HYOUKA_DATA < 50 
	GROUP BY BT33.NENDO
	,        BT33.KAI
) SUB1 
ON (ALLMISE.NENDO = SUB1.NENDO AND ALLMISE.KAI = SUB1.KAI)
LEFT JOIN ( 
	SELECT BT33.NENDO
	,      BT33.KAI
	,      COUNT(DISTINCT BT33.MISE_CD) PTN2 
	FROM BM31MSPM BM31
	,    BT33MSPD BT33
	,    ( 
	  SELECT MISEMST.MISE_CD 
	  FROM BM01TENM MISEMST
/*IF searchType == 'SV' */
      ,    BM50TANM BM50
/*END*/
/*IF searchType == 'MISE' */
     ,    (
         SELECT ONER_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND   MISE_CD = /*miseCd*/'01776'
         GROUP BY ONER_CD
  ) KOTEN
/*END*/
  WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
	AND   BM50.SV_CD = /*svCd*/'00000921' 
	AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
	AND   BM50.MISE_CD = MISEMST.MISE_CD 
/*END*/
/*IF searchType == 'ONER' */
     AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  		AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  			  				   	  from bm51svsb 
  						    	  where company_cd = /*companyCd*/'00' 
  						    	  and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
     AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
  GROUP BY MISEMST.MISE_CD 
 ) BM01 
	WHERE BT33.NENDO = /*nendo*/'2006' 
	AND BT33.KAI = /*kai*/'01' 
	AND BT33.COMPANY_CD = /*companyCd*/'00'
	AND BM31.HYOUKA_KBN = '3' 
	AND BM31.LIMIT_U = 100 
	AND BM31.NENDO = BT33.NENDO 
	AND BM31.KAI = BT33.KAI 
	AND BM31.KOUMOKU_NO = BT33.KOUMOKU_NO 
	AND BM31.KOUMOKU_SUB = BT33.KOUMOKU_SUB 
	AND BT33.MISE_CD = BM01.MISE_CD 
	AND BT33.HYOUKA_DATA >= 50 AND BT33.HYOUKA_DATA < 70
	GROUP BY BT33.NENDO
	,        BT33.KAI
) SUB2 
ON (ALLMISE.NENDO = SUB2.NENDO AND ALLMISE.KAI = SUB2.KAI)
LEFT JOIN( 
	SELECT BT33.NENDO
	,      BT33.KAI
	,      COUNT(DISTINCT BT33.MISE_CD) PTN3 
	FROM BM31MSPM BM31, BT33MSPD BT33
	,    ( 
	  SELECT MISEMST.MISE_CD 
	  FROM BM01TENM MISEMST
/*IF searchType == 'SV' */
	     ,    BM50TANM BM50
/*END*/
/*IF searchType == 'MISE' */
     ,    (
         SELECT ONER_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND   MISE_CD = /*miseCd*/'01776'
         GROUP BY ONER_CD
  ) KOTEN
/*END*/
  WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
     AND   BM50.SV_CD = /*svCd*/'00000921' 
  AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
  AND   BM50.MISE_CD = MISEMST.MISE_CD 
/*END*/
/*IF searchType == 'ONER' */
     AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  		AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  			  				   	  from bm51svsb 
  						    	  where company_cd = /*companyCd*/'00' 
  						    	  and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
     AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
  GROUP BY MISEMST.MISE_CD 
 ) BM01 
	WHERE BT33.NENDO = /*nendo*/'2006' 
	AND BT33.KAI = /*kai*/'01' 
	AND BT33.COMPANY_CD = /*companyCd*/'00'
	AND BM31.HYOUKA_KBN = '3' 
	AND BM31.LIMIT_U = 100 
	AND BM31.NENDO = BT33.NENDO 
	AND BM31.KAI = BT33.KAI 
	AND BM31.KOUMOKU_NO = BT33.KOUMOKU_NO 
	AND BM31.KOUMOKU_SUB = BT33.KOUMOKU_SUB 
	AND BT33.MISE_CD = BM01.MISE_CD 
	AND BT33.HYOUKA_DATA >= 70 AND BT33.HYOUKA_DATA < 90 
	GROUP BY BT33.NENDO
	,        BT33.KAI
) SUB3 
ON (ALLMISE.NENDO = SUB3.NENDO AND ALLMISE.KAI = SUB3.KAI)
LEFT JOIN ( 
	SELECT BT33.NENDO
	,      BT33.KAI
	,      COUNT(DISTINCT BT33.MISE_CD) PTN4 
	FROM BM31MSPM BM31, BT33MSPD BT33
	,    ( 
	  SELECT MISEMST.MISE_CD 
	  FROM BM01TENM MISEMST
/*IF searchType == 'SV' */
	  ,    BM50TANM BM50
/*END*/
/*IF searchType == 'MISE' */
     ,    (
         SELECT ONER_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND   MISE_CD = /*miseCd*/'01776'
         GROUP BY ONER_CD
  ) KOTEN
/*END*/
	WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
	AND   BM50.SV_CD = /*svCd*/'00000921' 
	AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
	AND   BM50.MISE_CD = MISEMST.MISE_CD 
/*END*/
/*IF searchType == 'ONER' */
	AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  		AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  			  				   	  from bm51svsb 
  						    	  where company_cd = /*companyCd*/'00' 
  						    	  and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
	AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
	GROUP BY MISEMST.MISE_CD 
	) BM01 

	WHERE BT33.NENDO = /*nendo*/'2006' 
	AND BT33.KAI = /*kai*/'01' 
	AND BT33.COMPANY_CD = /*companyCd*/'00'
	AND BM31.HYOUKA_KBN = '3' 
	AND BM31.LIMIT_U = 100 
	AND BM31.NENDO = BT33.NENDO 
	AND BM31.KAI = BT33.KAI 
	AND BM31.KOUMOKU_NO = BT33.KOUMOKU_NO 
	AND BM31.KOUMOKU_SUB = BT33.KOUMOKU_SUB 
	AND BT33.MISE_CD = BM01.MISE_CD 
	AND BT33.HYOUKA_DATA >= 90 
	GROUP BY BT33.NENDO
	,        BT33.KAI
) SUB4 
 ON (ALLMISE.NENDO = SUB4.NENDO AND ALLMISE.KAI = SUB4.KAI)