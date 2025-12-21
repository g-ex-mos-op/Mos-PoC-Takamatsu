select
    bd59.NENDO                     as  NENDO
  , bd59.USER_ID                   as  USER_ID
  , rtrim(vir51.USER_NAME_KJ)      as  USER_NAME_KJ
  , bd59.POINT                     as  POINT
  , bd59.POINT_SHU                 as  POINT_SHU
  , rtrim(bc39.POINT_SHU_NAME)     as  POINT_SHU_NAME
  , bd59.KB_COMPANY_CD             as  KB_COMPANY_CD
  , rtrim(bm78.KB_COMPANY_NAME)    as  KB_COMPANY_NAME
  , bd59.RANK_CD                   as  RANK_CD
  , rtrim(bm78.RANK_NAME)          as  RANK_NAME
  , bd61.NYUSYA_DT                 as  NYUSYA_DT
  , bd61.TAISHOKU_DT               as  TAISHOKU_DT
  , bd61.KINZOKU_YEAR              as  KINZOKU_YEAR
  , bd61.RETIRE_CD                 as  RETIRE_CD
  , rtrim(bm79.RETIRE_NAME)        as  RETIRE_NAME
  , bd59.KAIGAI_FLG                as  KAIGAI_FLG
  , rtrim(bd59.BIKOU)              as  BIKOU
from
  BD59HPRI bd59
    left outer join VIR51USER vir51 on ( bd59.user_id=vir51.user_id )
    left outer join BM78COMR  bm78  on ( bd59.kb_company_cd=bm78.kb_company_cd and bd59.rank_cd=bm78.rank_cd )
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

                     )  bd61  on ( bd59.user_id=bd61.user_id )
    left outer join BM79RETI  bm79  on ( bd61.retire_cd=bm79.retire_cd )
    left outer join BC39PSHU  bc39  on ( bd59.point_shu=bc39.point_shu )
where
    1=1
    /*IF nendoFrom != null */
      AND bd59.nendo >= /*nendoFrom*/'2013'
   /*END*/
   /*IF nendoTo != null */
      AND bd59.nendo <= /*nendoTo*/'2017'
   /*END*/
   /*IF userID != null */
      AND bd59.user_id = /*userID*/'00000610'
   /*END*/
   /*IF !kbCompanyCd.equals("") */
      AND bd59.kb_company_cd = /*kbCompanyCd*/'00'
   /*END*/
   /*IF taishokuFlg == "2" */
      AND (bd61.taishoku_dt is null  OR  bd61.taishoku_dt >= /*sysDate*/'20170401')
   /*END*/
order by
    bd59.USER_ID     asc
  , bd59.NENDO       desc
  , bd59.LAST_TMSP   desc

