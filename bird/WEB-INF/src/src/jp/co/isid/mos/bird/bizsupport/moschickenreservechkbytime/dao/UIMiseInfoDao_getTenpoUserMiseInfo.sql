SELECT     BM01.MISE_CD
  ,		   RTRIM(CHAR(REPLACE(BM01.MISE_NAME_KJ , '  ', '  '), 40)) as MISE_NAME_KJ
  ,        BM01.COMPANY_CD
FROM       BM01TENM as BM01
  ,        (SELECT MISE_CD 
            FROM   BM07UTEN
            WHERE  USER_ID =/*userId*/'99990002'
            AND    COMPANY_CD=/*companyCd*/'00'
            ) BM07
WHERE     BM01.MISE_CD =  BM07.MISE_CD
AND BM01.COMPANY_CD = /*companyCd*/'00'
AND BM01.CLOSE_DT   > /*sysDate*/'20060823'
ORDER BY BM01.MISE_CD
