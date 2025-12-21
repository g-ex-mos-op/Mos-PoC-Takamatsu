SELECT
    BT37.URIKAKE_CD
   ,rtrim(TM11.ONER_NAME_KJ) as URIKAKE_NAME
   ,BT37.MISE_CD
   ,rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ
   ,BT37.DENPYO_NO
   ,BT37.SHO_CD_JITU
   ,rtrim(SUB2.SHO_NAME_KJ) as SHO_NAME_KJ
   ,BT37.SHO_AMOUNT
   ,BT37.NOHIN_TANKA
   ,BT37.URI_KIN
   ,BT37.STAX_URI
   ,BT37.SOTO_TAX
   ,BT37.UCHI_TAX
   ,BT37.NOHIN_DT_J
   ,rtrim(BT37.TEKIYOU) as TEKIYOU
   ,BM32.SHO_CD_DAI
   ,rtrim(TC26.SE_BNR_NAME) as SE_BNR_NAME
   ,rtrim(TC03.NISU_NAME) as NISU_NAME
FROM
    BT37URTR BT37

        LEFT OUTER JOIN BM01TENM BM01 ON (BT37.COMPANY_CD = BM01.COMPANY_CD AND BT37.MISE_CD = BM01.MISE_CD)
        LEFT OUTER JOIN (
                      SELECT
  							 DISTINCT
  							 MM.NOHIN_DT_J
   							,MM20SHOM.SHO_CD_JITU
   							,MM20SHOM.SHO_M_DATE
   							,MM20SHOM.SHO_NAME_KJ
   							,MM20SHOM.SEIKYU_BNRUI
					FROM
						MM20SHOM
					INNER JOIN
					(
					SELECT
        				BT37URTR.NOHIN_DT_J
       					,MM20SHOM.SHO_CD_JITU
       					,MAX(MM20SHOM.SHO_M_DATE) AS SHO_M_DATE
       				FROM  BT37URTR
       				INNER JOIN  MM20SHOM
          			ON  BT37URTR.URIKAKE_CD = /*URIKAKECD*/'36216'
          			AND BT37URTR.COMPANY_CD = /*COMPANYCD*/'00'
          			AND MM20SHOM.SHO_CD_JITU = BT37URTR.SHO_CD_JITU
          			AND MM20SHOM.SHO_M_DATE <= BT37URTR.NOHIN_DT_J
       				GROUP BY BT37URTR.NOHIN_DT_J, MM20SHOM.SHO_CD_JITU
					) MM
						ON MM20SHOM.SHO_CD_JITU = MM.SHO_CD_JITU  AND MM20SHOM.SHO_M_DATE= MM.SHO_M_DATE
                     ) SUB2 ON (BT37.SHO_CD_JITU = SUB2.SHO_CD_JITU AND BT37.NOHIN_DT_J = SUB2.NOHIN_DT_J)
        LEFT OUTER JOIN BM32JDAI BM32 ON (BT37.SHO_CD_JITU = BM32.SHO_CD_JITU)
        LEFT OUTER JOIN TC26SEIK TC26 ON (SUB2.SEIKYU_BNRUI = TC26.SEIKYU_BNRUI)
        LEFT OUTER JOIN (
                        SELECT
                           DISTINCT
                           TM.NOHIN_DT_J
                          ,TM66SHOK.SHO_CD_JITU
                          ,TM66SHOK.SHO_M_DATE
                          ,TM66SHOK.SHO_NISUGATA
                          ,TM66SHOK.UNIT_KBN
                       FROM
                            TM66SHOK
                       INNER JOIN
                           (
                           SELECT
                                BT37URTR.NOHIN_DT_J
                               ,TM66SHOK.SHO_CD_JITU
                               ,MAX(TM66SHOK.SHO_M_DATE ) AS SHO_M_DATE
                               ,UNIT_KBN
                           FROM BT37URTR
                               INNER JOIN
                               TM66SHOK
                               ON
                                   BT37URTR.URIKAKE_CD = /*URIKAKECD*/'36216'
                                   AND BT37URTR.COMPANY_CD = /*COMPANYCD*/'00'
                                   AND TM66SHOK.SHO_CD_JITU = BT37URTR.SHO_CD_JITU
                                   AND TM66SHOK.SHO_M_DATE <= BT37URTR.NOHIN_DT_J
                                   GROUP BY BT37URTR.NOHIN_DT_J,TM66SHOK.SHO_CD_JITU,UNIT_KBN
                           ) TM
                                 ON TM66SHOK.SHO_CD_JITU = TM.SHO_CD_JITU AND TM66SHOK.SHO_M_DATE = TM.SHO_M_DATE
                       ) SUB3  ON (SUB2.SHO_CD_JITU = SUB3.SHO_CD_JITU
                               AND BT37.NOHIN_UNIT = SUB3.UNIT_KBN
                               AND BT37.NOHIN_DT_J = SUB3.NOHIN_DT_J)
        LEFT OUTER JOIN TC03NISU TC03 ON (SUB3.SHO_NISUGATA = TC03.SHO_NISUGATA)
   ,TM11ONER TM11
   ,(
     SELECT
        U.COMPANY_CD
       ,U.URIKAKE_CD
       ,U.URIKAKE_YM
       ,U.MISE_CD
       ,U.SEIKYU_ID
       ,U.SEIKYUSHO_ID
     FROM
        BS02SEKR S,
        BS01URSR U
     WHERE
        S.URIKAKE_CD   = /*urikakeCd*/'36216' AND
        S.SEIKYUSHO_ID = /*seikyuId*/'200601' AND
        S.COMPANY_CD   = U.COMPANY_CD AND
        S.URIKAKE_CD   = U.URIKAKE_CD AND
        S.SEIKYUSHO_ID = U.SEIKYUSHO_ID
    ) SUB1

WHERE
      BT37.COMPANY_CD = TM11.COMPANY_CD
  AND BT37.URIKAKE_CD = TM11.ONER_CD
  AND BT37.URIKAKE_CD = /*urikakeCd*/'36216'
  AND BT37.COMPANY_CD = /*companyCd*/'00'
  AND BT37.COMPANY_CD = SUB1.COMPANY_CD
  AND BT37.URIKAKE_CD = SUB1.URIKAKE_CD
  AND BT37.MISE_CD    = SUB1.MISE_CD
  AND BT37.SEIKYU_ID  = SUB1.SEIKYU_ID
  AND BT37.URIKAKE_YM = SUB1.URIKAKE_YM
ORDER BY
/*IF sort.equals("1")*/
    BT37.MISE_CD
   ,BT37.NOHIN_DT_J
   ,BT37.DENPYO_NO
/*END*/
/*IF sort.equals("2")*/
    BT37.SEIKYU_NOHIN_SEQ
   ,BT37.MISE_CD
   ,BT37.NOHIN_DT_J
   ,BT37.DENPYO_NO
/*END*/










