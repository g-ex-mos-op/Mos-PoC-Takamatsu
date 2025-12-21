SELECT
	CLOSE_DT, MISE_CD, MISE_NAME_KJ, COMPANY_CD
FROM
	(SELECT    BM01.MISE_CD
		  ,        BM01.MISE_NAME_KJ
		  ,        BM01.COMPANY_CD
		  ,        BM01.CLOSE_DT
	  FROM       BM01TENM as BM01
		  ,        (SELECT ONER_CD
		            FROM   BM06UONR
		            WHERE  USER_ID =/*userId*/'99990002'
		            AND    COMPANY_CD=/*companyCd*/'00'
		            ) BM06
	  WHERE     BM01.ONER_CD =  BM06.ONER_CD
			AND BM01.COMPANY_CD = /*companyCd*/'00'
			ORDER BY BM01.MISE_CD
	  ) as TBL
ORDER BY CLOSE_DT DESC, MISE_CD

