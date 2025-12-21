select count(*) from (
select
   bd59bd60.USER_ID              as  USER_ID
 , rtrim(vir51.USER_NAME_KJ)     as  USER_NAME_KJ
 , sum(bd59bd60.POINT)           as  POINT
 , bd59bd60.KB_COMPANY_CD        as  KB_COMPANY_CD
 , rtrim(vbm78.KB_COMPANY_NAME)  as  KB_COMPANY_NAME
 , bd61.TAISHOKU_DT              as  TAISHOKU_DT
from
 (
    select
       bd59.USER_ID                   as  USER_ID
     , bd59.POINT                     as  POINT
     , bd59.KB_COMPANY_CD             as  KB_COMPANY_CD
   from
     BD59HPRI bd59
   where
       bd59.kaigai_flg <> '1'
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
   union all
   select
       bd60.USER_ID                   as  USER_ID
     , bd60.POINT                     as  POINT
     , bd60.KB_COMPANY_CD             as  KB_COMPANY_CD
   from
     BD60YPRI bd60
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
) bd59bd60
       left outer join VIR51USER vir51 on ( bd59bd60.user_id=vir51.user_id )
       left outer join VBM78COMR vbm78 on ( bd59bd60.kb_company_cd=vbm78.kb_company_cd  )
       left outer join (
                         select
                             a.NENDO
                           , a.USER_ID
                           , a.TAISHOKU_DT
                         from
                             BD61TSES a ,
                             ( select max(nendo) as NENDO, user_id from BD61TSES group by user_id ) b
                         where
                               a.NENDO=b.NENDO and a.USER_ID=b.USER_ID

                         )  bd61  on ( bd59bd60.user_id=bd61.user_id )

 /*IF taishokuFlg == "2" */
   where (bd61.taishoku_dt is null  OR  bd61.taishoku_dt >= /*sysDate*/'20170401')
 /*END*/
group by
   bd59bd60.USER_ID
 , vir51.USER_NAME_KJ
 , bd59bd60.KB_COMPANY_CD
 , vbm78.KB_COMPANY_NAME
 , bd61.TAISHOKU_DT
)