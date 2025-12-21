select
    bd60.NENDO                    as  NENDO
  , bd60.USER_ID                  as  USER_ID
  , rtrim(vir51.USER_NAME_KJ)     as  USER_NAME_KJ
  , bd60.POINT                    as  POINT
  , rtrim(bd60.POINT_SHU_NAME)    as  POINT_SHU_NAME
  , bd60.KB_COMPANY_CD            as  KB_COMPANY_CD
  , rtrim(vbm78.KB_COMPANY_NAME)  as  KB_COMPANY_NAME
  , rtrim(bd60.BIKOU)             as  BIKOU
  , bd61.TAISHOKU_DT              as  TAISHOKU_DT
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
      AND (bd61.taishoku_dt is null  OR  bd61.taishoku_dt >= /*sysDate*/'20170401')
   /*END*/
order by
    bd60.USER_ID     asc
  , bd60.NENDO       desc
  , bd60.LAST_TMSP   desc
