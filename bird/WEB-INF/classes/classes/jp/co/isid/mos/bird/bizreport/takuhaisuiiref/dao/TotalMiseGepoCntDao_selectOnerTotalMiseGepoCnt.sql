SELECT
    count(distinct BT73.MISE_CD) as TOTAL_TEMPO_CNT
FROM
    BT73TAKU BT73 
   ,(SELECT MISE_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
  /*IF taishoJoken.equals("MISE") */
         AND MISE_CD = /*hyojiTaisho*/'01437'
    --ELSE
         AND ONER_CD = /*hyojiTaisho*/'36025'
  /*END*/
         ) BM01  
WHERE 
      BT73.COMPANY_CD   = /*companyCd*/'00'
  AND BT73.EIGYO_DT    >= /*kikanStart*/'200506'
  AND BT73.EIGYO_DT    <= /*kikanEnd*/'200605'
  AND BT73.OLDM_FLG     = '0'
  AND BT73.TAKUHAI_KBN <> ' '
  AND BT73.TAKUHAI_KBN <> '00' 
  AND BT73.OPEN_KBN  = 1 
  /*IF taishoJoken.equals("MISE") */
      AND BM01.MISE_CD = /*hyojiTaisho*/'01002'
  /*END*/
  AND BT73.MISE_CD = BM01.MISE_CD
  