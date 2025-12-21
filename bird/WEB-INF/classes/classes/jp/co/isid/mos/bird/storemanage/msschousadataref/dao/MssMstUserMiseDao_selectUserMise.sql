select     BM01TENM.MISE_CD
  ,        BM01TENM.MISE_NAME_KJ
  ,        BM01TENM.COMPANY_CD
from       BM01TENM BM01TENM
  ,        (SELECT ONER_CD,COMPANY_CD 
            FROM   BM06UONR
            WHERE  USER_ID =/*userId*/'99990002'
            ) BM06
where     BM01TENM.ONER_CD =  BM06.ONER_CD
AND       BM01TENM.COMPANY_CD =  /*companyCd*/'00' 
