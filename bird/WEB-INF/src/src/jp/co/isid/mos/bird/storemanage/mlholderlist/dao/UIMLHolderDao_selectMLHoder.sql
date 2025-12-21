SELECT     rtrim(BM01.MISE_NAME_KJ) AS MISE_NAME_KJ
,          BM12.STAFF_ID
,          rtrim(BM12.STAFF_L_NAME_KJ) AS STAFF_L_NAME_KJ
,          rtrim(BM12.STAFF_F_NAME_KJ) AS STAFF_F_NAME_KJ
,          BT26.LICENSE_KBN
,          BT26.LICENSE_NO
,          BT26.LICENSE_DT
,          BT26.LICENSE_VALID_DT
,          BT26.RENEW1_STATUS
,          BT26.RENEW2_STATUS
,          BT26.RENEW3_STATUS
,          BM12.SITUATION_KBN
,          BM11.ONER_CD
,          rtrim(BM11.ONER_NAME_KJ) AS ONER_NAME_KJ
,          BM10.SIBU_CD
,          rtrim(BM10.SIBU_NAME) AS SIBU_NAME_KJ
,          BM01.MISE_CD
,          CASE WHEN BT26.LICENSE_NO <> '' THEN '0' ELSE '1'
			  END AS LICENSE_FLG

from       BM01TENM AS BM01
	       INNER JOIN BM12STAF AS BM12 ON BM12.MISE_CD_1= BM01.MISE_CD
	                                  AND BM12.COMPANY_cd= BM01.COMPANY_CD
	       INNER JOIN BT26UPJK AS BT26 ON BM12.STAFF_ID= BT26.STAFF_ID
           INNER JOIN BM10GSIB AS BM10 ON BM10.COMPANY_CD = BM01.COMPANY_CD AND BM10.SIBU_CD = BM01.AREA_DAI
           INNER JOIN BM11ONER AS BM11 ON BM11.COMPANY_CD = BM01.COMPANY_CD  AND BM11.ONER_CD = BM01.ONER_CD
where      BM12.COMPANY_CD= /*dto.companyCd*/'00'
       AND (BT26.LICENSE_VALID_DT >= substr(/*dto.sysDate*/'20060826', 1, 6)
             OR BT26.LICENSE_VALID_DT = '') 
       AND BT26.EXPIRE_FLG <> '1'
/*IF dto.allDownload == false */
/*IF dto.selectFlg == 0 && dto.sitenCd != null */AND BM01.AREA_DAI = /*dto.sitenCd*/'031'/*END*/
/*IF dto.selectFlg == 1 && dto.onerCd != null  */AND BM01.ONER_CD = /*dto.onerCd*/'36340'/*END*/
/*IF dto.selectFlg == 2 && dto.miseCd != null  */AND BM01.MISE_CD = /*dto.miseCd*/'00693'/*END*/
/*END*/
ORDER BY 
/*IF dto.allDownload == true */
         BM01.SIBU_CD,
/*END*/
         BM01.MISE_CD,
         BM12.SITUATION_KBN,
         LICENSE_FLG,
         BT26.LICENSE_NO