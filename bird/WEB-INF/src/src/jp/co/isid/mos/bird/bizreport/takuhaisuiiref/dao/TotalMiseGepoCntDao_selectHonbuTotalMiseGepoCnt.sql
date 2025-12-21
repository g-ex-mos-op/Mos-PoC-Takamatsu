SELECT
    count(distinct BT73.MISE_CD) as TOTAL_TEMPO_CNT
FROM
    BT73TAKU BT73 
   ,BN01DTEN BN01 
   ,BM01TENM BM01 
   /*IF taishoJoken.equals("SEGMENT") */
       ,BM09GTSG BM09 
   /*END*/
   /*IF taishoJoken.equals("JIGYOU") || taishoJoken.equals("SLAREA")*/
       ,BM10GSIB BM10 
   /*END*/
WHERE 
      BT73.COMPANY_CD   = /*companyCd*/'00'
  AND BT73.EIGYO_DT    >= /*kikanStart*/'200506'
  AND BT73.EIGYO_DT    <= /*kikanEnd*/'200605'
  AND BT73.OLDM_FLG     = '0'
  AND BT73.TAKUHAI_KBN <> ' '
  AND BT73.TAKUHAI_KBN <> '00' 
  AND BT73.COMPANY_CD = BN01.COMPANY_CD 
  AND BT73.MISE_CD    = BN01.MISE_CD 
  AND BT73.EIGYO_DT   = BN01.EIGYO_YM 
  AND BT73.COMPANY_CD = BM01.COMPANY_CD 
  AND BT73.MISE_CD    = BM01.MISE_CD 

  /*IF tenpoShubetu.equals("1") */
      AND BN01.KBN1  = '1'
  /*END*/
  /*IF tenpoShubetu.equals("2") */
      AND (BN01.KBN1 = '1' OR BN01.KBN1 = '2')
  /*END*/
  /*IF tenpoShubetu.equals("3") */
      AND BN01.KBN1  = '3'
  /*END*/

  /*IF taishoJoken.equals("SEGMENT") */
      AND BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
      AND BM09.SEGMENT_TYPE = /*hyojiTaisho*/'00'
  /*END*/
  /*IF taishoJoken.equals("JIGYOU") */
      AND BM01.COMPANY_CD = BM10.COMPANY_CD
      AND BM01.SIBU_CD = BM10.SIBU_CD
      AND BM10.JIGYOU_CD = /*hyojiTaisho*/'00'
  /*END*/
  /*IF taishoJoken.equals("SLAREA") */
      AND BM01.COMPANY_CD = BM10.COMPANY_CD
      AND BM01.SIBU_CD = BM10.SIBU_CD
      AND BM10.SLAREA_CD = /*hyojiTaisho*/'00'
  /*END*/
  /*IF taishoJoken.equals("SIBU") */
      AND BM01.SIBU_CD = /*hyojiTaisho*/'00'
      /*IF blockCd != null */
          AND BM01.BLOCK_CD = /*blockCd*/'00'
      /*END*/
  /*END*/
  /*IF taishoJoken.equals("MISE") */
      AND BM01.MISE_CD = /*hyojiTaisho*/'01002'
  /*END*/
  /*IF tenpoShubetu.equals('ALL') */
      AND (BT73.OPEN_KBN  = 1 OR BT73.OPEN_KBN_ZEN = 1)
  -- ELSE
      AND BT73.OPEN_KBN  = 1
  /*END*/
  /*IF limitFlg==true */
      AND BM01.SIBU_CD IN ( 
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
          ) 
  /*END*/
