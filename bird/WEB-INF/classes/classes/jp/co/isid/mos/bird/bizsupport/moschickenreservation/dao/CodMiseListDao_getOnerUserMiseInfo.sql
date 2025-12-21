SELECT     BM01TENM.MISE_CD
  ,        BM01TENM.COMPANY_CD
  , 	   RTRIM(CHAR(REPLACE(MISE_NAME_KJ , '  ', '  '), 40)) as MISE_NAME_KJ 
FROM       BM01TENM BM01TENM
  ,        (SELECT ONER_CD 
            FROM   BM06UONR
            WHERE  USER_ID =/*userId*/'99990002'
            AND    COMPANY_CD=/*companyCd*/'00'
            ) BM06
WHERE     BM01TENM.ONER_CD =  BM06.ONER_CD
AND COMPANY_CD = /*companyCd*/'00'
AND CLOSE_DT > /*sysDate*/'20060507' 
ORDER BY MISE_CD
