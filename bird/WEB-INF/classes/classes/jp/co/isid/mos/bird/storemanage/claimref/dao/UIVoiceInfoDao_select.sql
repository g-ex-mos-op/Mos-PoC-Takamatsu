SELECT BD05.COMPANY_CD
,      BD05.CLAIM_NO
,      BD05.JUSIN_DT
,      RTRIM(BD05.KANRYO_DT) AS KANRYO_DT
,      BM01.SIBU_CD
,      BM01.SIBU_NAME
,      BD05.MISE_CD
,      CASE WHEN BM01.MISE_CD IS NULL THEN 'NO MISE' ELSE BM01.MISE_NAME_KJ END AS MISE_NAME_KJ
,      BD05.BNR_L
,      BD05.BNR_L_NAME
,      BD05.BNR_M
,      BC30.BNR_M_NAME
,      BD05.BNR_S
,      BD05.BNR_S_NAME
,      BD05.GENIN
,      BD05.NAIYOU
,      BD05.TENPO_TAIOU
,      BD05.SV_TAISAKU
,      BD05.UKETUKE_NO
,      BD05.SV_CHECK
,      BD05.KOKAI_FLG
,      BD05.TYPE_CD
,      BD05.TYPE_NAME
,      BD05.KAITOU

,      CLOSE_FLG
,      SV_NAME_KJ
FROM BD05VICE BD05
     LEFT OUTER JOIN (
      SELECT BM01.COMPANY_CD
      ,      BM10.HONBU_CD
      ,      BM10.HONBU_NAME
      ,      BM10.JIGYOU_CD
      ,      BM10.JIGYOU_NAME
      ,      BM10.SLAREA_CD
      ,      BM10.SLAREA_NAME
      ,      BM10.SIBU_CD
      ,      BM10.SIBU_NAME
      ,      BM01.MISE_CD
      ,      BM01.MISE_NAME_KJ
      ,      BM01.ONER_CD
      ,      CASE WHEN CLOSE_DT < /*sysDate*/'20080701' THEN '1' ELSE '0' END AS CLOSE_FLG
      ,      BR01.USER_NAME_KJ AS SV_NAME_KJ
      FROM BM01TENM BM01
      LEFT OUTER JOIN BM50TANM BM50 on (BM01.COMPANY_CD = BM50.COMPANY_CD AND BM01.MISE_CD = BM50.MISE_CD)
      LEFT OUTER JOIN BR01USER BR01 on (BM50.SV_CD = BR01.USER_ID)
      ,    BM10GSIB BM10
      WHERE BM01.COMPANY_CD = /*companyCd*/'00'
      AND   BM10.COMPANY_CD  = BM01.COMPANY_CD
/*IF taishoJoken.equals("AREADAI")*/
      AND   BM10.AREA_DAI_FLG = '1'
      AND   BM10.SIBU_CD     = BM01.AREA_DAI
--ELSE
      AND   BM10.SIBU_CD     = BM01.SIBU_CD
/*END*/
     ) BM01 on (BD05.COMPANY_CD = BM01.COMPANY_CD AND BD05.MISE_CD = BM01.MISE_CD)
, BC30CBRM BC30
/*IF userTypeCd == "02" && (hyojiNaiyo == null || hyojiNaiyo.equals("4")==false) */
,    BM06UONR BM06
/*END*/
/*IF userTypeCd == "03" && (hyojiNaiyo == null || hyojiNaiyo.equals("4")==false) */
,    BM07UTEN BM07
/*END*/
/*IF taishoJoken.equals("SEGMENT") */
,    (
SELECT GYOTAI_KBN
FROM BM09GTSG BM09
WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
GROUP BY GYOTAI_KBN
) BM09
/*END*/

WHERE BD05.JUSIN_DT like /*taishoNengetu*/'200805%'
/*IF hyojiNaiyo != null*/
/*IF hyojiNaiyo.equals("1") || hyojiNaiyo.equals("4")*/
AND   BD05.TYPE_CD = /*hyojiNaiyo*/'1'
--ELSE
AND   BD05.TYPE_CD = '1'
	/*IF hyojiNaiyo.equals("1COMP")*/
AND   LENGTH(RTRIM(BD05.KANRYO_DT)) > 0
	/*END*/
	/*IF hyojiNaiyo.equals("1YET")*/
AND   LENGTH(RTRIM(BD05.KANRYO_DT)) =0
	/*END*/
/*END*/
/*END*/
/*IF kanriNo != null */
AND   BD05.CLAIM_NO = /*kanriNo*/''
/*END*/
/*IF bnrM != null */
AND   BD05.BNR_M = /*bnrM*/''
/*END*/
AND   BC30.BNR_M = BD05.BNR_M
/*IF userTypeCd == "02" && (hyojiNaiyo == null ||  hyojiNaiyo.equals("4")==false) */
      AND   BM06.USER_ID    = /*userId*/'99990002'
      AND   BM06.COMPANY_CD = BM01.COMPANY_CD
      AND   BM06.ONER_CD    = BM01.ONER_CD
/*END*/
/*IF userTypeCd == "03" && (hyojiNaiyo == null ||  hyojiNaiyo.equals("4")==false) */
      AND   BM07.USER_ID    = /*userId*/'99990003'
      AND   BM07.COMPANY_CD = BM01.COMPANY_CD
      AND   BM07.MISE_CD    = BM01.MISE_CD
/*END*/

/*IF taishoJoken.equals("SEGMENT") */
      AND   BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
/*END*/
/*IF taishoJoken.equals("JIGYOU") */
      AND   BM01.JIGYOU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SLAREA") */
      AND   BM01.SLAREA_CD = /*hyojiTaisho*/
/*END*/
/*IF taishoJoken.equals("SIBU") || taishoJoken.equals("AREADAI") */
      AND   BM01.SIBU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SIBU") && blockCd != null */
      AND   BM01.BLOCK_CD = /*blockCd*/''
/*END*/
/*IF taishoJoken.equals("ONER") */
      AND   BM01.ONER_CD = /*hyojiTaisho*/'36001'
/*END*/

/*IF taishoJoken.equals("MISE") */
      AND   BM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF  (hyojiNaiyo == null || "4".equals(hyojiNaiyo)==false) && userTypeCd == "01" && limitFlg == true */
      AND    BM01.SIBU_CD IN (
         SELECT BM51.SIBU_CD
         FROM   BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
      )
/*END*/

ORDER BY BD05.JUSIN_DT DESC
,        BD05.CLAIM_NO