SELECT rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ
   ,   BM01.CLOSE_DT
   ,   BT76.MISE_CD
   ,   BT76.SHU_SYS_TIME
   ,   BT76.ALL_KEN
   ,   BT76.ALL_KIN
   ,   BT76.EAT_KIN
   ,   BT76.TAKE_KIN
   ,   BT76.TEL_KIN
   ,   BT76.DRIVE_KIN
   ,   BT76.OTHER_KIN
   ,   BT76.EAT_KEN
   ,   BT76.TAKE_KEN
   ,   BT76.TEL_KEN
   ,   BT76.DRIVE_KEN
   ,   BT76.OTHER_KEN

FROM  BT76SRAL BT76
,     (SELECT BM01.COMPANY_CD
       ,      BM01.MISE_CD
       ,      BM01.MISE_NAME_KJ
       ,      BM01.CLOSE_DT
       FROM   BM01TENM BM01
       ,      BM06UONR BM06
       WHERE  BM06.USER_ID    = /*birdUserInfo.userId*/'99990002'
       AND    BM06.COMPANY_CD = /*requestDto.companyCd*/'00'
       AND    BM01.COMPANY_CD = BM06.COMPANY_CD
       AND    BM01.ONER_CD    = BM06.ONER_CD
       AND    EXISTS (
       	     SELECT * FROM PC01TENM PC01 
       	     WHERE PC01.COMPANY_CD = /*requestDto.companyCd*/'00'
             AND   PC01.BB_KBN     = '1'
             AND   BM01.MISE_CD = PC01.MISE_CD
       )
       ) BM01
WHERE BT76.COMPANY_CD = /*requestDto.companyCd*/'00'
AND   BT76.DATA_NUM   = '00'
AND   BT76.SHU_SYS_DT = /*requestDto.latestDate*/'20070202'
AND   BT76.COMPANY_CD = BM01.COMPANY_CD
AND   BT76.MISE_CD    = BM01.MISE_CD

ORDER BY BT76.MISE_CD