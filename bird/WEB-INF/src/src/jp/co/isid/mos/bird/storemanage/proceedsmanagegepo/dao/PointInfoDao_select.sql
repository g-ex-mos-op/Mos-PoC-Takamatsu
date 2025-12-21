SELECT
  NVL(C.EIGYO_DT, NVL(D.EIGYO_DT,F.EIGYO_DT)) EIGYO_DT
  , NVL(C.POINT_KEI,0) POINT_KEI
  , NVL(F.POINT_KEI,0) POINT_NET_KEI
  , NVL(D.Y_CHARGE_KIN,0) Y_CHARGE_KIN
FROM
(
SELECT
  NVL(A.EIGYO_DT, B.EIGYO_DT) EIGYO_DT
  , SUM(NVL(A.POINT_KEI, 0) - NVL(B.POINT_KEI, 0)) POINT_KEI
FROM
  (
    SELECT
	  BD67.mise_cd
	  , BD67.COMPANY_CD
      , BD67.EIGYO_DT
      , BD67.POINT_KEI
    FROM
      BD67DPNT BD67
	  , BM01TENM BM01
    WHERE
      BD67.P_HENDO_KBN = '031'
	  AND BM01.COMPANY_CD = BD67.COMPANY_CD
	  AND BM01.MISE_CD = BD67.MISE_CD
	  /*IF "01".equals(userType) && limitKbn*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'99990001'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
      /*END*/
      /*IF "02".equals(userType) */
            AND   EXISTS(SELECT * FROM BM06UONR BM06
                      WHERE BM06.USER_ID = /*userId*/'99990003'
                      AND   BM06.ONER_CD = /*onerCd*/'36378'
                      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM06.ONER_CD    = BM01.ONER_CD)
      /*END*/
      /*IF "03".equals(userType) */
            AND   EXISTS(SELECT * FROM BM07UTEN BM07
                      WHERE BM07.USER_ID    = /*userId*/'99990003'
	                  AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	                  AND   BM07.MISE_CD    = BM01.MISE_CD)
      /*END*/
      /*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false */
	              AND BD67.MISE_CD = /*miseCd*/'00014'
      /*END*/
  ) A
  FULL OUTER JOIN (
    SELECT
      BD67.mise_cd
	  , BD67.COMPANY_CD
      , BD67.EIGYO_DT
      , BD67.POINT_KEI
    FROM
      BD67DPNT BD67
	  , BM01TENM BM01
    WHERE
      BD67.P_HENDO_KBN = '041'
	  AND BM01.COMPANY_CD = BD67.COMPANY_CD
	  AND BM01.MISE_CD = BD67.MISE_CD
	  /*IF "01".equals(userType) && limitKbn*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'99990001'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
      /*END*/
      /*IF "02".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM06UONR BM06
                      WHERE BM06.USER_ID = /*userId*/'99990003'
                      AND   BM06.ONER_CD = /*onerCd*/'36378'
                      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM06.ONER_CD    = BM01.ONER_CD)
      /*END*/
      /*IF "03".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM07UTEN BM07
                      WHERE BM07.USER_ID    = /*userId*/'99990003'
	                  AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	                 AND   BM07.MISE_CD    = BM01.MISE_CD)
      /*END*/
      /*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false*/
	              AND BD67.MISE_CD = /*miseCd*/'00014'
      /*END*/
  ) B
    ON A.EIGYO_DT = B.EIGYO_DT AND A.MISE_CD = B.MISE_CD AND A.COMPANY_CD = B.COMPANY_CD
WHERE (A.COMPANY_CD = /*companyCd*/'00' OR B.COMPANY_CD = /*companyCd*/'00')
AND   ((A.EIGYO_DT LIKE /*taishoYM*/'201209' || '%') OR (B.EIGYO_DT LIKE /*taishoYM*/'201209' || '%'))
GROUP BY NVL (A.EIGYO_DT,B.EIGYO_DT)
ORDER BY
  EIGYO_DT
) C
FULL OUTER JOIN
(
SELECT BD69.EIGYO_DT EIGYO_DT
       , SUM(NVL(BD69.Y_CHARGE_KIN, 0)) Y_CHARGE_KIN
FROM BD69KABD BD69
     , BM01TENM BM01
WHERE BM01.COMPANY_CD = BD69.COMPANY_CD
      AND BM01.MISE_CD = BD69.MISE_CD
      AND BD69.COMPANY_CD = /*companyCd*/'00'
      AND BD69.EIGYO_DT LIKE /*taishoYM*/'201209' || '%'
      AND BD69.NYUSHUTSU_KBN = '06'
      /*IF "01".equals(userType) && limitKbn*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'99990001'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
      /*END*/
      /*IF "02".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM06UONR BM06
                      WHERE BM06.USER_ID = /*userId*/'99990003'
                      AND   BM06.ONER_CD = /*onerCd*/'36378'
                      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM06.ONER_CD    = BM01.ONER_CD)
      /*END*/
      /*IF "03".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM07UTEN BM07
                      WHERE BM07.USER_ID    = /*userId*/'99990003'
                      AND   BM07.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM07.MISE_CD    = BM01.MISE_CD)
      /*END*/
      /*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false*/
                  AND BD69.MISE_CD = /*miseCd*/'00014'
      /*END*/
GROUP BY BD69.EIGYO_DT
) D
ON C.EIGYO_DT = D.EIGYO_DT
FULL OUTER JOIN
(
SELECT
  NVL(A.EIGYO_DT, B.EIGYO_DT) EIGYO_DT
  , SUM(NVL(A.POINT_KEI, 0) - NVL(B.POINT_KEI, 0)) POINT_KEI
FROM
  (
    SELECT
	  BD70.mise_cd
	  , BD70.COMPANY_CD
      , BD70.EIGYO_DT
      , BD70.POINT_KEI
    FROM
      BD70DNET BD70
	  , BM01TENM BM01
    WHERE
      BD70.P_HENDO_KBN = '031'
	  AND BM01.COMPANY_CD = BD70.COMPANY_CD
	  AND BM01.MISE_CD = BD70.MISE_CD
	  /*IF "01".equals(userType) && limitKbn*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'99990001'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
      /*END*/
      /*IF "02".equals(userType) */
            AND   EXISTS(SELECT * FROM BM06UONR BM06
                      WHERE BM06.USER_ID = /*userId*/'99990003'
                      AND   BM06.ONER_CD = /*onerCd*/'36378'
                      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM06.ONER_CD    = BM01.ONER_CD)
      /*END*/
      /*IF "03".equals(userType) */
            AND   EXISTS(SELECT * FROM BM07UTEN BM07
                      WHERE BM07.USER_ID    = /*userId*/'99990003'
	                  AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	                  AND   BM07.MISE_CD    = BM01.MISE_CD)
      /*END*/
      /*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false */
	              AND BD70.MISE_CD = /*miseCd*/'00014'
      /*END*/
  ) A
  FULL OUTER JOIN (
    SELECT
      BD70.mise_cd
	  , BD70.COMPANY_CD
      , BD70.EIGYO_DT
      , BD70.POINT_KEI
    FROM
      BD70DNET BD70
	  , BM01TENM BM01
    WHERE
      BD70.P_HENDO_KBN = '041'
	  AND BM01.COMPANY_CD = BD70.COMPANY_CD
	  AND BM01.MISE_CD = BD70.MISE_CD
	  /*IF "01".equals(userType) && limitKbn*/
            AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'99990001'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
      /*END*/
      /*IF "02".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM06UONR BM06
                      WHERE BM06.USER_ID = /*userId*/'99990003'
                      AND   BM06.ONER_CD = /*onerCd*/'36378'
                      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
                      AND   BM06.ONER_CD    = BM01.ONER_CD)
      /*END*/
      /*IF "03".equals(userType)*/
            AND   EXISTS(SELECT * FROM BM07UTEN BM07
                      WHERE BM07.USER_ID    = /*userId*/'99990003'
	                  AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	                 AND   BM07.MISE_CD    = BM01.MISE_CD)
      /*END*/
      /*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false*/
	              AND BD70.MISE_CD = /*miseCd*/'00014'
      /*END*/
  ) B
    ON A.EIGYO_DT = B.EIGYO_DT AND A.MISE_CD = B.MISE_CD AND A.COMPANY_CD = B.COMPANY_CD
WHERE (A.COMPANY_CD = /*companyCd*/'00' OR B.COMPANY_CD = /*companyCd*/'00')
AND   ((A.EIGYO_DT LIKE /*taishoYM*/'201209' || '%') OR (B.EIGYO_DT LIKE /*taishoYM*/'201209' || '%'))
GROUP BY NVL (A.EIGYO_DT,B.EIGYO_DT)
ORDER BY
  EIGYO_DT
) F
ON C.EIGYO_DT = F.EIGYO_DT OR D.EIGYO_DT = F.EIGYO_DT
ORDER BY EIGYO_DT