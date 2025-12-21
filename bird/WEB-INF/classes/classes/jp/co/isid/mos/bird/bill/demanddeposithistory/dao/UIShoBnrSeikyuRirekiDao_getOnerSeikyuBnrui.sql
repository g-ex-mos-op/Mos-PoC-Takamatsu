SELECT
    BUNRUI.ONER_CD
   ,rtrim(BUNRUI.ONER_NAME_KJ) as ONER_NAME_KJ
   ,BUNRUI.SEIKYU_BNRUI
   ,rtrim(BUNRUI.SE_BNR_NAME) as SE_BNR_NAME
   ,coalesce(SOTO.KIN,0) as SOTO_KIN
   ,coalesce(UTI.KIN,0) as UTI_KIN
FROM
   (
    SELECT
        ONER_CD 
       ,ONER_NAME_KJ 
       ,SEIKYU_BNRUI
       ,SE_BNR_NAME
    FROM
        TC26SEIK
       ,TM11ONER
    WHERE
           COMPANY_CD = /*companyCd*/'00'
      AND  ONER_CD = /*urikakeCd*/'36870'
   ) BUNRUI 
   LEFT JOIN 
       ( 
        SELECT
            T.SEIKYU_BNRUI 
           ,sum(T.URI_KIN + T.UCHI_TAX) KIN
        FROM
            BS03USSR T
        WHERE
              T.COMPANY_CD = /*companyCd*/'00'
          AND T.URIKAKE_CD = /*urikakeCd*/'36870'
          AND T.SEIKYUSHO_ID = /*seikyushoId*/'2006053101'
          AND (T.URI_KIN != 0 or T.SOTO_TAX != 0 )
          AND (T.STAX_URI = '1' or T.STAX_URI = '3' )
        GROUP BY
            T.SEIKYU_BNRUI
       ) UTI ON (BUNRUI.SEIKYU_BNRUI = UTI.SEIKYU_BNRUI)
   LEFT JOIN 
       ( 
        SELECT
            T.SEIKYU_BNRUI 
           ,sum(T.URI_KIN) KIN
        FROM
            BS03USSR T
        WHERE
              T.COMPANY_CD = /*companyCd*/'00'
          AND T.URIKAKE_CD = /*urikakeCd*/'36870'
          AND T.SEIKYUSHO_ID = /*seikyushoId*/'2006053101'
          AND (T.URI_KIN != 0 or T.SOTO_TAX != 0 )
          AND T.STAX_URI = '0'
        GROUP BY
            T.SEIKYU_BNRUI
       ) SOTO ON (BUNRUI.SEIKYU_BNRUI = SOTO.SEIKYU_BNRUI)
