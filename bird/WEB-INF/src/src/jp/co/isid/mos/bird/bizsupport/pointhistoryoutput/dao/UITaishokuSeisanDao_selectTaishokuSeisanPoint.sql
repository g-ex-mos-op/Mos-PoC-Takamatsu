select
    bd61.NENDO                    as  NENDO
  , bd61.USER_ID                  as  USER_ID
  , rtrim(vir51.USER_NAME_KJ)     as  USER_NAME_KJ
  , bd61.POINT_RUIKEI             as  POINT_RUIKEI
  , bd61.KB_COMPANY_CD            as  KB_COMPANY_CD
  , rtrim(vbm78.KB_COMPANY_NAME)  as  KB_COMPANY_NAME
  , bd61.NYUSYA_DT                as  NYUSYA_DT
  , bd61.TAISHOKU_DT              as  TAISHOKU_DT
  , bd61.KINZOKU_YEAR             as  KINZOKU_YEAR
  , bd61.RETIRE_CD                as  RETIRE_CD
  , rtrim(bm79.RETIRE_NAME)       as  RETIRE_NAME
  , bd61.SIKYU_RATE               as  SIKYU_RATE
  , bd61.T_SEISAN_POINT           as  T_SEISAN_POINT
  , rtrim(bd61.BIKOU)             as  BIKOU
from
  BD61TSES bd61
    left outer join VIR51USER vir51 on ( bd61.user_id=vir51.user_id )
    left outer join VBM78COMR vbm78 on ( bd61.kb_company_cd=vbm78.kb_company_cd  )
    left outer join BM79RETI  bm79  on ( bd61.retire_cd=bm79.retire_cd )
where
    1=1
    /*IF nendoFrom != null */
      AND bd61.nendo >= /*nendoFrom*/'2013'
   /*END*/
   /*IF nendoTo != null */
      AND bd61.nendo <= /*nendoTo*/'2017'
   /*END*/
   /*IF userID != null */
      AND bd61.user_id = /*userID*/'00081018'
   /*END*/
   /*IF !kbCompanyCd.equals("") */
      AND bd61.kb_company_cd = /*kbCompanyCd*/'00'
   /*END*/
order by
    bd61.USER_ID     asc
  , bd61.NENDO       desc
  , bd61.NYUSYA_DT   desc