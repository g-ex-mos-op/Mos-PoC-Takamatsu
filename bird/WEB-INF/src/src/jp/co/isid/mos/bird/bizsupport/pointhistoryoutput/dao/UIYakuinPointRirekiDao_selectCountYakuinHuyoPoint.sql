select
   count(*)
from
  BD60YPRI bd60
    left outer join VIR51USER vir51 on ( bd60.user_id=vir51.user_id )
    left outer join VBM78COMR vbm78 on ( bd60.kb_company_cd=vbm78.kb_company_cd  )
    left outer join (
                      select
                          a.NENDO
                        , a.USER_ID
                        , a.NYUSYA_DT
                        , a.TAISHOKU_DT
                        , a.KINZOKU_YEAR
                        , a.RETIRE_CD
                      from
                          BD61TSES a ,
                          ( select max(nendo) as NENDO, user_id from BD61TSES group by user_id ) b
                      where
                           a.NENDO=b.NENDO and a.USER_ID=b.USER_ID

                     )  bd61  on ( bd60.user_id=bd61.user_id )
where
    1=1
    /*IF nendoFrom != null */
      AND bd60.nendo >= /*nendoFrom*/'2013'
   /*END*/
   /*IF nendoTo != null */
      AND bd60.nendo <= /*nendoTo*/'2017'
   /*END*/
   /*IF userID != null */
      AND bd60.user_id = /*userID*/'00000610'
   /*END*/
   /*IF !kbCompanyCd.equals("") */
      AND bd60.kb_company_cd = /*kbCompanyCd*/'00'
   /*END*/
   /*IF taishokuFlg == "2" */
      AND (bd61.taishoku_dt is  null  OR  bd61.taishoku_dt >= /*sysDate*/'20170401')
   /*END*/
