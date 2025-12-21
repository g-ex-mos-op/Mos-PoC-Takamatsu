SELECT (CASE WHEN BD11.MENU_BUNRUI IS NULL  THEN 1
        ELSE 2 END) AS ROW_RANK
,      (CASE WHEN BD11.MENU_BUNRUI IS NULL  THEN 'TOTAL' 
        ELSE '' END) AS ROW_TYPE
,      BD11.COMPANY_CD
,      BD11.TARGET_NAME_KJ
,      BD11.MENU_BUNRUI
,      BD11.MENU_CD
,      (CASE WHEN BD11.MENU_BUNRUI IS NULL THEN '‡Œv' 
        ELSE RTRIM(BD11.MENU_NAME_KJ) END) AS MENU_NAME_KJ
,      (CASE WHEN BD11.MENU_BUNRUI IS NULL THEN null ELSE BD11.TANKA END) AS TANKA
,      BD11.TENPO_CNT
,      BD11.KOSU
,      BD11.KINGAKU
,      DECIMAL(CASE WHEN BD11.KINGAKU IS NULL OR SUM(CASE WHEN BD11.MENU_CD IS NOT NULL THEN BD11.KINGAKU ELSE 0 END) OVER(partition by BD11.COMPANY_CD)=0 THEN 0.00
                    ELSE (DOUBLE(BD11.KINGAKU)/DOUBLE(SUM(CASE WHEN BD11.MENU_CD IS NOT NULL THEN BD11.KINGAKU ELSE 0 END) OVER(partition by BD11.COMPANY_CD)))*100+0.005
                    END,11,2)  AS KINGAKU_KOUSEI_HI
,      BD11.KOSU_EAT
,      BD11.KINGAKU_EAT
,      BD11.KOSU_TAKE
,      BD11.KINGAKU_TAKE
,      BD11.KOSU_TEL
,      BD11.KINGAKU_TEL
,      BD11.KOSU_DRIVE
,      BD11.KINGAKU_DRIVE
,      BD11.KOSU_TAKUHAI
,      BD11.KINGAKU_TAKUHAI
,      BD11.KOSU_ETC
,      BD11.KINGAKU_ETC
FROM (
        SELECT BD10orBD11.COMPANY_CD
        ,      BM01.MISE_NAME_KJ AS TARGET_NAME_KJ
        ,      PC10.MENU_BUNRUI 
        ,      BD10orBD11.MENU_CD 
        ,      PC10.MENU_NAME_KJ
        ,      CASE WHEN (SUM(BD10orBD11.KAZU_KEI))=0 THEN null
                    WHEN COUNT(DISTINCT BD10orBD11.TANKA) = 1 THEN MAX(BD10orBD11.TANKA) 
                    WHEN SUM(BD10orBD11.URIAGE) >= 0 THEN DECIMAL(DOUBLE(SUM(BD10orBD11.URIAGE)) / DOUBLE(SUM(BD10orBD11.KAZU_KEI))+0.5, 11, 0) 
                    ELSE DECIMAL(DOUBLE(SUM(BD10orBD11.URIAGE)) / DOUBLE(SUM(BD10orBD11.KAZU_KEI))-0.5, 11, 0) 
               END AS TANKA
		,      COUNT(DISTINCT BD10orBD11.MISE_CD) TENPO_CNT
		,      SUM(BD10orBD11.KAZU_KEI) AS KOSU
		,      SUM(DECIMAL(BD10orBD11.URIAGE,11,0))  AS KINGAKU
		,      SUM(BD10orBD11.KAZU_EAT) AS KOSU_EAT
		,      SUM(DECIMAL(CASE WHEN BD10orBD11.KAZU_EAT IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_EAT)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_EAT
		,      SUM(BD10orBD11.KAZU_TAKE) AS KOSU_TAKE
		,      SUM(DECIMAL(CASE WHEN BD10orBD11.KAZU_TAKE IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_TAKE)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_TAKE
		,      SUM(BD10orBD11.KAZU_TEL) AS KOSU_TEL
		,      SUM(DECIMAL(CASE WHEN BD10orBD11.KAZU_TEL IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_TEL)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_TEL
		,      SUM(BD10orBD11.KAZU_DRIVE) AS KOSU_DRIVE
		,      SUM(DECIMAL(CASE WHEN BD10orBD11.KAZU_DRIVE IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_DRIVE)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_DRIVE
		,      SUM(BD10orBD11.KAZU_TAKUHAI) AS KOSU_TAKUHAI
		,      SUM(DECIMAL(CASE WHEN BD10orBD11.KAZU_TAKUHAI IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_TAKUHAI)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_TAKUHAI
		,      SUM(BD10orBD11.KAZU_GAIHAN + BD10orBD11.KAZU_TAKUHAI
		          + BD10orBD11.KAZU_OTHER_1+BD10orBD11.KAZU_OTHER_2+BD10orBD11.KAZU_OTHER_3+BD10orBD11.KAZU_OTHER_4
		          + BD10orBD11.KAZU_OTHER_5+BD10orBD11.KAZU_OTHER_6+BD10orBD11.KAZU_OTHER_7+BD10orBD11.KAZU_OTHER_8
		          + BD10orBD11.KAZU_OTHER_9) AS KOSU_ETC
		,      SUM(DECIMAL(CASE WHEN (BD10orBD11.KAZU_GAIHAN + BD10orBD11.KAZU_TAKUHAI
		          + BD10orBD11.KAZU_OTHER_1+BD10orBD11.KAZU_OTHER_2+BD10orBD11.KAZU_OTHER_3+BD10orBD11.KAZU_OTHER_4
		          + BD10orBD11.KAZU_OTHER_5+BD10orBD11.KAZU_OTHER_6+BD10orBD11.KAZU_OTHER_7+BD10orBD11.KAZU_OTHER_8
		          + BD10orBD11.KAZU_OTHER_9) IS NULL THEN 0
		                    ELSE (DOUBLE(BD10orBD11.KAZU_GAIHAN + BD10orBD11.KAZU_TAKUHAI
		          + BD10orBD11.KAZU_OTHER_1+BD10orBD11.KAZU_OTHER_2+BD10orBD11.KAZU_OTHER_3+BD10orBD11.KAZU_OTHER_4
		          + BD10orBD11.KAZU_OTHER_5+BD10orBD11.KAZU_OTHER_6+BD10orBD11.KAZU_OTHER_7+BD10orBD11.KAZU_OTHER_8
		          + BD10orBD11.KAZU_OTHER_9)*DOUBLE(BD10orBD11.TANKA))
		                    END,11,0))  AS KINGAKU_ETC
        FROM (
        	SELECT BM01.MISE_CD
            ,      BM01.MISE_NAME_KJ
            FROM BM01TENM BM01
		/*IF userTypeCd == "02" */
		    ,    BM06UONR BM06
		/*END*/
		/*IF userTypeCd == "03" */
		    ,    BM07UTEN BM07
		/*END*/
            ,   (
		             SELECT MISE_CD
		             FROM BN01DTEN
		             WHERE COMPANY_CD = /*companyCd*/'00'
	    /*IF "KIKAN".equals(taishoKikan) */
                     AND   EIGYO_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
	    --ELSE
		             AND   EIGYO_YM BETWEEN /*kikanSiteiFrom*/'200901' AND /*kikanSiteiTo*/'200904'
		/*END*/
		/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
		             AND   KBN1 = /*tenpoShubetu*/''
		/*END*/
		/*IF tenpoShubetu == "2" */
		             AND   KBN1 IN ('1', '2')
		/*END*/
					 AND MISE_CD = /*hyojiTaisho*/'02001'
 		             GROUP BY MISE_CD
		           ) BN01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01.MISE_CD = /*hyojiTaisho*/
/*IF userTypeCd == "01" && limitFlg == true */
            AND    BM01.SIBU_CD IN (
                SELECT   BM51.SIBU_CD
                FROM     BM51SVSB BM51
                WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                AND    BM51.SV_CD      = /*userId*/'9999000a'
                GROUP BY BM51.SIBU_CD
                )
/*END*/
/*IF userTypeCd == "02" */
	        AND   BM06.USER_ID    = /*userId*/'99990002'
	        AND   BM06.COMPANY_CD = BM01.COMPANY_CD
	        AND   BM06.ONER_CD    = BM01.ONER_CD
/*END*/
/*IF userTypeCd == "03" */
	        AND   BM07.USER_ID    = /*userId*/'99990003'
	        AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	        AND   BM07.MISE_CD    = BM01.MISE_CD
/*END*/
	        AND   BN01.MISE_CD    = BM01.MISE_CD
	    ) BM01
	    /*IF "KIKAN".equals(taishoKikan) */
	    ,   (SELECT * FROM BD10NMSL/*$targetMonth1*/ 
	                  WHERE COMPANY_CD = /*companyCd*/'00' AND MISE_CD = /*hyojiTaisho*/'02001'
	                  AND MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
	    	/*IF targetMonth2 != null*/
	        UNION ALL 
	        (
	        	SELECT * FROM BD10NMSL/*$targetMonth2*/ 
	        	         WHERE COMPANY_CD = /*companyCd*/'00' AND MISE_CD = /*hyojiTaisho*/'02001'
	                 	 AND MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
		    	/*IF targetMonth3 != null*/
		        UNION ALL 
		        (
			        SELECT * FROM BD10NMSL/*$targetMonth3*/ 
			                 WHERE COMPANY_CD = /*companyCd*/'00' AND MISE_CD = /*hyojiTaisho*/'02001'
	  		                 AND MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
			    	/*IF targetMonth4 != null*/
			        UNION ALL 
			        (
			        	SELECT * FROM BD10NMSL/*$targetMonth4*/ 
			                 WHERE COMPANY_CD = /*companyCd*/'00' AND MISE_CD = /*hyojiTaisho*/'02001'
	                  		 AND MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
			    	/*IF targetMonth5 != null*/
				        UNION ALL 
				        SELECT * FROM BD10NMSL/*$targetMonth5*/ 
				                 WHERE COMPANY_CD = /*companyCd*/'00' AND MISE_CD = /*hyojiTaisho*/'02001'
		                  		 AND MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
				        /*END*/
				     )
			        /*END*/
		        )
		        /*END*/
	        )
	        /*END*/
	        ) BD10orBD11
	    --ELSE
	    ,   BD11NMRI BD10orBD11
	    /*END*/
        ,    PC10SMNU PC10
        WHERE BD10orBD11.COMPANY_CD = /*companyCd*/'00'
        AND   BD10orBD11.MISE_CD = /*hyojiTaisho*/'02001'
	    /*IF "KIKAN".equals(taishoKikan) */
        AND   BD10orBD11.MENU_DT BETWEEN /*kikanSiteiFrom*/'20090131' AND /*kikanSiteiTo*/'20090402'
	    --ELSE
        AND   BD10orBD11.MENU_YM BETWEEN /*kikanSiteiFrom*/'200901' AND /*kikanSiteiTo*/'200904'
	    /*END*/
	    /*IF "KIKAN".equals(taishoKikan) */
	    AND   BD10orBD11.KAZU_KEI > 0
	    --ELSE
	    AND   BD10orBD11.URIAGE_FLG = '1'
	    /*END*/
	    AND   BD10orBD11.MENU_CD = PC10.MENU_CD
        AND   BD10orBD11.MISE_CD = BM01.MISE_CD
        GROUP BY ROLLUP ((BD10orBD11.COMPANY_CD
        ,      BM01.MISE_NAME_KJ
		, PC10.MENU_BUNRUI , BD10orBD11.MENU_CD, PC10.MENU_NAME_KJ)
		)
) BD11
ORDER BY MENU_BUNRUI
,        MENU_CD
,        TANKA
