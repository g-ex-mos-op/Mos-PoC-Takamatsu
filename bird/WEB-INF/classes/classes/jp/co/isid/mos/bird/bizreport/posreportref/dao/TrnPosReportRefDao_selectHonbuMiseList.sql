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
,     (SELECT * FROM BM01TENM MISE
      WHERE MISE.COMPANY_CD = /*requestDto.companyCd*/'00'
      AND   MISE./*$requestDto.taishoJoken*/'SIBU_CD' = /*requestDto.hyojiTaisho*/'011'
      ) BM01

WHERE BT76.COMPANY_CD = /*requestDto.companyCd*/'00'
AND   BT76.DATA_NUM   = '00'
AND   BT76.SHU_SYS_DT = /*requestDto.latestDate*/'20070202'
/*IF birdUserInfo.limit */
AND   EXISTS (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*requestDto.companyCd*/'00'
         AND    BM51.SV_CD      = /*birdUserInfo.userId*/'99990001'
         AND    BM01.SIBU_CD = BM51.SIBU_CD
         GROUP BY BM51.SIBU_CD
      )
/*END*/
AND   BT76.COMPANY_CD = BM01.COMPANY_CD
AND   BT76.MISE_CD    = BM01.MISE_CD

ORDER BY BT76.MISE_CD